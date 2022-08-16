package User.Dto;
import java.util.Date;

public class JoinCommand {


    private String user_id;

    private String user_pw;

    private String pw_check;
    private String user_name;

    private String sns;


    private Date birth;

    private String job;

    private String img;


    public JoinCommand() {
    }

    public JoinCommand(String user_id, String user_pw, String user_name, String sns, Date birth, String job, String img) {
        this.user_id = user_id;
        this.user_pw = user_pw;
        this.user_name = user_name;
        this.sns = sns;
        this.birth = birth;
        this.job = job;
        this.img = img;
    }

    public String getPw_check() {
        return pw_check;
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



    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
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
