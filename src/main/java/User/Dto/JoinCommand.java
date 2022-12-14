package User.Dto;
import java.util.Date;

public class JoinCommand {

    private String user_id;
    private String user_pw;
    private String pw_check;
    private String user_name;
    private String sns;
    private Date user_birth;
    private String job;
    private String img;
    private String phone;
    private String email;
    private String nickname;

    private String birth;

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public JoinCommand() {
    }

    public JoinCommand(String user_id, String user_pw, String pw_check, String user_name, String sns, Date user_birth, String job, String img, String phone, String email, String nickname) {
        this.user_id = user_id;
        this.user_pw = user_pw;
        this.pw_check = pw_check;
        this.user_name = user_name;
        this.sns = sns;
        this.user_birth = user_birth;
        this.job = job;
        this.img = img;
        this.phone = phone;
        this.email = email;
        this.nickname = nickname;
    }

    public JoinCommand(String user_id, String user_pw, String user_name, String sns, Date birth, String job, String img, String phone) {
        this.user_id = user_id;
        this.user_pw = user_pw;
        this.user_name = user_name;
        this.sns = sns;
        this.user_birth = birth;
        this.job = job;
        this.img = img;
        this.phone = phone;
    }

    public String getPw_check() {
        return pw_check;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setPw_check(String pw_check) {
        this.pw_check = pw_check;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_pw() {
        return user_pw;
    }

    public void setUser_pw(String user_pw) {
        this.user_pw = user_pw;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getSns() {
        return sns;
    }

    public void setSns(String sns) {
        this.sns = sns;
    }


    public Date getUser_birth() {
        return user_birth;
    }

    public void setUser_birth(Date user_birth) {
        this.user_birth = user_birth;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
