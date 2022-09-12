package User.Dto;

public class SNSAccount {

    private String sns;
    private String user_id;
    private String random_id;

    public SNSAccount() {
    }

    public SNSAccount(String sns, String user_id, String random_id) {
        this.sns = sns;
        this.user_id = user_id;
        this.random_id = random_id;
    }

    public String getSns() {
        return sns;
    }

    public void setSns(String sns) {
        this.sns = sns;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getRandom_id() {
        return random_id;
    }

    public void setRandom_id(String random_id) {
        this.random_id = random_id;
    }
}
