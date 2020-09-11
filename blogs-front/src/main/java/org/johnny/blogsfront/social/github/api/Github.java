package org.johnny.blogsfront.social.github.api;

import org.johnny.blogscommon.vo.social.github.GithubUserInfo;

/**
 * @author johnny
 * @create 2019-12-25 下午10:26
 **/
public interface Github {

    GithubUserInfo getUserInfo();

    GithubUserInfo getExistUserInfo();
}