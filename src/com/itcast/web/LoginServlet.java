package com.itcast.web;

import com.itcast.domain.UserNumber;
import com.itcast.server.UserServer;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Map;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //对客户端发生的请求进行重新编码
        request.setCharacterEncoding("utf-8");
        //创建session对象，获取验证码数据,转为字符串格式
        HttpSession session = request.getSession();
        String number = (String) session.getAttribute("number");
        //获取用户输入的验证码
        String checkCoke = request.getParameter("checkCoke");
        //清楚储存在服务在服务器中的session对象，保持验证码的唯一
        session.removeAttribute("number");
        //如果验证码错误则报错
        if (!checkCoke.equalsIgnoreCase(number)){
            request.setAttribute("login_error","验证密码输入错误");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
            return;
        }
        //获取客户端的请求
        Map<String, String[]> map = request.getParameterMap();
        //获取UserNumber对象，将用户数据封装
        UserNumber userNumber = new UserNumber();
        try {
            BeanUtils.populate(userNumber,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        try {
            //调用方法，验证用户的账号密码是否正确
            UserServer server = new UserServer();
            UserNumber login = server.login(userNumber);
            if (login!=null){
               response.sendRedirect(request.getContextPath()+"/findMorePageServlet");
               //request.getRequestDispatcher("/findMorePageServlet").forward(request,response);
            }else {
                request.setAttribute("login_error","账号或密码输入错误");
                request.getRequestDispatcher("/login.jsp").forward(request,response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
