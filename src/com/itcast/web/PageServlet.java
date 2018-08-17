package com.itcast.web;

import com.itcast.domain.PageBean;
import com.itcast.domain.User;
import com.itcast.server.UserServer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/pageServlet")
public class PageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取当前页码,并转为int类型
//        String currentPage = request.getParameter("currentPage");
//        if (currentPage == null) {
//            currentPage = "1";
//        }
//        int page = Integer.parseInt(currentPage);
//        UserServer server = new UserServer();
//        try {
//            PageBean<User> pageBean = server.getPageBean(page);
//            //将获取的对象封装转发到list.jsp
//            HttpSession session = request.getSession();
//            session.setAttribute("pages",pageBean);
//            response.sendRedirect(request.getContextPath()+"/list.jsp");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
