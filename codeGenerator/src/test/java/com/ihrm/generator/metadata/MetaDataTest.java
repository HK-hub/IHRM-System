package com.ihrm.generator.metadata;

import org.junit.Before;
import org.junit.Test;

import java.sql.*;
import java.util.Properties;

/**
 * @ClassName : MetaDataTest
 * @author : HK意境
 * @date : 2021/11/22
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public class MetaDataTest {

    //private DriverManager driverManager ;
    private Connection connection ;
    private PreparedStatement preparedStatement ;
    private Statement statement ;
    private static String url = "jdbc:mysql://localhost:3306/db_ihrm";
    private static String username="root";
    private static String password="root";


    /**
     * @methodName :
     * @author : HK意境
     * @date : 2021/11/22 16:52
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



    @Test
    public void metaDataBaseInfoTest() throws SQLException {

        // 获取元数据
        DatabaseMetaData metaData = connection.getMetaData();

        // 获取数据库基本信息
        //3.获取数据库基本信息
        System.out.println(metaData.getUserName());
        System.out.println(metaData.supportsTransactions());//是否支持事务
        System.out.println(metaData.getDatabaseProductName());

        connection.close();

    }


    //获取数据库列表
    @Test
    public void test02() throws SQLException {
        //1.获取元数据
        DatabaseMetaData metaData = connection.getMetaData();
        //2.获取所有数据库列表
        ResultSet rs = metaData.getCatalogs();

        while (rs.next()) {
            System.out.println(rs.getString(1));
        }
        rs.close();
        connection.close();
    }

    //获取指定数据库中的表信息
    @Test
    public void test03() throws Exception {
        //1.获取元数据
        DatabaseMetaData metaData = connection.getMetaData();

        //2.获取数据库中表信息
        /**
         * String catalog, String schemaPattern,String tableNamePattern, String types[]
         *
         *  catalog:当前操作的数据库
         *      mysql:
         *          :ihrm
         *          catalog
         *      oralce:
         *          xxx:1521:orcl
         *          catalog
         *   schema：
         *      mysql：
         *          ：null
         *      oracle：
         *          ：用户名（大写）
         *
         *    tableNamePattern：
         *      null：查询所有表
         *      为空：查询目标表
         *
         *    types：类型
         *      TABLE：表
         *      VIEW：视图
         *
         */
        ResultSet rs = metaData.getTables("db_ihrm", null, null, new String[]{"TABLE"});


        while (rs.next()) {
            System.out.println(rs.getString("TABLE_NAME"));
        }
    }


    //获取指定表中的字段信息
    @Test
    public void test04() throws Exception {
        DatabaseMetaData metaData = connection.getMetaData();

        //获取表中的字段信息
        /**
         *  catalog
         *  schema
         *  tableName
         *  columnName
         */
        ResultSet rs = metaData.getColumns(null, null, "bs_user", null);

        while (rs.next()) {
            System.out.println(rs.getString("COLUMN_NAME") + " :  " + rs.getString("TYPE_NAME"));
        }
    }

}
