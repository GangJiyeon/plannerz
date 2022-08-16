package Project.Dto;

public class Project_item_info {

    private Integer item_idx;
    private String title;
    private Boolean done;
    private Integer middle_idx;

    public Project_item_info(Integer item_idx, String title, Boolean done, Integer middle_idx) {
        this.item_idx = item_idx;
        this.title = title;
        this.done = done;
        this.middle_idx = middle_idx;
    }

    public Integer getItem_idx() {
        return item_idx;
    }

    public void setItem_idx(Integer item_idx) {
        this.item_idx = item_idx;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public Integer getMiddle_idx() {
        return middle_idx;
    }

    public void setMiddle_idx(Integer middle_idx) {
        this.middle_idx = middle_idx;
    }
}
