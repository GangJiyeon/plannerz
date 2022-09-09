package Calendar.Dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class MonthlyUpdate {

    private Integer update_monthly_idx;
    private String update_user_id;
    private String update_title;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime update_start_date;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime update_finish_date;
    private String update_bg_color;
    private String update_tx_color;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime update_alarm_time;

    public MonthlyUpdate() {
    }

    public MonthlyUpdate(Integer update_monthly_idx, String update_user_id, String update_title, LocalDateTime update_start_date, LocalDateTime update_finish_date, String update_bg_color, String update_tx_color, LocalDateTime update_alarm_time) {
        this.update_monthly_idx = update_monthly_idx;
        this.update_user_id = update_user_id;
        this.update_title = update_title;
        this.update_start_date = update_start_date;
        this.update_finish_date = update_finish_date;
        this.update_bg_color = update_bg_color;
        this.update_tx_color = update_tx_color;
        this.update_alarm_time = update_alarm_time;
    }

    public Integer getUpdate_monthly_idx() {
        return update_monthly_idx;
    }

    public void setUpdate_monthly_idx(Integer update_monthly_idx) {
        this.update_monthly_idx = update_monthly_idx;
    }

    public String getUpdate_user_id() {
        return update_user_id;
    }

    public void setUpdate_user_id(String update_user_id) {
        this.update_user_id = update_user_id;
    }

    public String getUpdate_title() {
        return update_title;
    }

    public void setUpdate_title(String update_title) {
        this.update_title = update_title;
    }

    public LocalDateTime getUpdate_start_date() {
        return update_start_date;
    }

    public void setUpdate_start_date(LocalDateTime update_start_date) {
        this.update_start_date = update_start_date;
    }

    public LocalDateTime getUpdate_finish_date() {
        return update_finish_date;
    }

    public void setUpdate_finish_date(LocalDateTime update_finish_date) {
        this.update_finish_date = update_finish_date;
    }

    public String getUpdate_bg_color() {
        return update_bg_color;
    }

    public void setUpdate_bg_color(String update_bg_color) {
        this.update_bg_color = update_bg_color;
    }

    public String getUpdate_tx_color() {
        return update_tx_color;
    }

    public void setUpdate_tx_color(String update_tx_color) {
        this.update_tx_color = update_tx_color;
    }

    public LocalDateTime getUpdate_alarm_time() {
        return update_alarm_time;
    }

    public void setUpdate_alarm_time(LocalDateTime update_alarm_time) {
        this.update_alarm_time = update_alarm_time;
    }
}
