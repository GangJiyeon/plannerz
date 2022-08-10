package Ctx;

import User.ExController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ControllerCtx {

    @Bean
    public ExController exController() {
        return new ExController();
    }
}
