package UserInfo.Controller;

import User.Dto.LoginSession;
import User.Dto.UserInfo;
import User.Service.LoginService;
import UserInfo.Dto.AlarmInfo;
import UserInfo.Service.AlarmService;
import com.google.gson.JsonObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Calendar;
import java.util.List;

import static java.util.Calendar.getInstance;

/** 알람 관련 컨트롤러
 1. 뷰 페이지 보여주기 : 알림 설정 페이지
 2. 기능 : 알림 정보 조회, 알림 정보 수정, 메시지 보내기 메서드
 **/
@EnableScheduling
@Controller
public class AlarmController {

    @Autowired
    private AlarmService alarmService;

    @Autowired
    private LoginService loginService;

    public void setAlarm(AlarmService alarmService,LoginService loginService){
        this.alarmService = alarmService;
    }

    /* 예약 메세지 발송 기능 추가 예정
    @Scheduled(cron="0 0 8,9,10,11 * * *")
    public void sendMessage1() {

        Calendar now = getInstance();
        int hour = now.get(Calendar.HOUR);
        List<AlarmInfo> alarmInfoList = alarmService.select_write(hour);

        for (AlarmInfo nth : alarmInfoList){
            UserInfo userInfo = loginService.select_userInfo(nth.getUser_id());
            String tel = userInfo.getPhone();
            String content = "스케줄을 작성할 시간입니다. ";
            message_test(tel, content);
        }
    }

    @Scheduled(cron="0 0 0,20,21,22,23 * * *")
    public void sendMessage2() {

        Calendar now = getInstance();
        int hour = now.get(Calendar.HOUR);
        List<AlarmInfo> alarmInfoList = alarmService.select_check(hour);

        for (AlarmInfo nth : alarmInfoList){
            UserInfo userInfo = loginService.select_userInfo(nth.getUser_id());
            String tel = userInfo.getPhone();
            String content = "스케줄을 체크할 시간입니다. ";
            message_test(tel, content);
        }
    }

     */


    //메세지 전송하기
    public void message_test(String tel, String content){
        sendSMS(tel, content);
    }

    //메세지 전송에 필요한 키값 획득
    // https://api.ncloud-docs.com/docs/common-ncpapi
    private String makeSignature(String url, String timestamp, String method, String accessKey, String secretKey) throws NoSuchAlgorithmException, InvalidKeyException {
        String space = " ";                    // one space
        String newLine = "\n";                 // new line

        String message = new StringBuilder()
                .append(method)
                .append(space)
                .append(url)
                .append(newLine)
                .append(timestamp)
                .append(newLine)
                .append(accessKey)
                .toString();

        SecretKeySpec signingKey;
        String encodeBase64String;
        try {

            signingKey = new SecretKeySpec(secretKey.getBytes("UTF-8"), "HmacSHA256");
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(signingKey);
            byte[] rawHmac = mac.doFinal(message.getBytes("UTF-8"));
            encodeBase64String = Base64.getEncoder().encodeToString(rawHmac);
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            encodeBase64String = e.toString();
        }


        return encodeBase64String;
    }

    //메세지 전송
    private void sendSMS(String tel, String content) {
        String hostNameUrl = "https://sens.apigw.ntruss.com";     		// 호스트 URL
        String requestUrl= "/sms/v2/services/";                   		// 요청 URL
        String requestUrlType = "/messages";                      		// 요청 URL
        String accessKey = "--";                     	// 네이버 클라우드 플랫폼 회원에게 발급되는 개인 인증키			// Access Key : https://www.ncloud.com/mypage/manage/info > 인증키 관리 > Access Key ID
        String secretKey = "--";  // 2차 인증을 위해 서비스마다 할당되는 service secret key	// Service Key : https://www.ncloud.com/mypage/manage/info > 인증키 관리 > Access Key ID
        String serviceId = "ncp:sms:kr:292282257447:plannerz";       // 프로젝트에 할당된 SMS 서비스 ID							// service ID : https://console.ncloud.com/sens/project > Simple & ... > Project > 서비스 ID
        String method = "POST";											// 요청 method
        String timestamp = Long.toString(System.currentTimeMillis()); 	// current timestamp (epoch)
        requestUrl += serviceId + requestUrlType;
        String apiUrl = hostNameUrl + requestUrl;

        // JSON 을 활용한 body data 생성

        JSONObject bodyJson = new JSONObject();
        JSONObject toJson = new JSONObject();
        JSONArray toArr = new JSONArray();

        toJson.put("content",content);	// Optional, messages.content	개별 메시지 내용, SMS: 최대 80byte, LMS, MMS: 최대 2000byte
        toJson.put("to",tel);						// Mandatory(필수), messages.to	수신번호, -를 제외한 숫자만 입력 가능
        toArr.add(toJson);

        bodyJson.put("type","SMS");							// Madantory, 메시지 Type (SMS | LMS | MMS), (소문자 가능)
        bodyJson.put("from","--");					// Mandatory, 발신번호, 사전 등록된 발신번호만 사용 가능
        bodyJson.put("content","PLANNERZ");	// Mandatory(필수), 기본 메시지 내용, SMS: 최대 80byte, LMS, MMS: 최대 2000byte
        bodyJson.put("messages", toArr);					// Mandatory(필수), 아래 항목들 참조 (messages.XXX), 최대 1,000개

        String body = bodyJson.toString();

        System.out.println(body);

        try {
            URL url = new URL(apiUrl);

            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setUseCaches(false);
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setRequestProperty("content-type", "application/json");
            con.setRequestProperty("x-ncp-apigw-timestamp", timestamp);
            con.setRequestProperty("x-ncp-iam-access-key", accessKey);
            con.setRequestProperty("x-ncp-apigw-signature-v2", makeSignature(requestUrl, timestamp, method, accessKey, secretKey));
            con.setRequestMethod(method);
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());

            wr.write(body.getBytes());
            wr.flush();
            wr.close();

            int responseCode = con.getResponseCode();
            BufferedReader br;
            System.out.println("responseCode" +" " + responseCode);
            if(responseCode == 202) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else { // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }

            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();

            System.out.println(response.toString());

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //알람 뷰 페이지 보여주기
    @GetMapping("/alarm")
    public String alarm(Model model, HttpSession session){
        LoginSession loginSession = (LoginSession) session.getAttribute("loginSession");
        String user_id = loginSession.getUser_id();
        UserInfo userInfo = loginService.select_userInfo(user_id);
        AlarmInfo alarmInfo = alarmService.select_alarmInfo(user_id);
        model.addAttribute("selectUserInfo", userInfo);

        model.addAttribute("alarmInfo", alarmInfo);
        return "alarm";
    }

    //알람 정보 설정 수정하기
    @PostMapping("/alarm/update")
    public String update_alarm(AlarmInfo alarmInfo, HttpSession session){

        LoginSession loginSession = (LoginSession)session.getAttribute("loginSession");
        alarmInfo.setUser_id(loginSession.getUser_id());
        alarmService.update(alarmInfo);

        return "redirect:/alarm";
    }

    @GetMapping("/alarm.do")
    public String alarm_do(){

       // sendPush();
        return "alarm";
    }



}
