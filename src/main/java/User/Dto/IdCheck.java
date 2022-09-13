package User.Dto;

import javax.validation.constraints.NotBlank;

public class IdCheck {

    @NotBlank
    private String check_user_id;

    private Boolean id_not_exist;

    public IdCheck() {
    }

    public IdCheck(String check_user_id, Boolean id_not_exist) {
        this.check_user_id = check_user_id;
        this.id_not_exist = id_not_exist;
    }


    public String getCheck_user_id() {
        return check_user_id;
    }

    public void setCheck_user_id(String check_user_id) {
        this.check_user_id = check_user_id;
    }

    public Boolean getId_not_exist() {
        return id_not_exist;
    }

    public void setId_not_exist(Boolean id_not_exist) {
        this.id_not_exist = id_not_exist;
    }


}
