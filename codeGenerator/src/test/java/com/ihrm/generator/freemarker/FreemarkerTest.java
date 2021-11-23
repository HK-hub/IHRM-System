package com.ihrm.generator.freemarker;

import freemarker.cache.FileTemplateLoader;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author : HK意境
 * @ClassName : FreemarkerTest
 * @date : 2021/11/22 15:31
 * @description : 第一个Freemarker 程序： 数据 + 模板 = 文件输出
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */

public class FreemarkerTest {

    @Test
    public void test01() throws IOException, TemplateException {

        // 创建 Freemarker 配置类
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_30);
        // 指定模板加载器： 将模板存入缓存中
        // 模板加载器：文件模板加载器 FileTemplateLoader
        FileTemplateLoader fileTemplateLoader = new FileTemplateLoader(new File("templates"));
        configuration.setTemplateLoader(fileTemplateLoader);
        // 获取模板
        Template template = configuration.getTemplate("template01.ftl");
        // 构造数据模型
        HashMap<String, Object> dataModel = new HashMap<>();
        dataModel.put("username","HK意境");
        dataModel.put("flag",654);

        ArrayList<String> weeks = new ArrayList<>();
        weeks.add("星期一");
        weeks.add("星期二");
        weeks.add("星期三");
        weeks.add("星期四");
        weeks.add("星期五");
        weeks.add("星期六");
        weeks.add("星期七");
        dataModel.put("weeks", weeks);


        // 文件输出:
        // 参数： 数据模型(hashMap)，writer ： fileWriter ——> 输出到文件 ，, printWriter ——> 输出到控制台
        //template.process(dataModel, new FileWriter(new File("E:\\百度网盘\\黑马程序员iHRM 人力资源管理系统\\day12\\result\\a.txt")));
        template.process(dataModel, new PrintWriter(System.out));


    }


    /**
     * 测试字符串模板
     *
     */
    @Test
    public void stringTest() throws IOException, TemplateException {

        // 创建配置类
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_31);

        // 指定加载器
        configuration.setTemplateLoader(new StringTemplateLoader());
        // 创建字符串模板 :
        String stringTemplate = "你好，欢迎您: ${username}" ;
        Template template = new Template("name1",new StringReader(stringTemplate), configuration);

        // 构造数据模型
        HashMap<String, Object> dataModel = new HashMap<>();
        dataModel.put("username","HHHH");

        // 输出
        template.process(dataModel, new PrintWriter(System.out));

    }



}
