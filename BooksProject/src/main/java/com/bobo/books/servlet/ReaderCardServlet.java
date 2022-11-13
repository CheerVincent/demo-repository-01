package com.bobo.books.servlet;

import com.alibaba.fastjson.JSON;
import com.bobo.books.bean.*;
import com.bobo.books.service.IBookService;
import com.bobo.books.service.IReaderCardService;
import com.bobo.books.service.IStudentService;
import com.bobo.books.service.impl.BookServiceImpl;
import com.bobo.books.service.impl.ReaderCardServiceImpl;
import com.bobo.books.service.impl.StudentServiceImpl;
import com.bobo.books.utils.BookFlagE;
import com.bobo.books.utils.Constant;
import com.bobo.books.utils.RequestParameterUtils;
import com.bobo.books.utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "readerCardServlet",urlPatterns = "/readerCardServlet")
public class ReaderCardServlet extends HttpServlet {

    IStudentService studentService = new StudentServiceImpl();

    IReaderCardService cardService = new ReaderCardServiceImpl();

    IBookService bookService = new BookServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1.获取请求的类型
        String type = req.getParameter(Constant.REQUEST_PARAMETER_TYPE);
        // 2.如果type不为空，那么根据type的不同值做出不同的操作
        if(StringUtils.isNotEmpty(type)){
            if(Constant.SERVLET_TYPE_QUEYR.equals(type)){
                queryList(req, resp);

            }else if(Constant.SERVLET_TYPE_SAVE.equals(type)){
                saveCard(req, resp);
            }else if(Constant.SERVLET_TYPE_UPDATE.equals(type)){
            }else if(Constant.SERVLET_TYPE_DELETE.equals(type)){
            }else if(Constant.SERVLET_TYPE_QUERYBYID.equals(type)){
            }else if("saveBefor".equals(type)){
                // 查询出所有的学生信息
                List<Student> list = studentService.list(null);
                req.setAttribute("stus",list);
                req.getRequestDispatcher("/card/cardAdd.jsp").forward(req,resp);
            }else if("queryBooks".equals(type)){

                // 获取借书卡的编号
                String cardId = req.getParameter("id");
                // 查询所有可以借阅的书籍
                List<Book> list = bookService.queryListByState(BookFlagE.FREE.code);
                req.setAttribute("books",list);
                req.setAttribute("cardId",cardId);
                req.getRequestDispatcher("/card/bookBorrow.jsp").forward(req,resp);
            }else{

            }
        }else{

        }
    }

    private void saveCard(HttpServletRequest req, HttpServletResponse resp) {
        // 生成借书卡
        try {
            ReaderCard readerCard = RequestParameterUtils.getRequestParameterForReflect(req, ReaderCard.class);
            // 根据学生编号 获取对应的学生姓名
            Student student = studentService.queryById(readerCard.getStuId());
            readerCard.setStuName(student.getStuName());
            // 生成 借书卡的 密码
            String password = UUID.randomUUID().toString();
            readerCard.setPassword(password);
            // readerCard.setState(0);
            cardService.saveReaderCard(readerCard);
            resp.sendRedirect("/readerCardServlet?type=query");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void queryList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 查询所有的 借书卡信息
        List<ReaderCard> list = null;
        // 查询当前登录用户对应的学生的学生卡的信息
        User user = (User) req.getSession().getAttribute(Constant.SESSION_LOGIN_USER);
        if(!"admin".equals(user.getUserName())){
            // 如果不是管理员 那么就不能查询所有的数据
            // 我们需要根据当前的登录用户找到对应的学生信息
            String phone = user.getUserName();
            Student stu = studentService.queryByPhone(phone);
            // 根据学生的编号查询 借书卡的情况
            if(stu != null){
                list = cardService.queryByStuId(stu.getId());
            }
        }else{
            list = cardService.list(null);
        }

        req.setAttribute("list",list);
        req.getRequestDispatcher("/card/card.jsp").forward(req, resp);
    }
}
