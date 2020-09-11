package org.johnny.blogscommon.vo.social.github;

import lombok.Data;

/**
 * @author johnny
 * @create 2019-12-25 下午10:26
 **/
@Data
public class GithubUserInfo {


    /**
     * login : Diamondtest
     * id : 28478049
     * avatar_url : https://avatars0.githubusercontent.com/u/28478049?v=3
     * gravatar_id :
     * url : https://api.github.com/users/Diamondtest
     * html_url : https://github.com/Diamondtest
     * followers_url : https://api.github.com/users/Diamondtest/followers
     * following_url : https://api.github.com/users/Diamondtest/following{/other_user}
     * gists_url : https://api.github.com/users/Diamondtest/gists{/gist_id}
     * starred_url : https://api.github.com/users/Diamondtest/starred{/owner}{/repo}
     * subscriptions_url : https://api.github.com/users/Diamondtest/subscriptions
     * organizations_url : https://api.github.com/users/Diamondtest/orgs
     * repos_url : https://api.github.com/users/Diamondtest/repos
     * events_url : https://api.github.com/users/Diamondtest/events{/privacy}
     * received_events_url : https://api.github.com/users/Diamondtest/received_events
     * type : User
     * site_admin : false
     * name : null
     * company : null
     * blog :
     * location : null
     * email : null
     * hireable : null
     * bio : null
     * public_repos : 0
     * public_gists : 0
     * followers : 0
     * following : 0
     * created_at : 2017-05-06T08:08:09Z
     * updated_at : 2017-05-06T08:16:22Z
     */

    private String login;
    private int id;
    private String avatar_url;
    private String gravatar_id;
    private String url;
    private String html_url;
    private String followers_url;
    private String following_url;
    private String gists_url;
    private String starred_url;
    private String subscriptions_url;
    private String organizations_url;
    private String repos_url;
    private String events_url;
    private String received_events_url;
    private String type;
    private boolean site_admin;
    private Object name;
    private Object company;
    private String blog;
    private Object location;
    private Object email;
    private Object hireable;
    private Object bio;
    private int public_repos;
    private int public_gists;
    private int followers;
    private int following;
    private String created_at;
    private String updated_at;

}