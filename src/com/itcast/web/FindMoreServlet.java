package com.itcast.web;

import com.itcast.domain.User;
import com.itcast.server.UserServer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@WebServlet("/findMoreServlet")
public class FindMoreServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String name = request.getParameter("checkName");
        String gender = request.getParameter("checkGender");
        UserServer server = new UserServer();
        try {
            List<User> user = server.getFindMore(name, gender);
            System.out.println(user);
            request.setAttribute("page",user);
            request.getRequestDispatcher("/list.jsp").forward(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
