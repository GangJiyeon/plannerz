package Calendar.Dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class MonthlyCommand {

    private String user_id;
    private String title;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime start_date;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime finish_date;
    private String bg_color;
    private String tx_color;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime alarm_time;

    public MonthlyCommand() {
    }

    public MonthlyCommand(String user_id, String title, LocalDateTime start_date, LocalDateTime finish_date, String bg_color, String tx_color, LocalDateTime alarm_time) {
        this.user_id = user_id;
        this.title = title;
        this.start_date = start_date;
        this.finish_date = finish_date;
        this.bg_color = bg_color;
        this.tx_color = tx_color;
        this.alarm_time = alarm_time;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDateTime start_date) {
        this.start_date = start_date;
    }

    public LocalDateTime getFinish_date() {
        return finish_date;
    }

    public void setFinish_date(LocalDateTime finish_date) {
        this.finish_date = finish_date;
    }

    public String getBg_color() {
        return bg_color;
    }

    public void setBg_color(String bg_color) {
        this.bg_color = bg_color;
    }

    public String getTx_color() {
        return tx_color;
    }

    public void setTx_color(String tx_color) {
        this.tx_color = tx_color;
    }

    public LocalDateTime getAlarm_time() {
        return alarm_time;
    }

    public void setAlarm_time(LocalDateTime alarm_time) {
        this.alarm_time = alarm_time;
    }
}
