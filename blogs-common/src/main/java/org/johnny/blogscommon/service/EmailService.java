package org.johnny.blogscommon.service;


import org.johnny.blogscommon.service.wx.WxXmlData;

/**
 * @author johnny
 * @create 2020-08-05 下午1:07
 **/
public interface EmailService {

    void sendEmail(WxXmlData wxXmlData);


    void sendEmailForLeaveWorld(String leaveWorld);


}