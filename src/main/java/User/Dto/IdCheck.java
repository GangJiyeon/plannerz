package User.Dto;

public class IdCheck {

    private String user_id;

    private Boolean id_nonexistent;

    public IdCheck() {
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public Boolean getId_exist() {
        return id_nonexistent;
    }

    public void setId_exist(Boolean id_notexist) {
        this.id_nonexistent = id_notexist;
    }
}
