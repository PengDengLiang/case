package com.itcast.server;

import com.itcast.dao.DaoUser;
import com.itcast.domain.PageBean;
import com.itcast.domain.User;
import com.itcast.domain.UserNumber;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

public class UserServer {
    private DaoUser daoUser = new DaoUser();
    //登入
    public UserNumber login(UserNumber number) throws SQLException {
        UserNumber userNumber = daoUser.getUserNumber(number.getUsername(), number.getPassword());
        return userNumber;
    }
    //查询所有用户信息
    public List<User> findAll() throws SQLException {
        List<User> users = daoUser.selectAll();
        if (users==null){
            return null;
        }
        return users;
    }
    //分页操作
//    public PageBean<User> getPageBean(int currentPage) throws SQLException {
//        //
//        PageBean<User> userPageBean = new PageBean<>();
//        //封装当前页码
//        userPageBean.setCurrentPage(currentPage);
//        //封装固定每页显示条数
//        int pageSize=2;
//        userPageBean.setPageSize(pageSize);
//        //调用DaoUser对象获取总数据条数进行封装
//        DaoUser daoUser = new DaoUser();
//        int totalCount = daoUser.getTotalCount();
//        userPageBean.setTotalcount(totalCount);
//        // 获取总页数
//        userPageBean.setTotalPage(userPageBean.gettotalPage());
//        //封装每页起始位置的数据在数据库中的位置
//        int begin=(currentPage-1)*pageSize;
//        //调用分页方法，
//        List<User> page = daoUser.getPage(begin, pageSize);
//        userPageBean.setList(page);
//        return userPageBean;
//    }
    //多条件查询
    public List<User> getFindMore(String name,String gender) throws SQLException {
        List<User> more = daoUser.findMore(name, gender);
        return more;
    }

    //多条件分页查询
    public PageBean<User> getFindPageBean(int currentPage,String name,String gender) throws SQLException {
        //
        PageBean<User> userFindPageBean = new PageBean<>();
        //封装当前页码
        userFindPageBean.setCurrentPage(currentPage);
        //封装固定每页显示条数
        int pageSize=2;
        userFindPageBean.setPageSize(pageSize);
        //调用DaoUser对象获取总数据条数进行封装
        DaoUser daoUser = new DaoUser();
        int totalCount = daoUser.getTotalCount(name,gender);
        userFindPageBean.setTotalcount(totalCount);
        // 获取总页数
        userFindPageBean.setTotalPage(userFindPageBean.gettotalPage());
        //封装每页起始位置的数据在数据库中的位置
        int begin=(currentPage-1)*pageSize;
        //调用分页方法，
        List<User> page = daoUser.getFindPage(name, gender, begin, pageSize);
        userFindPageBean.setList(page);
        return userFindPageBean;
    }
    //添加数据
    public void getAddUser(User user) throws SQLException {
        try {
            daoUser.addUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //根据id查询数据
    public User getSelectId(int id) throws SQLException {
         return daoUser.selectId(id);
    }
    public void getUpdata(User user) throws SQLException {
        try {
            daoUser.update(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //删除单条数据
    public void getDelect(int id) throws SQLException {
        try {
            daoUser.delect(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //删除多条数据
    public void getDelectMore(String[] split) throws SQLException {
       daoUser.deleteMore(split);
    }
}
