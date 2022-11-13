package com.bobo.books.servlet;

import com.bobo.books.bean.LendList;
import com.bobo.books.service.ILendService;
import com.bobo.books.service.impl.LendListServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet(name="lendListServlet",urlPatterns = "/lendListServlet")
public class LendListServlet extends HttpServlet {

    ILendService lendService = new LendListServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String type = req.getParameter("type");
        if("borrow".equals(type)){
            // 借阅书籍
            // 获取借书卡的编号
            String cardId = req.getParameter("cardId");
            // 获取需要借的书籍id
            String bookId = req.getParameter("bookId");
            LendList lendList = new LendList();
            lendList.setBookId(Integer.parseInt(bookId));
            lendList.setCardId(cardId);
            lendList.setLendDate(new Date()); // 设置借书的时间
            // 保存 借阅的记录
            lendService.borrowLendList(lendList);
            resp.sendRedirect("/readerCardServlet?type=query");
        }else{
            // 归还数据
            String cardId = req.getParameter("cardId");
            // 根据cardId查询到对应的借阅记录
            LendList lendList = lendService.queryByCardId(cardId);
            // 归还的操作
            lendService.backLendList(lendList);
            resp.sendRedirect("/readerCardServlet?type=query");
        }
    }
}
