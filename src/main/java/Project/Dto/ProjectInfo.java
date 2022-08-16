package Project.Dto;

import java.util.Date;

public class ProjectInfo {

    private Integer project_idx;
    private String title;
    private Date target_date;

    private String user_id;
    public ProjectInfo(Integer project_idx, String title, Date target_date, String user_id) {
        this.project_idx = project_idx;
        this.title = title;
        this.target_date = target_date;
        this.user_id = user_id;
    }

    public Integer getProject_idx() {
        return project_idx;
    }

    public void setProject_idx(Integer project_idx) {
        this.project_idx = project_idx;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getTarget_date() {
        return target_date;
    }

    public void setTarget_date(Date target_date) {
        this.target_date = target_date;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
