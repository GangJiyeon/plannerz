package Project.Dto;

public class Project_middle_info {

    private Integer project_middle_idx;
    private Integer project_idx;
    private String title;


    public Project_middle_info(Integer project_middle_idx, Integer project_idx, String title) {
        this.project_middle_idx = project_middle_idx;
        this.project_idx = project_idx;
        this.title = title;
    }

    public Integer getProject_middle_idx() {
        return project_middle_idx;
    }

    public void setProject_middle_idx(Integer project_middle_idx) {
        this.project_middle_idx = project_middle_idx;
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
}
