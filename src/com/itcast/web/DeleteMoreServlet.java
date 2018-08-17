package com.itcast.web;

import com.itcast.server.UserServer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

@WebServlet("/deleteMoreServlet")
public class DeleteMoreServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        //获取字符串进行字符串切割,获取新的数据
        String map= request.getParameter("id");
        String[] split = map.split("-");
        //由于新字符串会因为数据切割,导致第一个元素为空值
        //重新建立数组进行替换
        String [] str=new String[split.length-1];
        System.out.println(map+"+++++++++++++++++++++++++++++++++++++++++++");
        System.out.println(str.length+";"+str);
        for (int i = 1; i <split.length ; i++) {
            str[i-1]=split[i];
        }
        for (String s : str) {
            System.out.println("=="+s);
        }
        UserServer server = new UserServer();
        try {
            server.getDelectMore(str);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect(request.getContextPath()+"/findMorePageServlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
