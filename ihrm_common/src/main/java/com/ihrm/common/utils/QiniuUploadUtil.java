package com.ihrm.common.utils;

import com.google.gson.Gson;
import com.qiniu.cdn.CdnManager;
import com.qiniu.cdn.CdnResult;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;

/**
 * @ClassName : QiniuUploadUtil
 * @author : HK意境
 * @date : 2021/11/20
 * @description : 七牛云上传文件，工具类
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public class QiniuUploadUtil {

    private static final String accessKey = "LO43mP7ZxzuImH4783qGoBbGlbz8XSUFDmAneY6L";
    private static final String secretKey = "6FuYrCQr-X1Vju3J_tlFDnNRiwFtpNogTNPKAtiu";
    private static final String bucket = "ihrm-application";
    private static final String prix = "http://r2v2ewwih.hd-bkt.clouddn.com/";

    private UploadManager manager;

    public QiniuUploadUtil() {
        //初始化基本配置
        Configuration cfg = new Configuration(Zone.huadong());
        //创建上传管理器
        manager = new UploadManager(cfg);
    }

	//文件名 = key
	//文件的byte数组
    public String upload(String imgName , byte [] bytes) {

        Auth auth = Auth.create(accessKey, secretKey);
        //构造覆盖上传token
        String upToken = auth.uploadToken(bucket,imgName);
        try {
            Response response = manager.put(bytes, imgName, upToken);
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            //返回请求地址
            return prix+putRet.key+"?v="+System.currentTimeMillis();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }



    /**
     * @methodName : 删除图片
     * @author : HK意境
     * @date : 2021/11/21 21:44
     * @description :
     * @Todo :
     * @params :
         * @param : null
     * @return : null
     * @throws:
     * @Bug :
     * @Modified :
     * @Version : 1.0
     */
    public void delete(String imgName){

        Configuration cfg = new Configuration(Zone.huadong());
        Auth auth = Auth.create(accessKey, secretKey);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {

            bucketManager.delete(bucket, imgName);

        } catch (QiniuException ex) {
            //如果遇到异常，说明删除失败
            System.err.println(ex.code());
            System.err.println(ex.response.toString());
        }
    }


    /**
     * @methodName : 刷新图片
     * @author : HK意境
     * @date : 2021/11/21 21:45
     * @description :
     * @Todo :
     * @params :
         * @param : null
     * @return : null
     * @throws:
     * @Bug :
     * @Modified :
     * @Version : 1.0
     */
    public int refreshImage(String... urls){

        for (int i = 0; i < urls.length; i++) {
            String[] urlParts = urls[i].split("\\?");
            urls[i] = urlParts[0] ;
        }

        Auth auth = Auth.create(accessKey, secretKey);
        CdnManager c = new CdnManager(auth);
        int resultCode = 0 ;
        //待刷新的链接列表
        try {
            //单次方法调用刷新的链接不可以超过100个
            CdnResult.RefreshResult result = c.refreshUrls(urls);
            resultCode = result.code ;
            //获取其他的回复内容
        } catch (QiniuException e) {
            System.err.println(e.response.toString());
        }
        return resultCode ;
    }

}
