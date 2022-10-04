package Ctx;

import Etc.LoginCheckInterceptor;
import User.Validator.JoinValidator;
import User.Validator.User_idValidator;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
@EnableWebMvc
public class MvcCtx implements WebMvcConfigurer {



    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/WEB-INF/view/", ".jsp");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(loginCheckInterceptor())
                .addPathPatterns("/board/**","/todolist/**")
                .excludePathPatterns((""));

    }

    @Bean
    public LoginCheckInterceptor loginCheckInterceptor(){
        return new LoginCheckInterceptor();
    }
    @Bean
    public MessageSource messageSource(){
        ResourceBundleMessageSource ms = new ResourceBundleMessageSource();
        ms.setBasename("message.label_ko");
        ms.setDefaultEncoding("UTF-8");

        return ms;
    }

    @Override
    public Validator getValidator() {
        return new User_idValidator();
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        WebMvcConfigurer.super.addResourceHandlers(registry);
        registry.addResourceHandler("/image/board/**")
                .addResourceLocations("file:///Users/gangjiyeon/Desktop/포폴/plannerz/image/board/")
                .setCachePeriod(60*10*6)
                .resourceChain(true)
                .addResolver(new PathResourceResolver());
    }
}