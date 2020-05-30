package com.billsystem.interceptor;

import com.billsystem.pojo.User;
import com.billsystem.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        String contextPath=session.getServletContext().getContextPath();
        String[] requireAuthPages = new String[]{
                "index"
        };

        String uri = request.getRequestURI();

        uri = StringUtils.remove(uri, contextPath+"/");
        String page = uri;
        //System.out.println(page);

        if(begingWith(page, requireAuthPages)){
            //System.out.println(page);
            int id = getLastUrlId(page);
            //System.out.println(id);
            User user = userService.queryUserById(id);
            if(user != null && session.getAttribute(user.getName()) != null){
                return true;
            }else{
                response.sendRedirect("/login");
            }
        }
        return true;
    }

    private int getLastUrlId(String uri){
        char[] cs = uri.toCharArray();
        int id = 0;
        for(int i = 5; i < cs.length; i++){
            if(Character.isDigit(cs[i])){
                id += id * 10 + (cs[i] - '0');
            }else{
                break;
            }
        }
        return id;
    }

    private boolean begingWith(String page, String[] requiredAuthPages) {
        boolean result = false;
        for (String requiredAuthPage : requiredAuthPages) {
            if(StringUtils.startsWith(page, requiredAuthPage)) {
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
