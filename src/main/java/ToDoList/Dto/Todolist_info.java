package ToDoList.Dto;

public class Todolist_info {

    private Integer list_idx;
    private String list_title;
    private String user_id;

    public Todolist_info(Integer list_idx, String list_title, String user_id) {
        this.list_idx = list_idx;
        this.list_title = list_title;
        this.user_id = user_id;
    }

    public Todolist_info() {

    }

    public Integer getList_idx() {
        return list_idx;
    }

    public void setList_idx(Integer list_idx) {
        this.list_idx = list_idx;
    }

    public String getList_title() {
        return list_title;
    }

    public void setList_title(String list_title) {
        this.list_title = list_title;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
