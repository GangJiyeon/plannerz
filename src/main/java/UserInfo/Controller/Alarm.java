package UserInfo.Controller;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
public class Alarm {

    @Scheduled(cron="0 0 0/1 * * *")
    public void sendMessage() {

    }
}
