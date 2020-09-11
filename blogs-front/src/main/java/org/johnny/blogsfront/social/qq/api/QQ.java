package org.johnny.blogsfront.social.qq.api;

import org.johnny.blogscommon.vo.social.qq.QQUserInfo;

/**
 * qq接口
 *
 * @author johnny
 * @create 2019-12-21 上午11:34
 **/
public interface QQ {

     QQUserInfo getExistUserInfo();

     QQUserInfo getQQUserInfo();

}