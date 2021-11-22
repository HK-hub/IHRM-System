package com.ihrm.domain.system.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName : QRCode
 * @author : HK意境
 * @date : 2021/11/21
 * @description :
 * @Todo : 二维码
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QRCode implements Serializable {
    private static final long serialVersionUID = 4375387973088123002L;
    /**
     * 随机生成码
     */
    private String code;
    /**
     * Base64 二维码文件
     */
    private String file;
}
