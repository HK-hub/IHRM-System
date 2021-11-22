package com.ihrm.system.controller;

import com.ihrm.common.entity.Result;
import com.ihrm.common.entity.ResultCode;
import com.ihrm.domain.system.response.FaceLoginResult;
import com.ihrm.domain.system.response.QRCode;
import com.ihrm.system.service.FaceLoginService;
import com.ihrm.system.utils.BaiduAiUtil;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName : FaceLoginController
 * @author : HK意境
 * @date : 2021/11/21
 * @description : 刷脸登录
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@RestController
@RequestMapping("/sys/faceLogin")
public class FaceLoginController {

    @Autowired
    private FaceLoginService faceLoginService;
    @Autowired
    private BaiduAiUtil baiduAiUtil ;

    /**
     * 获取刷脸登录二维码
     */
    @RequestMapping(value = "/qrcode", method = RequestMethod.GET)
    public Result qrcode() throws Exception {
        // 获取二维码
        QRCode qrCode = faceLoginService.getQRCode();

        return new Result(ResultCode.SUCCESS,qrCode);
    }

    /**
     * 检查二维码：登录页面轮询调用此方法，根据唯一标识code判断用户登录情况
     */
    @RequestMapping(value = "/qrcode/{code}", method = RequestMethod.GET)
    public Result qrcodeCheck(@PathVariable(name = "code") String code) throws Exception {

        FaceLoginResult checkQrCodeResult = faceLoginService.checkQRCode(code);

        return new Result(ResultCode.SUCCESS, checkQrCodeResult);
    }

    /**
     * 人脸登录：根据落地页随机拍摄的面部头像进行登录
     *          根据拍摄的图片调用百度云AI进行检索查找
     */
    @RequestMapping(value = "/{code}", method = RequestMethod.POST)
    public Result loginByFace(@PathVariable(name = "code") String code,
                              @RequestParam(name = "file") MultipartFile attachment) throws Exception {
        // 人脸登录获取用户ID（不为 null 登录成功）
        String userId = faceLoginService.loginByFace(code, attachment);

        if (userId != null) {
            return new Result(ResultCode.SUCCESS,"face login success!");

        }else{
            return new Result(ResultCode.FAIL,"face login failed!");
        }

    }


    /**
     * 图像检测，判断图片中是否存在面部头像
     */
    @RequestMapping(value = "/checkFace", method = RequestMethod.POST)
    public Result checkFace(@RequestParam(name = "file") MultipartFile attachment) throws Exception {

        // 构造 dataUrl 数据
        String base64String = Base64.encodeBase64String(attachment.getBytes());

        Boolean aBoolean = baiduAiUtil.faceCheck(base64String);
        if (aBoolean){
            return new Result(ResultCode.SUCCESS,"face check success!");

        }else{
            return new Result(ResultCode.FAIL,"face check failed!");
        }
    }

}
