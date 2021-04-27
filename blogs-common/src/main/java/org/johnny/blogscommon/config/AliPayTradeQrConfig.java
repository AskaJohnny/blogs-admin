package org.johnny.blogscommon.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author johnny
 * @create 2021-04-25 10:00 下午
 **/
@Data
@ConfigurationProperties(prefix = "qr")
public class AliPayTradeQrConfig {

    private int qrExpireMinute;

    private int qrWidth;

    private int qrHeight;

    private String qrSuffix;
}
