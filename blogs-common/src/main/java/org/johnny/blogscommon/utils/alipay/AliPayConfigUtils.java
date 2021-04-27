package org.johnny.blogscommon.utils.alipay;

import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.kernel.Config;

/**
 * @author johnny
 * @create 2021-04-26 1:03 上午
 **/
public class AliPayConfigUtils {

    private static Config config = new Config();


    public static void initConfig() {
        config.protocol = "https";
        config.gatewayHost = "openapi.alipay.com";
        config.signType = "RSA2";

        config.appId = "2021002141616393";

        // 为避免私钥随源码泄露，推荐从文件中读取私钥字符串而不是写入源码中
        config.merchantPrivateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC2JyOoPWXk+HhV1ANrk6TZ/nddiuteKJw01gNUOir73R1WGQwXxb9N+n79xeWuTkT8VahZyJuUhXiJPDR7RBRiP/JqvThaE28uHsb+FJQRSecfktYTQlCVvrlJ29lrf01vwC+iOgUZmKABwxU5Yx83Qbq/A4R/WUWIRlhI3H659yGLqY3hhw40GyKtCDDR7uhPiyyelN8v0RciQ23lu1V+cZI4JzCF+PeZJ1cbTWx1YNmp0YnAQVpdaO8WQ8DMBM8TV0NzU/ID7cBrSpZr47eOcJIkhUwx8u0mdIftHs/EaUFatGdskZlZwAgP0PbngnxavQoni2iSegS/WGs8GYNJAgMBAAECggEAeNCE6fWvO6tBxUuV6ShIUv5glX6qlg1Bbhn0o0zhbaQ+7Pg3+RyDwmi/CfrUWEQ5pVP7EXHpHx1X0U5JUOjazD+1ve47DiONvn1TZvqnKsBlt59gte1qHagepUpYbuBEsDY8YdExyMRefEy0Kxf/gFrsTf07SGwhnj1qGQmGVDjiQjatr2ImCZEkbSE/ny/KiBdXOW/2XSGQ8hyGb2YCRHbJ5+Q2hhHDrWHPxv22vUPYUyy9PVjrPXTjSU3QYDXGzj5QOzd2LtmOLyhW9mMUgBRA5D11o2a49XW+itmjzs3KVj/2u0CGIiB+bKCSz78yaYDRLtB4nX0EgOzucefwMQKBgQDsGMLExMZOiLIDTBukL3YYh6K70T9bAwpCufaRhBpUt7/MYDK8xNkypxzIukyxUWbwiJ3gmzJQzWD+z8B5ifWjXoQItNgLIVUHh3JRQrHJRMJODXlfgsPy/sSWyj03gSC6rIz+PNnnXCbgDaATcbSqz9q/6Hb6vMJtiNBATRkRuwKBgQDFgjVZxGO2J2wmVpiP7h5jOnUVUtDjb2vio1myTtnWbbwfy0rAuiA05WHOeWxa9gYUn2q8YQYI76aACMkX0yM5WJc2uvPBJ+eKK9JkT78qOSONqEnkt39IY3vnPRN/YihpEXWFn/9NVkzxv7eqJIrI2HyxqpAuJa95oweSGVgcywKBgAd6kVDiWtXKBrmMA6QjzL3iLgl9C2C6q+yJL4JUiPD+ERlMQs8v/SFZodPJ8vv69CPScA3ZXLKqjtQDiLlQ4XMXX19XOzC/EY53f+uLDU6u2ujWDG42GjbsUxXjuiSz9nFzXdP/4XcuuByI0fVLuMlgZDGID5zJte76Un528sBZAoGBAL0SwyPmH1Y3Mw8Ygv/2Ad0YDxGUsCepeGwD2FAbr+nYLul44VzSURaSKClSj+ob7wI7AiDH+NEBKjETN/64vtWc+1UHb2WPX9Yj0Ur6FJTFEkX/vo1XJGAV7H0XON0PCIRclFaRLgPJA+vBjn8wcDOJGwWxtEiZBvy4lvzDilg1AoGAOIzJIiMqA6gUXQbBtJWiEuXkbUmhq5OPrSgQ+L0mXKEwviKqamuSz4QeqC8h1pBzL2729Jlw0BH3PfaIBOX/ZF01RKWdCwY4TJV3BirIdLaHKeIenevzz15U4rau4AgenRB2RZ0b8I86LGyPHCWJVPg46ckP7dJ89peI2likle0=";

        //注：证书文件路径支持设置为文件系统中的路径或CLASS_PATH中的路径，优先从文件系统中加载，加载失败后会继续尝试从CLASS_PATH中加载
        //config.merchantCertPath = "<-- 请填写您的应用公钥证书文件路径，例如：/foo/appCertPublicKey_2019051064521003.crt -->";
        //config.alipayCertPath = "<-- 请填写您的支付宝公钥证书文件路径，例如：/foo/alipayCertPublicKey_RSA2.crt -->";
        //config.alipayRootCertPath = "<-- 请填写您的支付宝根证书文件路径，例如：/foo/alipayRootCert.crt -->";

        //注：如果采用非证书模式，则无需赋值上面的三个证书路径，改为赋值如下的支付宝公钥字符串即可
        config.alipayPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAwgpONi9JTpwK3/v9lBVRIDN8fhGEam4ALoMar2zlWiyZY+lPmb+nYhmSx8kAjYlwsMmxnnG1U/Vxhpdx32BV9zNKLGRoTGbYJBuiGmbtpV3udA1CC97J/7C4MYypwxW9xz55gY7sqmDrHG1RCYwD+BEqfFLGnmz5dzMzOoZ3N8mk+f4Kwo5Dw1cbE3WExCTeJqecvu0CIddUx8NhHWt/XAKWklWzFLC3sTnKrAWT1Aa2whusuNkm8RHRZG/y92MeUWP6iNPHfaMxJqgJFbjEjAmV89XKO708lh6XF1S+pV36+Oh91rnp3qoRHqBzi38DC6Y6rd6qgtm+7r2UGMrjMwIDAQAB";

        //可设置异步通知接收服务地址（可选）
        config.notifyUrl = "https://www.askajohnny.com/blogs/alipay/callback";

        //可设置AES密钥，调用AES加解密相关接口时需要（可选）
        config.encryptKey = "";
    }

    public static Config getOptions() {
        return config;
    }
}