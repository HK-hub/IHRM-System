package com.ihrm.system.service;


import com.ihrm.common.utils.IdWorker;
import com.ihrm.domain.system.User;
import com.ihrm.domain.system.response.FaceLoginResult;
import com.ihrm.domain.system.response.QRCode;
import com.ihrm.system.mapper.UserDao;
import com.ihrm.system.utils.BaiduAiUtil;
import com.ihrm.system.utils.QRCodeUtil;
import org.apache.commons.codec.binary.Base64;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Qualifier;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * @author HK意境
 */
@Service
public class FaceLoginService {

    @Value("${qr.url.login}")
    private String faceLoginUrl;

    @Autowired
    private IdWorker idWorker;
    @Autowired
    private QRCodeUtil qrCodeUtil ;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private BaiduAiUtil baiduAiUtil;
    @Autowired
    private UserDao userDao ;

	//创建二维码
    public QRCode getQRCode() throws Exception {

        // 1。创建唯一标识
        String code = ""+idWorker.nextId();

        // 2. 生成二维码(url 地址)
        String content = faceLoginUrl + "?code=" + code ;
        System.out.println("二维码地址： " + content);
        String qrCode = qrCodeUtil.crateQRCode(content);

        // 3. 存入二维码状态，到 redis
        FaceLoginResult faceLoginResult = new FaceLoginResult("-1");
        // 参数： 状态对象，失效时间
        redisTemplate.boundValueOps(getCacheKey(code)).set(faceLoginResult,10 , TimeUnit.MINUTES);

        return new QRCode(code, qrCode);
    }

	//根据唯一标识，查询用户是否登录成功
    public FaceLoginResult checkQRCode(String code) {

        String key = getCacheKey(code);
        return (FaceLoginResult) redisTemplate.opsForValue().get(key);

    }

	//扫描二维码之后，使用拍摄照片进行登录
    public String loginByFace(String code, MultipartFile attachment) throws Exception {

        // 调用百度云 AI 查询用户
        String userId = baiduAiUtil.faceSearch(Base64.encodeBase64String(attachment.getBytes()));

        // 自动登录
        FaceLoginResult faceLoginResult = new FaceLoginResult("0");
        if (userId != null) {
            // 自己模拟登录

            // 查询用户对象
            User user = userDao.findById(userId).get();

            if (user != null) {
                // 获取subject
                Subject subject = SecurityUtils.getSubject();
                // 调用 login()
                subject.login(new UsernamePasswordToken(user.getMobile(), user.getPassword()));
                // 获取 token (session_id)
                String token = ""+subject.getSession().getId();

                faceLoginResult.setState("1");
                faceLoginResult.setUserId(userId);
                faceLoginResult.setToken(token);
            }
        }
        // 修改二维码状态
        redisTemplate.boundValueOps(getCacheKey(code)).set(faceLoginResult,10 , TimeUnit.MINUTES);

        return userId;
    }

	//构造缓存key
    private String getCacheKey(String code) {
        return "qrcode_" + code;
    }
}
