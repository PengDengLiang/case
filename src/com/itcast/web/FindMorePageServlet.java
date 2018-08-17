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

@WebServlet("/findMorePageServlet")
public class FindMorePageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        UserServer server = new UserServer();
        String name = request.getParameter("checkName");
        String gender = request.getParameter("checkGender");

        session.setAttribute("name",name );
        session.setAttribute("gender",gender);
        String currentPage = request.getParameter("currentPage");
        if (currentPage == null) {
            currentPage = "1";
        }
        int  page= Integer.parseInt(currentPage);
        try {
            PageBean<User> findPageBean = server.getFindPageBean(page, name, gender);
            //System.out.println(findPageBean);

            session.setAttribute("page",findPageBean);
            response.sendRedirect(request.getContextPath()+"/list.jsp");
            //request.getRequestDispatcher("/list.jsp").forward(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
