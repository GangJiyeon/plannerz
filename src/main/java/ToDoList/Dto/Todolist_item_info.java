package ToDoList.Dto;

public class Todolist_item_info {

    private Integer list_item_idx;
    private Integer list_idx;
    private String item_title;
    private Boolean done;

    public Todolist_item_info() {
    }

    public Todolist_item_info(Integer list_item_idx, Integer list_idx, String item_title, Boolean done) {
        this.list_item_idx = list_item_idx;
        this.list_idx = list_idx;
        this.item_title = item_title;
        this.done = done;
    }

    public Integer getList_item_idx() {
        return list_item_idx;
    }

    public void setList_item_idx(Integer list_item_idx) {
        this.list_item_idx = list_item_idx;
    }

    public Integer getList_idx() {
        return list_idx;
    }

    public void setList_idx(Integer list_idx) {
        this.list_idx = list_idx;
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
