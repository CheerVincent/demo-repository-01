package com.bobo.books.servlet;

import com.bobo.books.bean.User;
import com.bobo.books.service.IUserService;
import com.bobo.books.service.impl.UserServiceImpl;
import com.bobo.books.utils.Constant;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 处理登录和注销的Servlet
 */
@WebServlet(name = "loginServlet",urlPatterns = "/loginServlet")
public class LoginServlet extends HttpServlet {

    IUserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 实现登录的功能
        // 1.获取表单提交的账号密码
        String userName = req.getParameter("userName");
        String password = req.getParameter("password");
        // 2.调用Service的相关方法来实现处理
        User user = userService.checkUserNameAndPassword(userName, password);
        HttpSession session = req.getSession();
        // 3.根据验证的结果来做出对应的响应
        if(user != null){
            // 我们不能把密码存储在Session
            user.setPassword(null);
            // 登录成功 把登录成功的用户信息存储在Session
            session.setAttribute(Constant.SESSION_LOGIN_USER,user);
            // 登录成功
            resp.sendRedirect("/main.jsp");
        }else {
            // 登录失败

            session.setAttribute("msg","账号密码错误");
            resp.sendRedirect("/login.jsp");
        }
    }
}
