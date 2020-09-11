package org.johnny.blogsfront.security;

import org.johnny.blogsfront.security.filter.JwtTokenFilter;
import org.johnny.blogsfront.social.CustomSpringSocialConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * SpringSecurity的配置
 *
 * @author johnny
 * @create 2019-12-21 下午1:09
 **/
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 添加Social 配置  就是注入 CustomSpringSocialConfigurer
     */
    @Autowired
    private CustomSpringSocialConfigurer springSocialConfigurer;


    @Autowired
    private JwtTokenFilter jwtTokenFilter;


    @Override
    protected void configure(HttpSecurity http) throws Exception {


        http
                //addFilterBefore(sendToFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
                //扩展 SocialAuthenticationFilter 的 登录成功和失败的handler ，失败处理器 用于返回前端 authUrl 用于跳转到认证
                .apply(springSocialConfigurer)
                .and()
                .logout()
                .logoutUrl("/signOut")
                .logoutSuccessHandler(new SocialLogoutSuccessHandler())
                .deleteCookies("JESSIONID")
                .and()
                .authorizeRequests()
                .antMatchers("/login/*").authenticated()
                .anyRequest().permitAll()
                .and().cors()
                .configurationSource(corsConfigurationSource())
                .and()
                .csrf().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
    }



    private CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        //同源配置，*表示任何请求都视为同源，若需指定ip和端口可以改为如“localhost：8080”，多个以“，”分隔；
        corsConfiguration.addAllowedOrigin("*");
        //header，允许哪些header，本案中使用的是token，此处可将*替换为token；
        corsConfiguration.addAllowedHeader("*");
        //允许的请求方法，PSOT、GET等
        corsConfiguration.addAllowedMethod("*");
        //配置允许跨域访问的url
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }


}