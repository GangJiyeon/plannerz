package Project.Dto;

public class Project_middle_command {

    private String middle_title;
    private Integer project_idx;

    public Project_middle_command() {
    }

    public Project_middle_command(String middle_title, Integer project_idx) {
        this.middle_title = middle_title;
        this.project_idx = project_idx;
    }

    public String getMiddle_title() {
        return middle_title;
    }

    public void setMiddle_title(String middle_title) {
        this.middle_title = middle_title;
    }

    public Integer getProject_idx() {
        return project_idx;
    }

    public void setProject_idx(Integer project_idx) {
        this.project_idx = project_idx;
    }
}
