package com.cloudmusic.config;

import com.cloudmusic.bean.MyAuthenticationFailureHandler;
import com.cloudmusic.bean.MyAuthenticationSuccessHandler;
import com.cloudmusic.service.UserDetailsServiceImpl;
import com.cloudmusic.servlet.ValidateCodeFilter;
import com.cloudmusic.servlet.ValidateUserFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        this.userDetailAuthentication(auth);
    }

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    private void userDetailAuthentication(AuthenticationManagerBuilder auth) throws Exception{
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        this.authorizeRequests(http);
        this.formLogin(http);
        this.logout(http);
        this.rememberMe(http);
        this.addFilter(http);
    }

    // 自定义用户授权管理
    private void authorizeRequests(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/",
                        "/forgetPwd", "/sendCodeToMail",
                        "/css/**", "/img/**", "/js/**",
                        "/register",
                        "/code/img",
                        "/musicResource/**",
                        "/music/play", "/music/findByKey").permitAll()
                .antMatchers("/admin/**", "/music/upload").hasAuthority("admin")
                .anyRequest().authenticated();
        //关闭csrf防御
        http.csrf().disable();
    }

    @Autowired
    private ValidateCodeFilter validateCodeFilter;
    @Autowired
    private ValidateUserFilter validateUserFilter;
    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
    @Autowired
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;
    //自定义拦截器
    private void addFilter(HttpSecurity http) throws Exception {
        /**
         * 在用户登陆信息验证前，进行用户状态验证
         */
        validateUserFilter.setFailureHandler(myAuthenticationFailureHandler);
        http.addFilterBefore(validateUserFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin()
                .successHandler(myAuthenticationSuccessHandler)
                .failureHandler(myAuthenticationFailureHandler);

        /**
         * 在用户状态验证前，进行图形验证码验证
         */
        validateCodeFilter.setFailureHandler(myAuthenticationFailureHandler);
        http.addFilterBefore(validateCodeFilter, validateUserFilter.getClass())
                .formLogin()
                .successHandler(myAuthenticationSuccessHandler)
                .failureHandler(myAuthenticationFailureHandler);
    }

    // 自定义用户登录
    private void formLogin(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage("/userLogin").permitAll()
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/")
                .failureUrl("/userLogin");
    }

    // 自定义用户退出控制
    private void logout(HttpSecurity http) throws Exception {
        http.logout()
                .logoutSuccessUrl("/");
    }

    // 定制Remember-me记住我功能
    private void rememberMe(HttpSecurity http) throws Exception  {
        http.rememberMe()
                .rememberMeParameter("rememberme")
                .tokenValiditySeconds(3600)
                // 对cookie信息进行持久化管理
                .tokenRepository(tokenRepository());
    }

    /**
     * 持久化Token存储
     * @return
     */
    @Autowired
    private DataSource dataSource;
    @Bean
    public JdbcTokenRepositoryImpl tokenRepository(){
        JdbcTokenRepositoryImpl jr = new JdbcTokenRepositoryImpl();
        jr.setDataSource(dataSource);
        return jr;
    }

}
