package org.johnny.blogsauth.security;

import org.johnny.blogsauth.voter.RoleBasedVoter;
import org.johnny.blogsauth.detailservice.JwtUserDetailService;
import org.johnny.blogsauth.entrypoint.JwtAuthenticationEntryPoint;
import org.johnny.blogsauth.filter.JwtTokenFilter;
import org.johnny.blogsauth.handler.LogOutSuccessHandler;
import org.johnny.blogsauth.voter.RoleSecurityMetadataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.UnanimousBased;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Arrays;
import java.util.List;

/**
 * @author johnny
 * @create 2020-07-18 下午8:07
 **/
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationEntryPoint customizeAuthenticationEntryPoint;

    @Autowired
    private JwtUserDetailService customizeUserDetailService;

    @Autowired
    private AuthenticationSuccessHandler loginSuccessHandler;


    @Autowired
    private LogOutSuccessHandler logOutSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler loginFailHandler;

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Autowired
    private JwtTokenFilter jwtTokenFilter;


    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private FilterInvocationSecurityMetadataSource roleSecurityMetadataSource;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customizeUserDetailService)
                .passwordEncoder(bCryptPasswordEncoder);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .formLogin()
                .loginPage("/auth/login")
                .successHandler(loginSuccessHandler)
                .failureHandler(loginFailHandler)
                .and()
                .logout()
                .logoutUrl("/user/logout")
                .clearAuthentication(true)
                .logoutSuccessHandler(logOutSuccessHandler)
                .and()
                .authorizeRequests()
                .anyRequest().authenticated()
                .accessDecisionManager(customizeAccessDecisionManager())
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(
                            O fsi) {
                        fsi.setSecurityMetadataSource(roleSecurityMetadataSource);
                        return fsi;
                    }
                })
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling().authenticationEntryPoint(customizeAuthenticationEntryPoint)
                .accessDeniedHandler(accessDeniedHandler);


        http.addFilterAfter(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

//    @Bean
//    private FilterInvocationSecurityMetadataSource mySecurityMetadataSource(FilterInvocationSecurityMetadataSource securityMetadataSource) {
//        RoleSecurityMetadataSource roleSecurityMetadataSource = new RoleSecurityMetadataSource(securityMetadataSource);
//        return roleSecurityMetadataSource;
//    }


    private AccessDecisionManager customizeAccessDecisionManager() {

        List<AccessDecisionVoter<? extends Object>> decisionVoterList
                = Arrays.asList(
//                new WebExpressionVoter(),
                new RoleBasedVoter()
        );
        return new AffirmativeBased(decisionVoterList);
    }

}