package Project.Dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class ProjectCommand {

    private String title;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date target_date;
    private String user_id;

    public ProjectCommand() {
    }

    public ProjectCommand(String title, Date target_date, String user_id) {
        this.title = title;
        this.target_date = target_date;
        this.user_id = user_id;

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
