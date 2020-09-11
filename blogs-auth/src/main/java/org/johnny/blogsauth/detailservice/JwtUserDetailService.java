package org.johnny.blogsauth.detailservice;

import org.johnny.blogsauth.security.SecurityUser;
import org.johnny.blogscommon.service.system.UserService;
import org.johnny.blogscommon.vo.resultvo.system.UserResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author johnny
 * @create 2020-07-18 下午2:23
 **/
@Service
public class JwtUserDetailService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SecurityUser securityUser;
        //User user;
        UserResultVo userResultVo = userService.findByUserName(username);
        if (userResultVo == null) {
            throw new UsernameNotFoundException(username);
        }
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        userResultVo.getRoles().forEach(role -> {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role);
            grantedAuthorities.add(grantedAuthority);
        });

        securityUser = new SecurityUser(userResultVo.getId(), userResultVo.getUsername(), passwordEncoder.encode(userResultVo.getPassword()), grantedAuthorities);
        return securityUser;
    }
}