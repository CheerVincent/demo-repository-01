package com.bobo.books.service;

import com.bobo.books.bean.Book;

import java.util.List;

public interface IBookService {
    /**
     * 查询所有的书籍信息
     * @return
     */
    public List<Book> list(Book book);

    /**
     * 添加书籍信息
     * @param book
     * @return
     */
    public Integer saveBook(Book book);

    /**
     * 更新书籍信息
     * @param book
     * @return
     */
    public Integer updateBook(Book book);

    /**
     * 根据编号删除书籍信息
     * @param id
     * @return
     */
    public Integer deleteById(Integer id);


    public Book queryById(Integer id);

    List<Book> queryListByState(int code);

    List<Book> queryBookByKeyword(String keyword);
}
