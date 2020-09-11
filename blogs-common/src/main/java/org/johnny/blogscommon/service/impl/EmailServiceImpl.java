package org.johnny.blogscommon.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.johnny.blogscommon.enums.WxSubscribeEnum;
import org.johnny.blogscommon.service.EmailService;
import org.johnny.blogscommon.service.wx.WxXmlData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


/**
 * @author johnny
 * @create 2020-08-05 下午1:07
 **/
@Service
@Slf4j
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;


    @Override
    public void sendEmail(WxXmlData wxXmlData) {
        MimeMessage mimeMessage = this.mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
        try {
            messageHelper.setFrom("626142589@qq.com");
            messageHelper.setTo("626142589@qq.com");
            if (WxSubscribeEnum.UNSUBSCRIBE.getValue().equals(wxXmlData.getEvent())) {
                messageHelper.setSubject("微信公众号 取消关注  通知!!!");
                messageHelper.setText(" 微信用户: " + wxXmlData.getFromUserName() + " 取消了关注啦！！！");
            } else if (WxSubscribeEnum.SUBSCRIBE.getValue().equals(wxXmlData.getEvent())) {
                messageHelper.setSubject("微信公众号 关注  通知!!!");
                messageHelper.setText(" 新微信用户: " + wxXmlData.getFromUserName() + " 关注啦！！！");
            }
            this.mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendEmailForLeaveWorld(String leaveWorld) {
        MimeMessage mimeMessage = this.mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
        try {
            messageHelper.setFrom("626142589@qq.com");
            messageHelper.setTo("626142589@qq.com");
            messageHelper.setSubject("博客有人留言啦!!!");
            messageHelper.setText(" 用户留言: " + leaveWorld);
            this.mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}