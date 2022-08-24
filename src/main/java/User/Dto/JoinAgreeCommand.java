package User.Dto;

public class JoinAgreeCommand {

    private Boolean agree1;
    private Boolean agree2;
    private Boolean agree3;

    public JoinAgreeCommand() {
    }

    public JoinAgreeCommand(Boolean agree1, Boolean agree2, Boolean agree3) {
        this.agree1 = agree1;
        this.agree2 = agree2;
        this.agree3 = agree3;
    }

    public Boolean getAgree1() {
        return agree1;
    }

    public void setAgree1(Boolean agree1) {
        this.agree1 = agree1;
    }

    public Boolean getAgree2() {
        return agree2;
    }

    public void setAgree2(Boolean agree2) {
        this.agree2 = agree2;
    }

    public Boolean getAgree3() {
        return agree3;
    }

    public void setAgree3(Boolean agree3) {
        this.agree3 = agree3;
    }
}
