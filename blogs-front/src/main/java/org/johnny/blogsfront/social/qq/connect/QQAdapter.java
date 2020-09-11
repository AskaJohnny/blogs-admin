package org.johnny.blogsfront.social.qq.connect;

import lombok.extern.slf4j.Slf4j;
import org.johnny.blogscommon.vo.social.qq.QQUserInfo;
import org.johnny.blogsfront.social.qq.api.QQ;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

/**
 * QQ的apdater 主要用于将QQ返回的用户信息 适配到通用的 Connection 连接里
 *
 * @author johnny
 * @create 2019-12-21 下午12:52
 **/
@Slf4j
public class QQAdapter implements ApiAdapter<QQ> {


    @Override
    public boolean test(QQ api) {
        //测试 QQ服务是否可用
        return true;
    }

    @Override
    public void setConnectionValues(QQ api, ConnectionValues values) {
        //调用QQ互联的 api接口 获取QQ的用户信息
        QQUserInfo qqUserInfo = api.getQQUserInfo();
        //将用户信息的一些特定的属性 专递给ConnectionValues 供 Connection 使用
        values.setDisplayName(qqUserInfo.getNickname());
        values.setImageUrl(qqUserInfo.getFigureurl_qq_1());
        values.setProfileUrl(null);
        values.setProviderUserId(qqUserInfo.getOpenId());
        log.info("qqUserInfo: {}", qqUserInfo);
    }

    @Override
    public UserProfile fetchUserProfile(QQ api) {
        return null;
    }

    @Override
    public void updateStatus(QQ api, String message) {

    }
}