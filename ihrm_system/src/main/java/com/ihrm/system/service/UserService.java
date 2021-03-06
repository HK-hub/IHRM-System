package com.ihrm.system.service;

import com.ihrm.common.utils.IdWorker;
import com.ihrm.common.utils.QiniuUploadUtil;
import com.ihrm.domain.company.Department;
import com.ihrm.domain.system.Role;
import com.ihrm.domain.system.User;
import com.ihrm.system.feign.DepartmentFeignClient;
import com.ihrm.system.mapper.RoleDao;
import com.ihrm.system.mapper.UserDao;
import com.ihrm.system.utils.BaiduAiUtil;
import org.apache.commons.codec.binary.Base64;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;


/**
 * @author : HK意境
 * @ClassName : UserService
 * @date : 2021/11/14 14:25
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Service
public class UserService {

    @Autowired
    private DepartmentFeignClient departmentFeignClient ;
    @Autowired
    private UserDao userDao;
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private RoleDao roleDao ;
    @Autowired
    private BaiduAiUtil baiduAiUtil ;


    /**
     * 1.保存用户
     */
    public void save(User user) {
        //设置主键的值
        String id = idWorker.nextId()+"";
        //设置初始密码
        String password = new Md5Hash("123456", user.getMobile(), 3).toString();
        user.setPassword(password);

        //设置用户登录
        user.setLevel("user");
        //指定用户状态: 启动
        user.setEnableState(1);
        // 雪花算法ID
        user.setId(id);
        //调用dao保存部门
        userDao.save(user);
    }

    /**
     * 2.更新用户
     */
    public void update(User user) {
        //1.根据id查询部门
        User target = userDao.findById(user.getId()).get();
        //2.设置部门属性
        target.setUsername(user.getUsername());
        target.setPassword(user.getPassword());
        target.setDepartmentId(user.getDepartmentId());
        target.setDepartmentName(user.getDepartmentName());
        //3.更新部门
        userDao.save(target);
    }

    /**
     * 3.根据id查询用户
     */
    public User findById(String id) {
        return userDao.findById(id).get();
    }

    /**
     * 4.查询全部用户列表
     *      参数：map集合的形式
     *          hasDept
     *          departmentId
     *          companyId
     *
     */
    public Page findAll(Map<String,Object> map, int page, int size) {

        //1.需要查询条件
        Specification<User> spec = new Specification<User>() {
            /**
             * 动态拼接查询条件
             * @return
             */
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<>();
                //根据请求的companyId是否为空构造查询条件
                if(!StringUtils.isEmpty(map.get("companyId"))) {
                    list.add(criteriaBuilder.equal(root.get("companyId").as(String.class),(String)map.get("companyId")));
                }
                //根据请求的部门id构造查询条件
                if(!StringUtils.isEmpty(map.get("departmentId"))) {
                    list.add(criteriaBuilder.equal(root.get("departmentId").as(String.class),(String)map.get("departmentId")));
                }
                if(!StringUtils.isEmpty(map.get("hasDept"))) {
                    //根据请求的hasDept判断  是否分配部门 0未分配（departmentId = null），1 已分配 （departmentId ！= null）
                    if("0".equals((String) map.get("hasDept"))) {
                        list.add(criteriaBuilder.isNull(root.get("departmentId")));
                    }else {
                        list.add(criteriaBuilder.isNotNull(root.get("departmentId")));
                    }
                }
                return criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
            }
        };

        //2.分页
        // PageRequest.of(int ,int ) ： 第一个参数：多少也，第二个参数：一页多少条
        Page<User> pageUser = userDao.findAll(spec, PageRequest.of(page-1, size));
        return pageUser;
    }

    public User findByMobile(String mobile){

        User user = userDao.findByMobile( mobile) ;
        return user ;
    }


    /**
     * 5.根据id删除用户
     */
    public void deleteById(String id) {
        userDao.deleteById(id);
    }


    /**
     * @methodName : 分配角色
     * @author : HK意境
     * @date : 2021/11/14 19:57
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
    public void assignRoles(String userId, List<String> roleIds) {
        // 更具 Id 查询用户
        User user = userDao.findById(userId).get();

        // 设置 用户的角色集合
        HashSet<Role> roles = new HashSet<>();

        for (String roleId : roleIds) {
            Role role = roleDao.findById(roleId).get();
            roles.add(role) ;
        }
        // 设置用户和角色的关系
        user.setRoles(roles);

        //更新用户角色
        userDao.save(user) ;

    }


    /**
     * @methodName : saveAll
     * @author : HK意境
     * @date : 2021/11/19 13:27
     * @description :
     * @Todo : 批量保存用户
     * @params :
         * @param : null
     * @return : null
     * @throws:
     * @Bug :
     * @Modified :
     * @Version : 1.0
     */
    @Transactional
    public void saveAll(List<User> users, String companyId, String companyName) {
        for (User user : users) {
            // 默认密码
            user.setPassword(new Md5Hash("123456",user.getMobile(),3).toString());
            // 设置用户ID
            user.setId(""+idWorker.nextId());
            // 基本属性
            user.setCompanyId(companyId);
            user.setCompanyName(companyName);
            user.setLevel("user");

            // 填充部门属性
            Department byCode = departmentFeignClient.findByCode(user.getDepartmentId(), user.getCompanyId());
            if (byCode != null) {
                user.setDepartmentId(byCode.getId());
                user.setDepartmentName(byCode.getName());
            }

            userDao.save(user);

        }
    }


    /**
     * @methodName : uploadImage
     * @author : HK意境
     * @date : 2021/11/20 15:27
     * @description : 七牛云存储，实现用户头像，百度云人脸库
     * @Todo : 上传，保存图片，返回 url 地址, 并且注册到百度云人脸库中
     * @params : 
         * @param : id：用户id
         * @param : file : 头像文件
     * @return : 请求路径，url ,data url
     * @throws: 
     * @Bug :
     * @Modified :
     * @Version : 1.0
     */
    public String uploadImage(String id, MultipartFile file) throws IOException {

        //查询用户
        User user = userDao.findById(id).get();

        if (user != null) {

            // 将图片上传到七牛云存储
            String imageName = user.getUsername()+"-"+user.getMobile();
            String imageUrl = new QiniuUploadUtil().upload(imageName, file.getBytes());

            // 立即刷新图片缓存
            int code = new QiniuUploadUtil().refreshImage(imageUrl);

            //设置头像
            user.setStaffPhoto(imageUrl);
            userDao.save(user) ;

            //对图片bytes 数组进行base64编码
            String imageBase64 = Base64.encodeBase64String(file.getBytes());

            // 判断是否已经注册人脸信息
            if (baiduAiUtil.faceExists(id)) {
                // error_code == 0 ， ——> 进行更新
                baiduAiUtil.faceUpdate(id, imageBase64);

            }else {
                // 进行注册
                baiduAiUtil.faceRegister(id, imageBase64);
            }

            return imageUrl ;

        }else {
            return "null" ;
        }

    }
}
