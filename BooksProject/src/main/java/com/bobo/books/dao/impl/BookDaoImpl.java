package com.bobo.books.dao.impl;

import com.bobo.books.bean.Book;
import com.bobo.books.dao.IBookDao;
import com.bobo.books.utils.DelFlagE;
import com.bobo.books.utils.MyDBUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * BookDao的实现
 */
public class BookDaoImpl implements IBookDao {

    String sql ;
    QueryRunner queryRunner ;

    @Override
    public List<Book> list(Book book) {
        queryRunner = MyDBUtils.getQueryRunner();
        sql = "select * from t_book where is_deleted = ?";
        try {
            return queryRunner.query(sql, new ResultSetHandler<List<Book>>() {
                @Override
                public List<Book> handle(ResultSet rs) throws SQLException {
                    List<Book> list = new ArrayList<>();
                    while(rs.next()){
                        Book b = new Book();
                        b.setId(rs.getInt("id"));
                        b.setBookName(rs.getString("book_name"));
                        b.setAuthor(rs.getString("author"));
                        b.setPublish(rs.getString("publish"));
                        b.setIsbn(rs.getString("isbn"));
                        String introduction = rs.getString("introduction");
                        if(introduction != null && !"".equals(introduction) && introduction.length() > 20){
                            introduction = rs.getString("introduction").substring(0,20);
                        }
                        b.setIntroduction(introduction+"...");
                        b.setLanguage(rs.getString("language"));
                        b.setPrice(rs.getBigDecimal("price"));
                        b.setPubdate(rs.getDate("pubdate"));
                        b.setPressmark(rs.getString("pressmark"));
                        b.setIsDeleted(rs.getInt("is_deleted"));
                        b.setState(rs.getInt("state"));
                        list.add(b);
                    }
                    return list;
                }
            }, DelFlagE.NO.code);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer saveBook(Book book) {
        queryRunner = MyDBUtils.getQueryRunner();
        sql = "insert into t_book(book_name,author,publish,isbn,introduction,language,price,pubdate,pressmark)values(?,?,?,?,?,?,?,?,?)";
        try {
            return queryRunner.update(sql,book.getBookName(),
                                    book.getAuthor(),
                                    book.getPublish(),
                                    book.getIsbn(),
                                    book.getIntroduction(),
                                    book.getLanguage(),
                                    book.getPrice(),
                                    book.getPubdate(),
                                    book.getPressmark());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return -1;
    }

    public static void main(String[] args) {
        BookDaoImpl dao = new BookDaoImpl();
        Book book = new Book();
        book.setId(1);
        book.setBookName("时间简史");
        book.setAuthor("霍金");
        book.setPrice(new BigDecimal(59.9));
        book.setLanguage("中文");
        Calendar calendar = Calendar.getInstance();
        calendar.set(1997,4,6);
        book.setPubdate(calendar.getTime());
        book.setIntroduction("一本神器的书籍....66666");
        book.setIsbn("xxx0102123231");
        book.setPressmark("1-3-03");
        book.setPublish("清华出版社");
        //dao.saveBook(book);
         dao.updateBook(book);
        List<Book> list = dao.list(null);
        System.out.println(list);
    }

    @Override
    public Integer updateBook(Book book) {
        queryRunner = MyDBUtils.getQueryRunner();
        if(book.getIsDeleted() == null || "".equals(book.getIsDeleted())){
            book.setIsDeleted(DelFlagE.NO.code);
        }
        if(book.getState() == null || "".equals(book.getState())){
            book.setState(0);
        }
        sql = "update t_book set book_name = ? ,author=?,publish=?,isbn=?,introduction=?,language=?,price=?,pubdate=?,state=? ,is_deleted=? where id = ? ";
        try {
            return queryRunner.update(sql,book.getBookName(),
                    book.getAuthor(),
                    book.getPublish(),
                    book.getIsbn(),
                    book.getIntroduction(),
                    book.getLanguage(),
                    book.getPrice(),
                    book.getPubdate(),
                    book.getState(),
                    book.getIsDeleted(),
                    book.getId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return -1;
    }

    @Override
    public Integer deleteById(Integer id) {
        queryRunner = MyDBUtils.getQueryRunner();
        sql = "update t_book set is_deleted = ? where id = ?";
        try {
            return queryRunner.update(sql,DelFlagE.YES.code,id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return -1;
    }

    @Override
    public Book queryById(Integer id) {
        queryRunner = MyDBUtils.getQueryRunner();
        sql = "select * from t_book where is_deleted = ? and id = ?";
        try {
            return queryRunner.query(sql, new ResultSetHandler<Book>() {
                @Override
                public Book handle(ResultSet rs) throws SQLException {
                    if(rs.next()){
                        Book b = new Book();
                        b.setId(rs.getInt("id"));
                        b.setBookName(rs.getString("book_name"));
                        b.setAuthor(rs.getString("author"));
                        b.setPublish(rs.getString("publish"));
                        b.setIsbn(rs.getString("isbn"));
                        String introduction = rs.getString("introduction");
                        b.setIntroduction(introduction);
                        b.setLanguage(rs.getString("language"));
                        b.setPrice(rs.getBigDecimal("price"));
                        b.setPubdate(rs.getDate("pubdate"));
                        b.setPressmark(rs.getString("pressmark"));
                        b.setIsDeleted(rs.getInt("is_deleted"));
                        b.setState(rs.getInt("state"));
                        return b;
                    }
                    return null;
                }
            }, DelFlagE.NO.code,id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Book> queryListByState(int code) {
        queryRunner = MyDBUtils.getQueryRunner();
        sql = "select * from t_book where is_deleted = ? and state = ?";
        try {
            return queryRunner.query(sql, new ResultSetHandler<List<Book>>() {
                @Override
                public List<Book> handle(ResultSet rs) throws SQLException {
                    List<Book> list = new ArrayList<>();
                    while(rs.next()){
                        Book b = new Book();
                        b.setId(rs.getInt("id"));
                        b.setBookName(rs.getString("book_name"));
                        b.setAuthor(rs.getString("author"));
                        b.setPublish(rs.getString("publish"));
                        b.setIsbn(rs.getString("isbn"));
                        String introduction = rs.getString("introduction");
                        if(introduction != null && !"".equals(introduction) && introduction.length() > 20){
                            introduction = rs.getString("introduction").substring(0,20);
                        }
                        b.setIntroduction(introduction+"...");
                        b.setLanguage(rs.getString("language"));
                        b.setPrice(rs.getBigDecimal("price"));
                        b.setPubdate(rs.getDate("pubdate"));
                        b.setPressmark(rs.getString("pressmark"));
                        b.setIsDeleted(rs.getInt("is_deleted"));
                        b.setState(rs.getInt("state"));
                        list.add(b);
                    }
                    return list;
                }
            }, DelFlagE.NO.code,code);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Book> queryBookByKeyword(String keyword) {
        queryRunner = MyDBUtils.getQueryRunner();
        sql = "select * from t_book where is_deleted = ? and book_name like ?";
        try {
            return queryRunner.query(sql, new ResultSetHandler<List<Book>>() {
                @Override
                public List<Book> handle(ResultSet rs) throws SQLException {
                    List<Book> list = new ArrayList<>();
                    while(rs.next()){
                        Book b = new Book();
                        b.setId(rs.getInt("id"));
                        b.setBookName(rs.getString("book_name"));
                        b.setAuthor(rs.getString("author"));
                        b.setPublish(rs.getString("publish"));
                        b.setIsbn(rs.getString("isbn"));
                        String introduction = rs.getString("introduction");
                        if(introduction != null && !"".equals(introduction) && introduction.length() > 20){
                            introduction = rs.getString("introduction").substring(0,20);
                        }
                        b.setIntroduction(introduction+"...");
                        b.setLanguage(rs.getString("language"));
                        b.setPrice(rs.getBigDecimal("price"));
                        b.setPubdate(rs.getDate("pubdate"));
                        b.setPressmark(rs.getString("pressmark"));
                        b.setIsDeleted(rs.getInt("is_deleted"));
                        b.setState(rs.getInt("state"));
                        list.add(b);
                    }
                    return list;
                }
            }, DelFlagE.NO.code,"%"+keyword+"%");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
