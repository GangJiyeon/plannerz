package ToDoList.Dto;

public class Todolist {

    private Integer list_idx;
    private String list_title;
    private String user_id;
    private String list_item_idx;
    private String item_title;
    private Boolean done;

    public Todolist() {
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

    public String getList_item_idx() {
        return list_item_idx;
    }

    public void setList_item_idx(String list_item_idx) {
        this.list_item_idx = list_item_idx;
    }

    public String getItem_title() {
        return item_title;
    }

    public void setItem_title(String item_title) {
        this.item_title = item_title;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }
}
