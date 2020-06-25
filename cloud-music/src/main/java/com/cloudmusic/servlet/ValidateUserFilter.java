package com.cloudmusic.servlet;

import com.cloudmusic.bean.MyAuthenticationFailureHandler;
import com.cloudmusic.domian.User;
import com.cloudmusic.entity.user.ValidateUserException;
import com.cloudmusic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class ValidateUserFilter extends OncePerRequestFilter {

    private MyAuthenticationFailureHandler failureHandler;

    public MyAuthenticationFailureHandler getFailureHandler() {
        return failureHandler;
    }

    public void setFailureHandler(MyAuthenticationFailureHandler failureHandler) {
        this.failureHandler = failureHandler;
    }

    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        if (request.getRequestURI().equals("/userLogin") && request.getMethod().equals("POST")){
            String username = request.getParameter("username");
            User user = null;
            try {
                user = userService.findByUsername(username);
                validate(user);
            }catch (ValidateUserException e){
                failureHandler.onAuthenticationFailure(request, response, e);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    private void validate(User user){
        if (user.getValid()!=1){
            throw new ValidateUserException("用户未激活");
        }
    }
}
