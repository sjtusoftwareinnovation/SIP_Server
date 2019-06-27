package cn.edu.sjtu.sip_server.interceptor;

import cn.edu.sjtu.sip_server.constant.Const;
import cn.edu.sjtu.sip_server.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;


@Slf4j
@Component
public class SessionInterceptor extends HandlerInterceptorAdapter {
    @Value("${sipserver.runtime}")
    private boolean runtime;
    @Value("${sipserver.email}")
    private String email;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        Access access = method.getAnnotation(Access.class);
        HttpSession session = request.getSession();
        log.info("session id is " + session.getId());
        if (access == null) {
            // 如果注解为null, 说明不需要拦截, 直接放过
            return true;
        }
        User user = (User) session.getAttribute(Const.USER_KEY);
        if (access.roles().length > 0) {
            int[] roles = access.roles();
            Set<Integer> roleSet = new HashSet<>();
            for (int role : roles) {
                roleSet.add(role);
            }
            if (user != null && roleSet.contains(user.getRole())) {
                log.info("Valid authority", access.roles());
                return true;
            }
        }
        log.error("Invalid authority", access.roles());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {

    }

}
