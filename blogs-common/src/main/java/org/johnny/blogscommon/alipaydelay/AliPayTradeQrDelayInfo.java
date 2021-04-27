package org.johnny.blogscommon.alipaydelay;

import lombok.Data;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 二维码
 *
 * @author johnny
 * @create 2021-04-26 4:46 下午
 **/
@Data
public class AliPayTradeQrDelayInfo implements Delayed {

    private String outTradeNo;
    private long expireTime;


    public AliPayTradeQrDelayInfo(String outTradeNo, long expireTime) {
        this.outTradeNo = outTradeNo;
        this.expireTime = expireTime;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        //判断expireTime是否大于当前系统时间，并将结果转换成MILLISECONDS
        long diffTime = expireTime - System.currentTimeMillis();
        return unit.convert(diffTime, TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        //compareTo用在AliPayTradeQrDelayInfo的排序
        return (int) (this.getExpireTime() - ((AliPayTradeQrDelayInfo) o).getExpireTime());
    }
}