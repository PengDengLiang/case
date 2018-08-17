package com.itcast.dao;

import com.itcast.domain.User;
import com.itcast.domain.UserNumber;
import com.itcast.server.UserServer;
import com.itcast.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoUser {
    private QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
    //创建登入验证
    public  UserNumber getUserNumber(String username,String password) throws SQLException {
        String sql="select * from user where username=? and password=?";
        UserNumber query = runner.query(sql, new BeanHandler<UserNumber>(UserNumber.class),username,password);
        return query;
    }
    //展示所以数据库信息
    public List<User> selectAll() throws SQLException {
        String sql="select * from usertwo";
        List<User> query = runner.query(sql, new BeanListHandler<User>(User.class));
        return query;
    }
    //条件查询
    public List<User> findMore(String name,String gender) throws SQLException {
        String sql="select * from usertwo where 1=1";
        List<String> list = new ArrayList<>();
        if (!name.equals("")){
            sql+="and name like ?";
            list.add("'%"+name+"%'");
        }
        if (!gender.equals("")){
            sql+="and gender like ?";
            list.add("'%"+gender+"%'");
        }
        Object[] objects = list.toArray();
        List<User> query = runner.query(sql, new BeanListHandler<User>(User.class), objects);
        return query;
    }
    @Test
    public void test2() throws SQLException {
        DaoUser daoUser = new DaoUser();
        long l = System.currentTimeMillis();
        System.out.println(l);
        for (int i = 0; i <1000000; i++) {
            List<User> users = daoUser.selectAll();
        }
        long l1 = System.currentTimeMillis();
        System.out.println(l1-l);
    }
    //分页操作
        //查询所有数据条数
    public int getTotalCount(String name,String gender) throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());
        String sql="select count(*) from usertwo where 1=1";
        List<String> list=new ArrayList<>();
        if (name==null&&gender==null){
            name="";
            gender="";
        }
        if (!name.equals("")){
            sql+=" and name like ?";
            list.add("%"+name+"%");
        }
        if (!gender.equals("")){
            sql+=" and gender like ?";
            list.add("%"+gender+"%");
        }
        Object[] objects = list.toArray();
        System.out.println(sql+"==============");
        Number totalCount = (Number)runner.query(sql,new ScalarHandler(),objects);
        System.out.println(totalCount+"==================");
        return totalCount.intValue();
    }

        //数据分页展示
//    public List<User> getPage(int begin, int pageSize) throws SQLException {
//        String sql="select * from usertwo limit ?,?";
//        List<User> query = runner.query(sql, new BeanListHandler<User>(User.class), begin, pageSize);
//        return query;
//    }
    //分页多条件查询
    public List<User> getFindPage(String name,String gender,int begin, int pageSize) throws SQLException {
        String a="";
        List<Object> list = new ArrayList<>();
        if (name==null&&gender==null){
            name="";
            gender="";
        }
        if (!name.equals("")){
            a+=" and name =?";
            list.add(name);
        }
        if (!gender.equals("")){
            a+=" and gender = ?";
            list.add(gender);
        }
        System.out.println(a);
        System.out.println(list);
        list.add(begin);
        list.add(pageSize);
        Object[] objects = list.toArray();
        String sql="select * from usertwo  where 1=1"+a+" limit ?,?";
        //System.out.println(sql);
        List<User> query = runner.query(sql, new BeanListHandler<User>(User.class), objects);
        //System.out.println(query);
        return query;
    }
//    @Test
//    public void test() throws SQLException {
//        DaoUser daoUser = new DaoUser();
//        //getFindPage
//        List<User> findPage = daoUser.getFindPage("张三", "", 0, 2);
//        System.out.println(findPage);
//    }
    //添加数据
    public void addUser(User user) throws SQLException {
        String sql="insert into usertwo (id,name,gender,age,address,qq,email) values(null,?,?,?,?,?,?)";
            //String name,String gender, int age,String address,String qq,String email
        int update = runner.update(sql, user.getName(), user.getGender(), user.getAge(), user.getAddress(), user.getQq(), user.getEmail());
    }
    //根据id值查询数据库信息,返回数据进行数据修改
    public User selectId(int id) throws SQLException {
        String sql="select * from usertwo where id=?";
        return runner.query(sql, new BeanHandler<User>(User.class), id);
    }
    //修改数据库
    public void update(User user) throws SQLException {
        String sql="update usertwo set name=?,gender=?,age=?,address=?, qq=?,email=? where id=?";
        int update = runner.update(sql, user.getName(), user.getGender(), user.getAge(), user.getAddress(), user.getQq(), user.getEmail(), user.getId());
    }
    //删除单条数据
    public void delect(int id) throws SQLException {
        String sql="delete from usertwo where id=?";
        int del = runner.update(sql, id);
    }
    //删除多行
    public void deleteMore(String[] split) throws SQLException {
        String sql="delete from usertwo where id=?";
        for (String s : split) {
            runner.update(sql, Integer.parseInt(s));
        }
    }
}