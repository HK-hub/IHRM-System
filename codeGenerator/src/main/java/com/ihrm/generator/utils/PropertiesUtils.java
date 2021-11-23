package com.ihrm.generator.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

/**
 * @ClassName : PropertiesUtils
 * @author : HK意境
 * @date : 2021/11/22
 * @description : 需要把所有的配置都配置到 properties 文件夹下所有的 .properties 文件
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public class PropertiesUtils {

    public static Map<String,String> customMap = new HashMap<>();

    static {
        File dir = new File("properties");

        try {
            String absolutePath = dir.getAbsolutePath();
            String realPath = new StringBuilder(absolutePath).insert(absolutePath.lastIndexOf("\\"),"\\codeGenerator").toString();

            List<File> files = FileUtils.searchAllFile(new File(realPath));
            for (File file : files) {
                if(file.getName().endsWith(".properties")) {
                    Properties prop = new Properties();
                    prop.load(new FileInputStream(file));
                    customMap.putAll((Map) prop);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        PropertiesUtils.customMap.forEach((k, v)->{
            System.out.println(k+"--"+v);
        });
    }
}
