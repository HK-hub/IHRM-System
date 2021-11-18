package com.ihrm.common.controller;

import com.ihrm.common.entity.Result;
import com.ihrm.common.entity.ResultCode;
import org.springframework.web.bind.annotation.*;

/**
 * @author : HK意境
 * @ClassName : ErrorController
 * @date : 2021/11/17 14:54
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@CrossOrigin
@RestController
@RequestMapping("/auth")
public class ErrorController {


    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public Result errors(@RequestParam(name = "code",required = false)int code){
       if (code == 1){
           return new Result(ResultCode.UNAUTHENTICATED) ;
       }else if (code == 2){
           return new Result(ResultCode.UNAUTHORISE) ;
       }else {
           return new Result(ResultCode.UNAUTHENTICATED) ;
       }

    }
}
