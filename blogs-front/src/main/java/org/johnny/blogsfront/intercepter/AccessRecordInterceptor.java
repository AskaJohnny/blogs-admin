package org.johnny.blogsfront.intercepter;

import com.github.jarod.qqwry.IPZone;

import lombok.extern.slf4j.Slf4j;
import org.johnny.blogscommon.entity.ipaccess.IpAccessInfo;
import org.johnny.blogscommon.utils.IpUtil;
import org.johnny.blogscommon.utils.QQWryUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 访问统计  Intercepter
 *
 * @author johnny
 * @create 2020-08-14 上午11:39
 **/
@Slf4j
public class AccessRecordInterceptor extends HandlerInterceptorAdapter {



//    /**
//     * 目前是 解析 ip 并且生成 IpAccessInfo 放入 linkedBlockingQueue 队列中去
//     *
//     * @param request
//     * @param response
//     * @param handler
//     * @return
//     * @throws Exception
//     */
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        String ip = IpUtil.getIpAddress(request);
//        log.info("【请求者 ip : {} 】", ip);
//        IPZone ipZone = QQWryUtils.getIpZoneByIp(ip);
//        log.info("【解析到 城市: {}】", ipZone.getMainInfo());
//
//        IpAccessInfo ipAccessInfo = new IpAccessInfo();
//        ipAccessInfo.setCity(ipZone.getMainInfo());
//        ipAccessInfo.setIp(ip);
//        ipAccessInfo.setOperators(ipZone.getSubInfo());
//        try {
//            IpQueue.linkedBlockingQueue.add(ipAccessInfo);
//        } catch (IllegalStateException e) {
//            log.warn("队列已满 ");
//        }
//        return true;
//    }
}