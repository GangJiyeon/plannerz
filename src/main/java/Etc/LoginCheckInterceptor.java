package Etc;

import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        HttpSession session = request.getSession(false);
        if(session != null){
            Object loginSession = session.getAttribute("loginSession");
            if(loginSession != null){
                return true;
            }
        }

        response.sendRedirect(request.getContextPath() + "/login");

        return false;
    }
}
