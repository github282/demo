package com.cloudmusic.config;

import com.cloudmusic.bean.MyAuthenticationFailureHandler;
import com.cloudmusic.bean.MyAuthenticationSuccessHandler;
import com.cloudmusic.service.UserDetailsServiceImpl;
import com.cloudmusic.servlet.ValidateCodeFilter;
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
        this.addFilterBefore(http);
    }

    // 自定义用户授权管理
    private void authorizeRequests(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/register", "/code/img", "/css/**", "/img/**", "/musicResource/**").permitAll()
                .antMatchers("/user/admin/**", "/upload/**").hasAuthority("admin")
                .anyRequest().authenticated();
                //.and()
                //.formLogin();
    }

    @Autowired
    private ValidateCodeFilter validateCodeFilter;
    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
    @Autowired
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;
    //自定义拦截器
    private void addFilterBefore(HttpSecurity http) throws Exception {
        validateCodeFilter.setMyAuthenticationFailureHandler(myAuthenticationFailureHandler);
        http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)//在进行用户身份验证之前进行拦截
                .formLogin()
                .loginPage("/userLogin")
                .loginProcessingUrl("/userLogin")
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
                .failureUrl("/userLogin?error");
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
