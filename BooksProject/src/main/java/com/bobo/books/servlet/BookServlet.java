package com.bobo.books.servlet;

import com.bobo.books.bean.Book;
import com.bobo.books.service.IBookService;
import com.bobo.books.service.impl.BookServiceImpl;
import com.bobo.books.utils.Constant;
import com.bobo.books.utils.RequestParameterUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "bookServlet",urlPatterns = "/bookServlet")
public class BookServlet extends HttpServlet {

    private IBookService bookService = new BookServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1.获取提交的type数据
        String type = req.getParameter(Constant.REQUEST_PARAMETER_TYPE);
        if(Constant.SERVLET_TYPE_QUEYR.equals(type)){
            queryBookList(req, resp);
        }else if(Constant.SERVLET_TYPE_SAVE.equals(type)){
            saveOrUpdateBook(req, resp);
        }else if(Constant.SERVLET_TYPE_QUERYBYID.equals(type)){
            // 根据编号查询book信息
            String id = req.getParameter("id");
            Book book = bookService.queryById(Integer.parseInt(id));
            req.setAttribute("book",book);
            req.getRequestDispatcher("/book/bookUpdate.jsp").forward(req,resp);
        }else if(Constant.SERVLET_TYPE_UPDATE.equals(type)){
            saveOrUpdateBook(req, resp);
        }else if(Constant.SERVLET_TYPE_DELETE.equals(type)){
            deleteById(req, resp);
        }else if("search".equals(type)){
            // 查询的关键字
            String keyword = req.getParameter("keyword");
            List<Book> list =bookService.queryBookByKeyword(keyword);
            req.setAttribute("list",list);
            req.setAttribute("keyword",keyword);
            req.getRequestDispatcher("/book/book.jsp").forward(req, resp);
        }else{
            queryBookList(req, resp);
        }
    }

    private void deleteById(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // 逻辑删除Book信息
        String id = req.getParameter("id");
        bookService.deleteById(Integer.parseInt(id));
        resp.sendRedirect("/bookServlet?type=query");
    }

    private void saveOrUpdateBook(HttpServletRequest req, HttpServletResponse resp) {
        // 表示完成Book数据的添加
        try {
            // 通过反射帮助我们从Request中快速提取表单的信息到Book对象中，提升了开发的效率
            Book book = RequestParameterUtils.getRequestParameterForReflect(req, Book.class);
            int count = -1;
            if(book.getId() != null && book.getId() > 0){
                // 表示是更新
                count  = bookService.updateBook(book);
            }else{
                // 表示是添加
                count = bookService.saveBook(book);
            }
            if(count > 0){
                // 表示添加成功
                resp.sendRedirect("/bookServlet?type=query");
            }else{
                // TODO 表示添加失败
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void queryBookList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 查询所有的书籍信息
        List<Book> list = bookService.list(null);
        req.setAttribute("list",list);
        req.getRequestDispatcher("/book/book.jsp").forward(req, resp);
    }
}
