package Interceptors;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Description:
 *
 * @author:
 * @date: 2018-08-03 上午1:47
 **/
public class MyInterceptor2 extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        System.err.println("MyInterceptor2 preHandle....");
        PrintWriter writer=null;
        if(request.getRequestURI().endsWith("test")){
            System.err.println(request.getRequestURI());
            writer=response.getWriter();
            writer.write("false");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           @Nullable ModelAndView modelAndView) throws Exception {
        System.err.println("MyInterceptor2 postHandle....");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                @Nullable Exception ex) throws Exception {
        System.err.println("MyInterceptor2 afterCompletion....");
    }

}
