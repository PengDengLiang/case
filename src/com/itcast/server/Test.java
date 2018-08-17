package com.itcast.server;

import com.itcast.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        //测试使用关系型数据库的效率
        JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());
        String sql="select count(*) from usertwo where name=?";
        long l = System.currentTimeMillis();
        System.out.println(l);
        for (int i = 0; i <100000; i++) {
            Map<String, Object> map = jdbcTemplate.queryForMap(sql, "张三");
        }
        long y= System.currentTimeMillis();
        System.out.println((y-l)/1000);
    }
}
