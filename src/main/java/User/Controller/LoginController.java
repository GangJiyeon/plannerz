package User.Controller;

import User.Dto.LoginCommand;
import User.Dto.LoginSession;
import User.Dto.NaverLoginBO;
import User.Dto.UserInfo;
import User.Exception.WrongIdPwException;
import User.Validator.LoginCommandValidator;
import User.Service.LoginService;
import com.github.scribejava.core.model.OAuth2AccessToken;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;

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
    public String login(Model model, HttpSession session){
        model.addAttribute("loginCommand", new LoginCommand());

        model.addAttribute("loginCommand", new LoginCommand());

        /* 네이버아이디로 인증 URL을 생성하기 위하여 naverLoginBO클래스의 getAuthorizationUrl메소드 호출 */
        String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session);

        //https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=sE***************&
        //redirect_uri=http%3A%2F%2F211.63.89.90%3A8090%2Flogin_project%2Fcallback&state=e68c269c-5ba9-4c31-85da-54c16c658125
        System.out.println("네이버:" + naverAuthUrl);

        // 네이버
        model.addAttribute("url", naverAuthUrl);
        return "login";
    }


    //직접 입력 로그인
    @PostMapping("/login/self")
    public String selfLogin(@ModelAttribute("loginCommand") @Valid LoginCommand loginCommand,
                            Errors errors, HttpSession session) {
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




}
