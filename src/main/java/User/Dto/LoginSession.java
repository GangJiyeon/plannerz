package User.Dto;

public class LoginSession {

    private String user_id;
    private String user_name;
    private String sns;

    private String img;

    public LoginSession() {
    }

    public LoginSession(String user_id, String user_name, String sns, String img) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.sns = sns;
        this.img = img;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
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
}
