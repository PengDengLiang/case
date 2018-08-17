package com.itcast.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtils {
    private static DataSource dataSource;
    static {
        try {
            //创建Properties对象，他是一个持久的属性集，属性列表中每个键及其对应的值都是一个字符串
            Properties pro = new Properties();
            // 使用ClassLoader加载properties配置文件生成对应的输入流
            InputStream stream = JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties");
            // 使用properties对象加载输入流
            pro.load(stream);
            //使用druid的工厂方法获取连接池对象
            dataSource = DruidDataSourceFactory.createDataSource(pro);
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //创键获取连接池对象
    public static DataSource getDataSource(){
        return dataSource;
    }
    //创建与连接池相连接的Connection对象
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
