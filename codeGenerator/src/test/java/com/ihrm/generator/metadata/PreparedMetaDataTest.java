package com.ihrm.generator.metadata;

import org.junit.Before;
import org.junit.Test;

import java.sql.*;
import java.util.Properties;

/**
 * @author : HK意境
 * @ClassName : PreparedMetaDataTest
 * @date : 2021/11/22 18:40
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public class PreparedMetaDataTest {


    //private DriverManager driverManager ;
    private Connection connection ;
    private PreparedStatement preparedStatement ;
    private Statement statement ;
    private static String url = "jdbc:mysql://localhost:3306/db_ihrm";
    private static String username="root";
    private static String password="root";


    @Before
    public void initialize() throws ClassNotFoundException, SQLException {
        // 注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");

        // 获取链接
        Properties properties = new Properties();
        properties.put("username",username);
        properties.put("password",password);
        properties.put("remarksReporting","true");

        this.connection = DriverManager.getConnection(url,"root","root");

        //
        statement = connection.createStatement();

        //connection.prepareStatement()
    }



    //sql = SELECT * FROM bs_user WHERE id="1063705482939731968"
    @Test
    public void test() throws Exception {
        String sql = "select * from bs_user where id=?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, "1063705482939731968");

        //获取参数元数据
        ParameterMetaData metaData = pstmt.getParameterMetaData();

        int count = metaData.getParameterCount();

        System.out.println(count);
    }





}
