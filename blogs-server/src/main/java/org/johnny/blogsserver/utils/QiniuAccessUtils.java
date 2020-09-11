package org.johnny.blogsserver.utils;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

/**
 * 七牛云 访问工具
 *
 * @author johnny
 * @create 2019-12-03 下午2:17
 **/
public class QiniuAccessUtils {

    /**
     * 七牛AK
     */
    public static final String accessKey = "e1C2jGSQsaTBNGr7RhnXEMbueJ-hfkX2uhTPKdq7";
    /**
     * 七牛SK
     */
    public static final String secretKey = "23pb5PmhN9j4KDvAsBkS7nQ-is-TLIQ4QFZDrb0z";
    /**
     * 七牛存储空间名
     */

    public static final String bucket = "johnny-blogs";
    /**
     * 七牛默认域名 -> 切换为了 正式域名 http://cdn.askajohnny.com/
     */
    public static final String domain = "http://cdn.askajohnny.com/";


    //设置好账号的ACCESS_KEY和SECRET_KEY
    private static String ACCESS_KEY = accessKey;
    private static String SECRET_KEY = secretKey;

    //要上传的空间 //对应要上传到七牛上 你的那个路径（自己建文件夹 注意设置公开）
    private static String bucketname = bucket;

    //密钥配置
    private static Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);

    private static Configuration cfg = new Configuration(Zone.huanan());
    //创建上传对象

    private static UploadManager uploadManager = new UploadManager(cfg);

    //简单上传，使用默认策略，只需要设置上传的空间名就可以了
    public static String getUpToken() {
        return auth.uploadToken(bucketname);
    }


    public static String UploadPic(String FilePath, String FileName) {
        Configuration cfg = new Configuration(Zone.huanan());
        UploadManager uploadManager = new UploadManager(cfg);
        //AccessKey的值
        String accessKey = ACCESS_KEY;
        //SecretKey的值
        String secretKey = SECRET_KEY;
        //存储空间名
        String bucket = bucketname;
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        try {
            Response response = uploadManager.put(FilePath, FileName, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
            return domain + FileName;
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }
        return null;
    }

    public static String updateFile(MultipartFile file, String filename) throws Exception {
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        try {
            InputStream inputStream = file.getInputStream();
            ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
            byte[] buff = new byte[600]; //buff用于存放循环读取的临时数据
            int rc = 0;
            while ((rc = inputStream.read(buff, 0, 100)) > 0) {
                swapStream.write(buff, 0, rc);
            }

            byte[] uploadBytes = swapStream.toByteArray();
            try {
                Response response = uploadManager.put(uploadBytes, filename, getUpToken());
                //解析上传成功的结果
                DefaultPutRet putRet;
                putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                return domain + putRet.key;

            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                }
            }
        } catch (UnsupportedEncodingException ex) {
        }
        return null;
    }


}