package User.Dto;

public class LoginCommand {

    private String user_id;
    private String user_pw;
    private boolean remember_me;

    public LoginCommand() {
    }

    public LoginCommand(String user_id, String user_pw, boolean remember_me) {
        this.user_id = user_id;
        this.user_pw = user_pw;
        this.remember_me = remember_me;
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

    public boolean getRemember_me() {
        return remember_me;
    }

    public void setRemember_me(boolean remember_me) {
        this.remember_me = remember_me;
    }
}
