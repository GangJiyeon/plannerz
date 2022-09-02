package Ctx;

import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

public class FileCtx {

    @Bean
    public CommonsMultipartResolver multipartResolver(){
        CommonsMultipartResolver mr = new CommonsMultipartResolver();
        mr.setMaxInMemorySize(52428800);
        mr.setDefaultEncoding("utf-8");
        return mr;
    }
}
