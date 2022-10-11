package User.Controller;

import User.Dto.*;
import User.Service.JoinService;
import User.Validator.LoginCommandValidator;
import User.Service.LoginService;
import UserInfo.Service.AlarmService;
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
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/** 로그인 관련 컨트롤러
 1. 뷰 페이지 보여주기 : 로그인
 2. 기능 : 직접 입력 로그인, 카카오 로그인, 네이버 로그인
 **/
@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private AlarmService alarmService;
    @Autowired
    private JoinService joinService;

    public void setLoginService(LoginService loginService, NaverLoginBO naverLoginBO, JoinService joinService) {
        this.loginService = loginService;
        this.naverLoginBO = naverLoginBO;
        this.joinService = joinService;
    }

    public void setAlarmService(AlarmService alarmService) {
        this.alarmService = alarmService;
    }

    @Autowired
    private NaverLoginBO naverLoginBO;
    private String apiResult = null;


    //로그인 뷰 보여주기
    @GetMapping("/login")
    public String login(Model model, HttpSession session, LoginCommand loginCommand,
                        @CookieValue(value = "remember_me", required = false) Cookie cookie, HttpServletRequest request) throws Exception {

        model.addAttribute("loginCommand", new LoginCommand());

        /* 네이버아이디로 인증 URL을 생성하기 위하여 naverLoginBO클래스의 getAuthorizationUrl메소드 호출 */
        String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session);

        //https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=sE***************&
        //redirect_uri=http%3A%2F%2F211.63.89.90%3A8090%2Flogin_project%2Fcallback&state=e68c269c-5ba9-4c31-85da-54c16c658125
        System.out.println("네이버:" + naverAuthUrl);

        // 네이버
        model.addAttribute("url", naverAuthUrl);

        if (cookie != null) {
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
    public String selfLogin(@ModelAttribute("loginCommand") LoginCommand loginCommand,
                            Errors errors, HttpSession session,
                            HttpServletResponse response) {

        //입력값 검증
        new LoginCommandValidator().validate(loginCommand, errors);

        if (errors.hasErrors()) {
            return "login";
        }


        UserInfo userInfo = loginService.loginService(loginCommand);
        //등록된 계정(아이디, 비밀번호로 userInfo가 조회될 경우)인 경우
        if (userInfo != null) {
            //세션에 저장
            LoginSession loginSession = new LoginSession(userInfo.getUser_id(), userInfo.getUser_name(), userInfo.getSns(), userInfo.getImg());
            session.setAttribute("loginSession", loginSession);
        }
        //등록되지 않은 계정인 경우
        else {
            errors.reject("ioPasswordNotMatching");
            return "login";
        }

        //아이디 기억하기
        Cookie rememberCookie = new Cookie("remember_me", loginCommand.getUser_id());
        rememberCookie.setPath("/");

        if (loginCommand.getRemember_me()) {
            rememberCookie.setMaxAge(60 * 60 * 24 * 30);
        } else {
            rememberCookie.setMaxAge(0);
        }
        response.addCookie(rememberCookie);
        return "login_success";


    }

    //네이버 로그인 성공시 callback호출 메소드
    @RequestMapping(value = "/login_success", method = {RequestMethod.GET, RequestMethod.POST})
    public String callback(Model model,
                           @RequestParam("code") String code,
                           @RequestParam("state") String state, HttpSession session,
                           LoginSession loginSession)
            throws IOException, ParseException, IOException, java.text.ParseException {

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

        String user_id = (String) response_obj.get("id");
        String user_name = (String) response_obj.get("name");
        String birthday = (String) response_obj.get("birthday");
        String birthyear = (String) response_obj.get("birthyear");
        String img = (String) response_obj.get("profile_image");
        String phone = (String) response_obj.get("mobile");
        phone = phone.replace("-", "");
        String email = (String) response_obj.get("email");
        String nickname = (String) response_obj.get("nickname");
        String sns = "naver";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String user_birth = birthyear + "-" + birthday;
        Date birth = formatter.parse(user_birth);

        //4. db 조회 및 처리
        //SNS_ACCOUNT_TB 조회 후 등록된 계정이면 => USER_TB의 사용자 정보를 가져옴
        //                     등록된 계정이 아니라면 => 랜덤 id 생성 후 SNS_ACCOUNT_TB 에 저장, USER_TB에 저장

        SNSAccount snsAccount = new SNSAccount();
        snsAccount.setUser_id(user_id);
        snsAccount.setSns("naver");

        //해당 sns id로 구성된 사용자 정보 SNS_ACCOUNT_TB에서 조회하기
        SNSAccount select_snsAccount = joinService.selectSNS_Account(snsAccount);

        //등록되지 않은 SNS 계정이라면
        if (select_snsAccount == null) {
            //랜덤 아이디 생성 후 SNS_ACCOUNT_TB 에 계정 등록
            SNSAccount snsAccount1 = joinService.create_random_id(snsAccount);

            //USER_TB에 사용자 정보 저장
            JoinCommand joinCommand = new JoinCommand();
            joinCommand.setUser_name(user_name);
            joinCommand.setSns(sns);
            joinCommand.setUser_pw("pw");
            joinCommand.setJob("무직");
            joinCommand.setUser_birth(birth);
            joinCommand.setImg(img);
            joinCommand.setPhone(phone);
            joinCommand.setEmail(email);
            joinCommand.setNickname(nickname);
            joinCommand.setUser_id(snsAccount1.getRandom_id());
            joinService.join(joinCommand);
            alarmService.insert(joinCommand.getUser_id());

            loginSession = new LoginSession(joinCommand.getUser_id(), user_name, sns, joinCommand.getImg());
            session.setAttribute("loginSession", loginSession);


            model.addAttribute("joinCommand", joinCommand);
            model.addAttribute("joinAgreeCommand", new JoinAgreeCommand());
            return "join3";
        } else {
            UserInfo userInfo1 = loginService.select_userInfo(select_snsAccount.getRandom_id());

            loginSession = new LoginSession(select_snsAccount.getRandom_id(), user_name, sns, userInfo1.getImg());
            session.setAttribute("loginSession", loginSession);

        }

        model.addAttribute("result", apiResult);
        return "login_success";
    }

    //<--------------------------카카오-------------------------->
    // 카카오 연동정보 조회
    @RequestMapping(value = "/login/oauth_kakao")
    public String oauthKakao(@RequestParam(value = "code", required = false) String code,
                             Model model, HttpSession session) throws Exception {

        String access_Token = getAccessToken(code);
        HashMap<String, Object> userInfo = getUserInfo(access_Token);

        String user_name = (String) userInfo.get("user_name");
        String sns = (String) userInfo.get("sns");


        //db 조회 및 처리
        //SNS_ACCOUNT_TB 조회 후 등록된 계정이면 => USER_TB의 사용자 정보를 가져옴
        //                     등록된 계정이 아니라면 => 랜덤 id 생성 후 SNS_ACCOUNT_TB 에 저장, USER_TB에 저장

        SNSAccount snsAccount = new SNSAccount();
        snsAccount.setUser_id((String) userInfo.get("email"));
        snsAccount.setSns(sns);

        String user_id = (String) userInfo.get("email");

        //해당 sns id로 구성된 사용자 정보 SNS_ACCOUNT_TB에서 조회하기
        SNSAccount select_snsAccount = joinService.selectSNS_Account(snsAccount);

        SNSAccount snsAccount1 = null;
        //등록되지 않은 SNS 계정이라면
        if (select_snsAccount == null) {
            //랜덤 아이디 생성 후 SNS_ACCOUNT_TB 에 계정 등록
            snsAccount1 = joinService.create_random_id(snsAccount);

            JoinCommand joinCommand = new JoinCommand();
            joinCommand.setJob("무직");
            joinCommand.setUser_pw("pw");
            joinCommand.setUser_id(snsAccount1.getRandom_id());
            joinCommand.setEmail((String) userInfo.get("email"));
            joinCommand.setUser_name((String) userInfo.get("user_name"));
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String birthday = ((String) userInfo.get("birthday")).substring(0, 2);
            birthday = birthday + "-" + ((String) userInfo.get("birthday")).substring(2, 4);
            joinCommand.setSns("kakao");

            Date birth = formatter.parse("0000-" + birthday);
            joinCommand.setUser_birth(birth);
            joinCommand.setImg((String) userInfo.get("img"));

            //USER_TB에 사용자 정보 저장
            joinService.join(joinCommand);
            alarmService.insert(joinCommand.getUser_id());
            model.addAttribute("joinAgreeCommand", new JoinAgreeCommand());
            LoginSession loginSession = new LoginSession(joinCommand.getUser_id(), user_name, sns, joinCommand.getImg());
            session.setAttribute("loginSession", loginSession);

            return "join3";
        } else {
            UserInfo userInfo1 = loginService.select_userInfo(select_snsAccount.getRandom_id());
            LoginSession loginSession = new LoginSession(select_snsAccount.getRandom_id(), user_name, sns, userInfo1.getImg());
            session.setAttribute("loginSession", loginSession);

        }


        return "/login_success"; //본인 원하는 경로 설정
    }

    //토큰발급
    public String getAccessToken(String authorize_code) {
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

    //유저정보조회
    public HashMap<String, Object> getUserInfo(String access_Token) {

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

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }

            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
            JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();

            System.out.println("properties=" + properties);
            System.out.println("kakao=" + kakao_account);

            String img = properties.getAsJsonObject().get("profile_image").getAsString();
            String email = kakao_account.getAsJsonObject().get("email").getAsString();
            String birthday = kakao_account.getAsJsonObject().get("birthday").getAsString();
            String user_name = properties.getAsJsonObject().get("nickname").getAsString();

            userInfo.put("accessToken", access_Token);
            userInfo.put("user_name", user_name);
            userInfo.put("img", img);
            userInfo.put("birthday", birthday);
            userInfo.put("sns", "kakao");
            userInfo.put("email", email);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return userInfo;
    }

}
