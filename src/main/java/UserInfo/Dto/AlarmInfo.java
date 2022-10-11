package UserInfo.Dto;

public class AlarmInfo {

    private String user_id;
    private Boolean schedule_alarm;
    private Boolean write_alarm;
    private Boolean check_alarm;
    private String write_time;
    private String check_time;

    public AlarmInfo() {
    }

    public AlarmInfo(String user_id, Boolean schedule_alarm, Boolean write_alarm, Boolean check_alarm, String write_time, String check_time) {
        this.user_id = user_id;
        this.schedule_alarm = schedule_alarm;
        this.write_alarm = write_alarm;
        this.check_alarm = check_alarm;
        this.write_time = write_time;
        this.check_time = check_time;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public Boolean getSchedule_alarm() {
        return schedule_alarm;
    }

    public void setSchedule_alarm(Boolean schedule_alarm) {
        this.schedule_alarm = schedule_alarm;
    }

    public Boolean getWrite_alarm() {
        return write_alarm;
    }

    public void setWrite_alarm(Boolean write_alarm) {
        this.write_alarm = write_alarm;
    }

    public Boolean getCheck_alarm() {
        return check_alarm;
    }

    public void setCheck_alarm(Boolean check_alarm) {
        this.check_alarm = check_alarm;
    }

    public String getWrite_time() {
        return write_time;
    }

    public void setWrite_time(String write_time) {
        this.write_time = write_time;
    }

    public String getCheck_time() {
        return check_time;
    }

    public void setCheck_time(String check_time) {
        this.check_time = check_time;
    }
}
