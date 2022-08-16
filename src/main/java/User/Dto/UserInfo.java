package User.Dto;

import java.util.Date;

public class UserInfo {

    private String user_id;
    private String user_pw;
    private String user_name;
    private String sns;
    private Date join_date;
    private Date birth;
    private String job;
    private String img;

    public UserInfo(String user_id, String user_pw, String user_name, String sns, Date join_date, Date birth, String job, String img) {
        this.user_id = user_id;
        this.user_pw = user_pw;
        this.user_name = user_name;
        this.sns = sns;
        this.join_date = join_date;
        this.birth = birth;
        this.job = job;
        this.img = img;
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

    public Date getJoin_date() {
        return join_date;
    }

    public void setJoin_date(Date join_date) {
        this.join_date = join_date;
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
