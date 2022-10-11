package User.Controller;

import User.Dto.*;
import User.Exception.CantMakeUserInfoException;
import User.Service.JoinService;
import User.Validator.AgreeValidator;
import User.Validator.JoinValidator;
import UserInfo.Service.AlarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/** 회원가입 관련 컨트롤러
 1. 뷰 페이지 보여주기 : 회원가입 성공, 회원가입 1단계~4단계, 회원탈퇴
 2. 기능 : 본인인증, 네이버/kakao 회원가입, 아이디 중복 체크, 회원가입, 약관 동의, 회원탈퇴
 **/
@Controller
public class JoinController {

    @Autowired
    private JoinService joinService;

    @Autowired
    private AlarmService alarmService;

    public void setLoginService(JoinService joinService) {
        this.joinService = joinService;
    }

    public void setAlarmService(AlarmService alarmService){
        this.alarmService = alarmService;
    }

    @Autowired
    private NaverLoginBO naverLoginBO;
    private String apiResult = null;

    //회원가입 뷰 보여주기
    @RequestMapping("/join")
    public String join(Model model, HttpSession session){

        //<<네이버 회원가입>>
        /* 네이버아이디로 인증 URL을 생성하기 위하여 naverLoginBO클래스의 getAuthorizationUrl메소드 호출 */
        String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session);

        //https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=sE***************&
        //redirect_uri=http%3A%2F%2F211.63.89.90%3A8090%2Flogin_project%2Fcallback&state=e68c269c-5ba9-4c31-85da-54c16c658125
        System.out.println("네이버:" + naverAuthUrl);

        // 네이버
        model.addAttribute("url", naverAuthUrl);

        //<<카카오 회원가입>>
        //카카오 인가코드 발급
        System.out.println("--------- 카카오연동 들어옴 ---------");

        String reqUrl =
                "https://kauth.kakao.com/oauth/authorize"
                        + "?client_id=7b08064aebeb5d53440182eabe007d83"
                        + "&redirect_uri=http://localhost:8080/Z/login/oauth_kakao"
                        + "&response_type=code";

        model.addAttribute("reqUrl", reqUrl);
        return "join1";
    }

    //회원가입 성공 뷰 페이지 보여주기
    @RequestMapping("/success")
    public String success(){
        return "success";
    }

    //본인인증 뷰 페이지(회원가입 1단계) 보여주기
    @GetMapping("/identify")
    public String identify(){
        return "join1";
    }

    //회원가입 2단계 뷰 페이지 보여주기
    @PostMapping("/view/step2")
    public String go_step2(Model model) {
        return "join2";
    }

    //본인인증 시 입력한 이름, 생년월일, 전화번호 세션 저장
    @PostMapping("/view/step_2")
    public String view_step2_2(@RequestParam("user_name") String user_name,
                               @RequestParam("user_birth") String user_birth,
                               @RequestParam("phone") String phone,
                               Model model, HttpSession session){

        session.setAttribute("user_name", user_name);
        session.setAttribute("user_birth", user_birth);
        session.setAttribute("phone", phone);

        model.addAttribute("joinCommand", new JoinCommand());
        model.addAttribute("idCheck", new IdCheck());

        return "join2_2";
    }

    //회원가입 3단계 뷰 페이지 보여주기
    @PostMapping("/view/step3")
    public String go_step3(Model model, HttpSession session) {
        model.addAttribute("joinAgreeCommand", new JoinAgreeCommand());
        //2단계에서 입력한 본인인증 정보 세션 삭제
        session.removeAttribute("user_name");
        session.removeAttribute("user_birth");
        session.removeAttribute("phone");
        return "join3";
    }

    //회원가입 4단계 뷰 페이지 보여주기
    @PostMapping("/view/step4")
    public String go_step4() {
        return "join4";
    }

    //아이디 중복 체크
    @PostMapping("/check/id")
    public String checkId(Model model, HttpSession session, @Valid IdCheck idCheck, Errors errors) {

        //입력값 검증
        if (errors.hasErrors()) {
            return "join2_2";
        }

        //이미 등록된 아이디 여부 확인
        boolean id_notExist = joinService.checkid(idCheck.getCheck_user_id());
        idCheck.setId_not_exist(id_notExist);

        //사용 가능한 아이디이면
        if (id_notExist) {
            session.setAttribute("new_userId", idCheck.getCheck_user_id());
        //이미 등록된 아이디이면
        } else {
            errors.rejectValue("check_user_id","duplicate");
        }

        //회원가입 2단계에 필요한 커맨드 객체 전달
        model.addAttribute("joinAgreeCommand", new JoinAgreeCommand());
        model.addAttribute("joinCommand", new JoinCommand());
        return "join2_2";
    }

    //회원정보 입력
    @PostMapping("/join/input")
    public String join(Model model, JoinCommand joinCommand, Errors errors, HttpSession session,
                       @RequestParam(required = false, value = "file") MultipartFile file) throws ParseException {

        model.addAttribute("idCheck", new IdCheck());

        //DB에 전송할 회원정보 재구성하기
        String birth = joinCommand.getBirth();
        String year = birth.substring(0,4);
        String month = birth.substring(4,6);
        String day = birth.substring(6);
        birth = year + "-" + month + "-" + day;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date user_birth = format.parse(birth);
        joinCommand.setUser_birth(user_birth);

        //입력값 검증하기
        new JoinValidator().validate(joinCommand, errors);

        joinCommand.setUser_id((String) session.getAttribute("new_userId"));
        joinCommand.setSns("none");

        if(errors.hasErrors()){
            return "join2_2";
        }

        try{

            //이미지를 저장할 서버의 경로
            String uploadFolder = "/Users/gangjiyeon/IdeaProjects/Z/src/main/webapp/img/user";

            //사용자가 업로드한 파일리 존재한다면
            if (!file.isEmpty()) {
                String fileRealName = file.getOriginalFilename(); //파일명을 얻어낼 수 있는 메서드!
                long size = file.getSize(); //파일 사이즈

                //서버에 저장할 파일이름 fileextension으로 .jsp이런식의  확장자 명을 구함
                String fileExtension = fileRealName.substring(fileRealName.lastIndexOf("."), fileRealName.length());

                //고유한 랜덤 문자를 통해 db와 서버에 저장할 파일명을 새롭게 만들기
                UUID uuid = UUID.randomUUID();
                String[] uuids = uuid.toString().split("-");

                String uniqueName = uuids[0];

                // File saveFile = new File(uploadFolder+"\\"+fileRealName); uuid 적용 전
                File saveFile = new File(uploadFolder + "//" + uniqueName + fileExtension);  // 적용 후
                try {
                    file.transferTo(saveFile); // 실제 파일 저장메서드(filewriter 작업을 손쉽게 한방에 처리해준다.)
                    String savedFile = saveFile.toString();
                    joinCommand.setImg(uniqueName + fileExtension);

                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            //파일을 업로드 하지 않았다면
            }else {
                //기본이미지 등록
                joinCommand.setImg("회원.jpeg");
            }

            //아이디 체크 시 저장한 아이디 세션 삭제
            session.removeAttribute("user_id");

            //회원정보 등록
            joinService.join(joinCommand);

            //알림정보 등록
            alarmService.insert(joinCommand.getUser_id());

            //약관동의에 필요한 커맨드 객체 생성
            model.addAttribute("joinAgreeCommand", new JoinAgreeCommand());
            return "join3";

        }catch (CantMakeUserInfoException e){
            return "join2_2";
        }
    }

    //동의 항목 체크
    @PostMapping("/check/agree")
    public String checkAgree(JoinAgreeCommand joinAgreeCommand, Errors errors){

        new AgreeValidator().validate(joinAgreeCommand, errors);
        if(errors.hasErrors()){
            return "join3";
        }
        return "join4";
    }

    //회원정보 수정
    @PostMapping("/update/userinfo")
    public String update_userinfo(UserInfo userInfo){
        return "userinfo";
    }

    //회원탈퇴 뷰 보여주기
    @GetMapping("/delete/user")
    public String delete_view(){
        return "delete";
    }

    //회원탈퇴
    @PostMapping("/delete/user.do")
    public String delete(HttpSession session, @RequestParam(value = "pw", required = false)String pw){
        if (pw == null){
            pw="pw";
        }
        LoginSession loginSession = (LoginSession) session.getAttribute("loginSession");
        joinService.deleteUserInfo(loginSession.getUser_id(), pw);

        session.removeAttribute("loginSession");
        return "home";
    }
}
