package com.cloudmusic.servlet;

import com.cloudmusic.controller.ImageCodeController;
import com.cloudmusic.config.MyAuthenticationFailureHandler;
import com.cloudmusic.entity.imageCode.ImageCode;
import com.cloudmusic.entity.imageCode.ValidateCodeException;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class ValidateCodeFilter extends OncePerRequestFilter {

    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;

    public MyAuthenticationFailureHandler getMyAuthenticationFailureHandler() {
        return myAuthenticationFailureHandler;
    }

    public void setMyAuthenticationFailureHandler(MyAuthenticationFailureHandler myAuthenticationFailureHandler) {
        this.myAuthenticationFailureHandler = myAuthenticationFailureHandler;
    }

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        if (request.getRequestURI().equals("/userLogin") && request.getMethod().equals("POST")){
            try {
                validate(new ServletWebRequest(request));
            }catch (ValidateCodeException e){
                myAuthenticationFailureHandler.onAuthenticationFailure(request, response, e);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    private void validate(ServletWebRequest request) throws ServletRequestBindingException {
        //从session中取出ImageCode
        ImageCode codeInSession = (ImageCode) sessionStrategy.getAttribute(request, ImageCodeController.SESSION_KEY);
        //从请求中获取参数imageCode
        String codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(),"imageCode");

        if(!StringUtils.hasText(codeInRequest)){
            throw new ValidateCodeException("验证码的值不能为空！");
        }
        if(codeInSession == null){
            throw new ValidateCodeException("验证码不存在！");
        }
        if(codeInSession.isExpired()){
            sessionStrategy.removeAttribute(request,ImageCodeController.SESSION_KEY);
            throw new ValidateCodeException("验证码已过期！");
        }
        if(!codeInSession.getCode().equals(codeInRequest)){
            throw new ValidateCodeException("验证码不正确！");
        }
        //验证完成后删除session中的ImageCode
        sessionStrategy.removeAttribute(request,ImageCodeController.SESSION_KEY);
    }
}
