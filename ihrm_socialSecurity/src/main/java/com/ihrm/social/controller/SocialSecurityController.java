package com.ihrm.social.controller;

import com.ihrm.common.controller.BaseController;
import com.ihrm.common.entity.PageResult;
import com.ihrm.common.entity.Result;
import com.ihrm.common.entity.ResultCode;
import com.ihrm.domain.social.*;
import com.ihrm.social.feign.SystemFeignClient;
import com.ihrm.social.service.ArchiveService;
import com.ihrm.social.service.CompanySettingsService;
import com.ihrm.social.service.PaymentItemService;
import com.ihrm.social.service.UserSocialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : HK意境
 * @ClassName : SocialSecurityController
 * @date : 2021/11/24 12:52
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@CrossOrigin
@RestController
@RequestMapping("/social")
public class SocialSecurityController extends BaseController {

    @Autowired
    private CompanySettingsService companySettingsService ;
    @Autowired
    private UserSocialService userSocialService ;
    @Autowired
    private SystemFeignClient systemFeignClient ;
    @Autowired
    private PaymentItemService paymentItemService ;
    @Autowired
    private ArchiveService archiveService ;


    /**
     * @methodName :
     * @author : HK意境
     * @date : 2021/11/24 12:54
     * @description : 查询企业是否设置过社保
     * @Todo :
     * @params :
         * @param : null
     * @return : null
     * @throws:
     * @Bug :
     * @Modified :
     * @Version : 1.0
     */
    @RequestMapping(value = "/settings", method = RequestMethod.GET)
    public Result settings(){
        CompanySettings byId = companySettingsService.findById(this.companyId);

        return new Result(ResultCode.SUCCESS, byId);
    }


    /**
     * @methodName : 查询企业员工的社保信息列表
     * @author : HK意境
     * @date : 2021/11/24 15:48
     * @description :
     * @Todo :
     * @params : 
         * @param : page , pageSize
     * @return : null
     * @throws: 
     * @Bug :
     * @Modified :
     * @Version : 1.0
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Result list(@RequestBody Map<String, Object> map){

        // 获取请求参数
        Integer page = (Integer) map.get("page");
        Integer pageSize = (Integer) map.get("pageSize");

        // 调用 service ,查询当前企业的社保信息
        PageResult socialSecurityInfo = userSocialService.findAll(page, pageSize, this.companyId);

        return new Result(ResultCode.SUCCESS, socialSecurityInfo) ;
    }



    /**
     * 保存或更新用户社保
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public Result saveUserSocialSecurity(@RequestBody UserSocialSecurity uss) {
        userSocialService.save(uss);
        return new Result(ResultCode.SUCCESS);
    }



    /**
     * @methodName :
     * @author : HK意境
     * @date : 2021/11/24 16:32
     * @description : 根据用户Id 查询用户社保信息
     * @Todo : 根据id 查询用户数据，在查询社保信息，然后封装VO对象
     * @params :
     * @param : null
     * @return : null
     * @throws:
     * @Bug :
     * @Modified :
     * @Version : 1.0
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result findById(@PathVariable(name = "id")String userId){

        Map map = new HashMap<>();

        // 查询用户信息
        Object user = systemFeignClient.findById(userId).getData();
        map.put("user", user);

        // 查询用户社保信息
        UserSocialSecurity userSocialSecurity = userSocialService.findById(userId);
        map.put("userSocialSecurity", userSocialSecurity);

        return new Result(ResultCode.SUCCESS,map) ;
    }


    /**
     * 根据城市id查询参保城市的参保项目
     */
    @RequestMapping(value = "/payment_item/{id}",method = RequestMethod.GET)
    public Result findPaymentItem(@PathVariable String id) {
        List<CityPaymentItem> list = paymentItemService.findAllByCityId(id);
        return new Result(ResultCode.SUCCESS,list);
    }




    /**
     * @methodName :
     * @author : HK意境
     * @date : 2021/11/24 18:09
     * @description :
     * @Todo : 查询月份数据报表
     * @params :
         * @param : yearMonth ：查询当前年月的数据报表，
         * @param : opType : 1(当月数据)， 其他(历史数据)
     * @return : null
     * @throws:
     * @Bug :
     * @Modified :
     * @Version : 1.0
     */
    @RequestMapping(value = "/historys/{yearMonth}", method = RequestMethod.GET)
    public Result historyDetail(@PathVariable(name = "yearMonth")String yearMonth,
                                @RequestParam(name = "opType") int opType) throws Exception {

        List<ArchiveDetail> archiveDetails = new ArrayList<>();

        if (opType == 1){
            // 未归档，查询当前月的详细数据
           archiveDetails =  archiveService.getReports(yearMonth, companyId);

        }else {
            // 归档数据
            Archive archive = archiveService.findArchive(this.companyId , yearMonth);
            if (archive != null) {
                archiveDetails = archiveService.findAllDetailByArchiveId(archive.getId());
            }
        }
        return new Result(ResultCode.SUCCESS , archiveDetails);
    }




    /**
     * @methodName : 数据归档
     * @author : HK意境
     * @date : 2021/11/24 20:30
     * @description : 数据归档，覆盖数据
     * @Todo :
     * @params :
         * @param : null
     * @return : null
     * @throws:
     * @Bug :
     * @Modified :
     * @Version : 1.0
     */
    @RequestMapping(value = "/historys/{yearMonth}/archive",method = RequestMethod.POST)
    public Result historyDetail(@PathVariable String yearMonth) throws Exception {

        archiveService.archive(yearMonth,companyId);

        return new Result(ResultCode.SUCCESS);
    }



    /**
     * @methodName : 新建报表
     * @author : HK意境
     * @date : 2021/11/24 20:41
     * @description : 新建某月份的报表
     * @Todo : 制作新的报表， 切换统计周期
     * @params :
         * @param : null
     * @return : null
     * @throws:
     * @Bug :
     * @Modified :
     * @Version : 1.0
     */
    @RequestMapping(value = "/historys/{yearMonth}/newReport",method = RequestMethod.PUT)
    public Result newReport(@PathVariable String yearMonth) {
        //修改企业设置
        CompanySettings cs = companySettingsService.findById(companyId);
        if(cs == null) {
            cs = new CompanySettings();
        }
        cs.setCompanyId(companyId);
        cs.setDataMonth(yearMonth);
        companySettingsService.save(cs);

        return new Result(ResultCode.SUCCESS);
    }




     /**
      * @methodName : 保存企业设置
      * @author : HK意境
      * @date : 2021/11/24 20:43
      * @description : 保存企业设置
      * @Todo :
      * @params :
          * @param : null
      * @return : null
      * @throws:
      * @Bug :
      * @Modified :
      * @Version : 1.0
      */
    @RequestMapping(value = "/settings",method = RequestMethod.POST)
    public Result saveSettings(@RequestBody CompanySettings companySettings) {
        companySettings.setCompanyId(companyId);
        companySettingsService.save(companySettings);
        return new Result(ResultCode.SUCCESS);
    }


    /**
     * 根据用户id和考勤年月查询用户考勤归档明细
     *
     * /social_securitys/historys/data?userId=1063705482939731968&yearMonth=201906
     */
    @RequestMapping(value = "/historys/data",method = RequestMethod.GET)
    public ArchiveDetail historyData(@RequestParam(name = "userId") String userId,
                                     @RequestParam(name = "yearMonth") String yearMonth) throws Exception {
        return archiveService.findUserArchiveDetail(userId,yearMonth);
    }

    /**
     * 查询历史归档列表
     *  /historys/2019/list
     */
    @RequestMapping(value = "/historys/{year}/list",method = RequestMethod.GET)
    public Result historysList(@PathVariable String year) throws Exception {
        List<Archive> list = archiveService.findArchiveByYear(companyId,year);
        return new Result(ResultCode.SUCCESS,list);
    }

}
