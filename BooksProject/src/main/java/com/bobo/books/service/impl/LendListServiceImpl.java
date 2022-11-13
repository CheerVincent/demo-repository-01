package com.bobo.books.service.impl;

import com.bobo.books.bean.Book;
import com.bobo.books.bean.LendList;
import com.bobo.books.bean.ReaderCard;
import com.bobo.books.dao.ILendListDao;
import com.bobo.books.dao.impl.LendListDaoImpl;
import com.bobo.books.service.IBookService;
import com.bobo.books.service.ILendService;
import com.bobo.books.service.IReaderCardService;
import com.bobo.books.utils.BookFlagE;

public class LendListServiceImpl implements ILendService {

    ILendListDao lendListDao = new LendListDaoImpl();
    IReaderCardService readerCardService = new ReaderCardServiceImpl();
    IBookService bookService = new BookServiceImpl();

    @Override
    public Integer borrowLendList(LendList lendList) {
        // 更新 借书卡的状态和书籍的状态
        ReaderCard readerCard = readerCardService.queryById(Integer.parseInt(lendList.getCardId()));
        readerCard.setState(1);
        readerCardService.updateReaderCard(readerCard);

        Book book = bookService.queryById(lendList.getBookId());
        book.setState(BookFlagE.BORROW.code);
        bookService.updateBook(book);

        return lendListDao.borrowLendList(lendList);
    }

    @Override
    public Integer backLendList(LendList lendList) {
        // 更新 借书卡的状态和书籍的状态
        ReaderCard readerCard = readerCardService.queryById(Integer.parseInt(lendList.getCardId()));
        readerCard.setState(0);
        readerCardService.updateReaderCard(readerCard);

        Book book = bookService.queryById(lendList.getBookId());
        book.setState(BookFlagE.FREE.code);
        bookService.updateBook(book);
        return lendListDao.backLendList(lendList);
    }

    @Override
    public LendList queryByCardId(String cardId) {
        return lendListDao.queryByCardId(cardId);
    }
}
