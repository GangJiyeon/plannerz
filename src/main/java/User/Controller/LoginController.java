package User.Controller;

import User.Dto.LoginCommand;
import User.Dto.LoginSession;
import User.Dto.NaverLoginBO;
import User.Dto.UserInfo;
import User.Exception.WrongIdPwException;
import User.Validator.LoginCommandValidator;
import User.Service.LoginService;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    public void setLoginService(LoginService loginService, NaverLoginBO naverLoginBO) {
        this.loginService = loginService;
        this.naverLoginBO = naverLoginBO;
    }
    @Autowired
    private NaverLoginBO naverLoginBO;
    private String apiResult = null;


    @GetMapping("/login")
    public String login(Model model, HttpSession session, LoginCommand loginCommand,
                        @CookieValue(value = "remember_me", required = false) Cookie cookie, HttpServletRequest request) throws Exception{





        model.addAttribute("loginCommand", new LoginCommand());

        /* 네이버아이디로 인증 URL을 생성하기 위하여 naverLoginBO클래스의 getAuthorizationUrl메소드 호출 */
        String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session);

        //https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=sE***************&
        //redirect_uri=http%3A%2F%2F211.63.89.90%3A8090%2Flogin_project%2Fcallback&state=e68c269c-5ba9-4c31-85da-54c16c658125
        System.out.println("네이버:" + naverAuthUrl);

        // 네이버
        model.addAttribute("url", naverAuthUrl);

        if(cookie != null){
            loginCommand.setUser_id(cookie.getValue());
            loginCommand.setRemember_me(true);
            System.out.println(cookie.getValue());
        }

        //카카오 인가코드 발급
        System.out.println("--------- 카카오연동 들어옴 ---------");

        String reqUrl =
                "https://kauth.kakao.com/oauth/authorize"
                        + "?client_id=7b08064aebeb5d53440182eabe007d83"
                        + "&redirect_uri=http://localhost:8080/Z/login/oauth_kakao"
                        + "&response_type=code";

        model.addAttribute("reqUrl", reqUrl);

        return "login";
    }


    //직접 입력 로그인
    @PostMapping("/login/self")
    public String selfLogin(@ModelAttribute("loginCommand") @Valid LoginCommand loginCommand,
                            Errors errors, HttpSession session,
                            HttpServletResponse response) {

        new LoginCommandValidator().validate(loginCommand, errors);

        if (errors.hasErrors()) {
            return "login";
        }

        try {
            UserInfo userInfo = loginService.loginService(loginCommand);

            if (userInfo != null) {
                LoginSession loginSession = new LoginSession(userInfo.getUser_id(), userInfo.getUser_name(), userInfo.getSns());
                session.setAttribute("loginSession", loginSession);
            }

            Cookie rememberCookie = new Cookie("remember_me", loginCommand.getUser_id());
            rememberCookie.setPath("/");

            System.out.println(loginCommand.getRemember_me());

            if (loginCommand.getRemember_me()){
                rememberCookie.setMaxAge(60*60*24*30);

                System.out.println("success");
            }else {
                rememberCookie.setMaxAge(0);

                System.out.println("fail");

            }

            response.addCookie(rememberCookie);

            return "login_success";
        } catch (WrongIdPwException e) {
            errors.reject("ioPasswordNotMatching");
            System.out.println("login error");
            return "login";
        }
    }

    //네이버 로그인 성공시 callback호출 메소드
    @RequestMapping(value = "/login_success", method = {RequestMethod.GET, RequestMethod.POST})
    public String callback(Model model,
                           @RequestParam("code") String code,
                           @RequestParam("state") String state, HttpSession session,
                           LoginSession loginSession)
            throws IOException, ParseException, IOException {



        OAuth2AccessToken oauthToken;
        oauthToken = naverLoginBO.getAccessToken(session, code, state);

        //1. 로그인 사용자 정보를 읽어온다.
        apiResult = naverLoginBO.getUserProfile(oauthToken);  //String형식의 json데이터
        /** apiResult json 구조
         {"resultcode":"00",
         "message":"success",
         "response":{"id":"33666449",
                    "nickname":"shinn****",
                     "age":"20-29",
                     "gender":"M",
                     "email":"sh@naver.com",
                     "name":"\uc2e0\ubc94\ud638"
                    }
         }
         **/

        //2. String형식인 apiResult를 json형태로 바꿈
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(apiResult);
        JSONObject jsonObj = (JSONObject) obj;

        //3. 데이터 파싱
        //Top레벨 단계 _response 파싱
        JSONObject response_obj = (JSONObject) jsonObj.get("response");
        //response의 nickname값 파싱


        String user_id = (String) response_obj.get("id");
        String user_name = (String) response_obj.get("name") ;

        System.out.println(user_id);
        System.out.println(user_name);
        model.addAttribute("result", apiResult);

        loginSession = new LoginSession(user_id, user_name, "naver");
        session.setAttribute("loginSession", loginSession);
        
        model.addAttribute("result", apiResult);
        return "login_success";
    }


    //토큰발급
    public String getAccessToken (String authorize_code) {
        String access_Token = "";
        String refresh_Token = "";
        String reqURL = "https://kauth.kakao.com/oauth/token";

        try {
            URL url = new URL(reqURL);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //  URL연결은 입출력에 사용 될 수 있고, POST 혹은 PUT 요청을 하려면 setDoOutput을 true로 설정해야함.
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            //	POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=7b08064aebeb5d53440182eabe007d83");  //본인이 발급받은 key
            sb.append("&redirect_uri=http://localhost:8080/Z/login/oauth_kakao");     // 본인이 설정해 놓은 경로
            sb.append("&code=" + authorize_code);
            bw.write(sb.toString());
            bw.flush();

            //    결과 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);

            //    요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println("response body : " + result);

            //    Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            access_Token = element.getAsJsonObject().get("access_token").getAsString();
            refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();

            System.out.println("access_token : " + access_Token);
            System.out.println("refresh_token : " + refresh_Token);

            br.close();
            bw.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return access_Token;
    }


    // 카카오 연동정보 조회
    @RequestMapping(value = "/login/oauth_kakao")
    public String oauthKakao(@RequestParam(value = "code", required = false) String code, Model model) throws Exception {
        System.out.println("#########" + code);
        String access_Token = getAccessToken(code);
        System.out.println("###access_Token#### : " + access_Token);

        HashMap<String, Object> userInfo = getUserInfo(access_Token);
        System.out.println("###access_Token#### : " + access_Token);
        System.out.println("###userInfo#### : " + userInfo.get("account_email"));
        System.out.println("###nickname#### : " + userInfo.get("profile_nickname"));
        System.out.println("###gender#### : " + userInfo.get("gender"));

        JSONObject kakaoInfo =  new JSONObject(userInfo);
        model.addAttribute("kakaoInfo", kakaoInfo);

        return "/login_success"; //본인 원하는 경로 설정
    }


    //유저정보조회
    public HashMap<String, Object> getUserInfo (String access_Token) {

        //    요청하는 클라이언트마다 가진 정보가 다를 수 있기에 HashMap타입으로 선언
        HashMap<String, Object> userInfo = new HashMap<String, Object>();
        String reqURL = "https://kapi.kakao.com/v2/user/me";
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            //    요청에 필요한 Header에 포함될 내용
            conn.setRequestProperty("Authorization", "Bearer " + access_Token);

            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println("response body : " + result);

            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
            JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();

            String nickname = properties.getAsJsonObject().get("profile_nickname").getAsString();
            String img = properties.getAsJsonObject().get("profile_image").getAsString();
            String email = kakao_account.getAsJsonObject().get("account_email").getAsString();
            String gender = kakao_account.getAsJsonObject().get("gender").getAsString();
            String birthday = kakao_account.getAsJsonObject().get("birthday").getAsString();

            userInfo.put("accessToken", access_Token);
            userInfo.put("nickname", nickname);
            userInfo.put("email", email);
            userInfo.put("gender", gender);
            userInfo.put("img", img);
            userInfo.put("birthday", birthday);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return userInfo;
    }

}
