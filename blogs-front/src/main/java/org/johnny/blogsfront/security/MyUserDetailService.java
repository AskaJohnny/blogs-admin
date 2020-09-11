 package org.johnny.blogsfront.security;

 import lombok.extern.slf4j.Slf4j;
 import org.johnny.blogscommon.entity.blog.BlogUserInfo;
 import org.johnny.blogscommon.repository.blog.BlogUserInfoRepository;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.security.core.authority.AuthorityUtils;
 import org.springframework.security.core.userdetails.UsernameNotFoundException;
 import org.springframework.security.crypto.password.PasswordEncoder;
 import org.springframework.social.security.SocialUserDetails;
 import org.springframework.social.security.SocialUserDetailsService;
 import org.springframework.stereotype.Component;


 /**
  * @author johnny
  * @create 2019-04-12 下午8:48
  **/
 @Component
 @Slf4j
 public class MyUserDetailService implements SocialUserDetailsService {

     @Autowired
     private BlogUserInfoRepository blogUserInfoRepository;


     @Autowired
     private PasswordEncoder passwordEncoder;

     /**
      *  userId 就是 我们业务系统的id ， 也就是 UserConnection表的  ，springsocial是通过 providerid 和 provuderUserid 两个去表里查询的
      */
     @Override
     public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {

         log.info("userconnect 登录Id : {}", userId);
         BlogUserInfo blogUserInfo = blogUserInfoRepository.findById(Long.valueOf(userId)).orElse(null);
         //模拟从数据库中 获取到 密码 根据用户名
         String bcryptPassword = passwordEncoder.encode("123456");
         CustomSocialUser customSocialUser = CustomSocialUser.builder().id(userId)
                 .username(blogUserInfo.getUserName())
                 .headImage(blogUserInfo.getHeadImage())
                 .authorities(AuthorityUtils.commaSeparatedStringToAuthorityList("admin,ROLE_USER"))
                 .build();

         //return new SocialUser(userId, bcryptPassword, AuthorityUtils.commaSeparatedStringToAuthorityList("admin,ROLE_USER"));
         return customSocialUser;
     }
 }