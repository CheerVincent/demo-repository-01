package com.bobo.books.servlet;

import com.bobo.books.bean.User;
import com.bobo.books.service.IUserService;
import com.bobo.books.service.impl.UserServiceImpl;
import com.bobo.books.utils.Constant;
import com.bobo.books.utils.RequestParameterUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

/**
 * 用户的Servlet
 * 作用：接收请求 --》通过Service处理请求
 *      响应请求
 */
@WebServlet(name = "UserServlet",urlPatterns = "/userServlet")
public class UserServlet extends HttpServlet {
    // 声明IUserService对象
    private IUserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    /**
     * 统一处理浏览器提交的 http://localhost:8082/userServlet 的请求
     * @param req 封装请求相关信息的对象
     * @param resp  封装响应相关信息的对象
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String type = req.getParameter(Constant.REQUEST_PARAMETER_TYPE);

        if(type != null && !"".equals(type)){
            if(Constant.SERVLET_TYPE_SAVE.equals(type)){
                // 添加用户信息
                try {
                    saveOrUpdateUser(req, resp);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else if(Constant.SERVLET_TYPE_UPDATE.equals(type)){
                // 更新用户信息
            }else if(Constant.SERVLET_TYPE_DELETE.equals(type)){
                // 删除用户信息
                deleteUserById(req, resp);
            }else if(Constant.SERVLET_TYPE_QUEYR.equals(type)){
                // 查询用户
                queryUser(req, resp);
            }else if(Constant.SERVLET_TYPE_QUERYBYID.equals(type)){
                // 查询单条记录
                String id = req.getParameter("id");
                User user = userService.queryById(Integer.parseInt(id));
                // 跳转到更新的页面同时保存数据到Request作用域中
                req.setAttribute("user",user);
                req.getRequestDispatcher("/user/userUpdate.jsp").forward(req,resp);
            }else if(Constant.SERVLET_TYPE_CHECK.equals(type)){
                // 验证账号是否存在
                String userName = req.getParameter("userName");
                String s = userService.checkUserName(userName);
                resp.getWriter().println(s);
                resp.flushBuffer();
            }
        }else{
            // 查询用户信息
            queryUser(req, resp);
        }
    }

    private void deleteUserById(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // 获取需要删除的用户编号
        String id = req.getParameter("id");
        // 通过Service来处理删除操作
        Integer count = userService.deleteById(Integer.parseInt(id));
        // 然后做一个重定向查询所有用户信息的操作
        resp.sendRedirect("/userServlet");
    }

    /**
     * 添加用户的方法
     * @param req
     * @param resp
     * @throws IOException
     */
    private void saveOrUpdateUser(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // 1.获取用户表单提交的信息
        /*User user = new User();
        user.setUserName(req.getParameter("userName"));
        user.setPassword(req.getParameter("password"));
        user.setPhoneNum(req.getParameter("phoneNum"));
        user.setEmail(req.getParameter("email"));*/
        User user = RequestParameterUtils.getRequestParameterForReflect(req, User.class);
        Integer count = -1;
        if(user.getId() != null && user.getId() > 0){
            //表示是更新
            count = userService.updateUser(user);
        }else{
            // count 就是影响的行数
            count = userService.addUser(user);
        }

        if(count > 0){
            // 表示查询成功 再做一次查询操作
            resp.sendRedirect("/userServlet");
        }else{
            // 表示插入失败
            System.out.println("插入失败...");
            // TODO 跳转到失败页面
        }
    }



    /**
     * 查询用户信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void queryUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 通过service查询所有的用户信息
        List<User> list = userService.getUser(null);
        // 把查询的信息绑定在 request 作用域中
        req.setAttribute("list",list);
        // 页面跳转到user.jsp中
        req.getRequestDispatcher("/user/user.jsp").forward(req, resp);
    }
}
