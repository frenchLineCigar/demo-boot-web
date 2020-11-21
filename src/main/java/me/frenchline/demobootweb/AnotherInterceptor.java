package me.frenchline.demobootweb;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Member;

/**
 * @author swlee
 * @contact frenchline707@gmail.com
 * @since 2019-11-21
 */
public class AnotherInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle 2");
        //handler 정보 참고한 로직 구현....
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle 2");
        //modelAndView 정보 수정하는 로직 구현....
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion 2");
        //어떤 핸들러(handler)가 처리했는지, 예외(ex)가 발생했는지에 대한 정보 확인하는 로직 구현....
    }
}
