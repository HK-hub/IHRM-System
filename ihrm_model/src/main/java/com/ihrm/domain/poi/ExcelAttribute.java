package com.ihrm.domain.poi;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName : ExcelAttribute
 * @author : HK意境
 * @date : 2021/11/19
 * @description : 自定义注解，实现动态属性注入，设置单元格值
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ExcelAttribute {

    /** 对应的列名称 */
    String name() default "";

    /** 列序号 */
    int sort();

    /** 字段类型对应的格式 */
    String format() default "";

}
