package com.bobo.books.service.impl;

import com.bobo.books.bean.ReaderCard;
import com.bobo.books.dao.IReaderCardDao;
import com.bobo.books.dao.impl.ReaderCardImpl;
import com.bobo.books.service.IReaderCardService;

import java.util.List;

public class ReaderCardServiceImpl implements IReaderCardService {

    IReaderCardDao cardDao = new ReaderCardImpl();

    @Override
    public List<ReaderCard> list(ReaderCard card) {

        return cardDao.list(card);
    }

    @Override
    public Integer saveReaderCard(ReaderCard card) {
        return cardDao.saveReaderCard(card);
    }

    @Override
    public Integer updateReaderCard(ReaderCard card) {
        return cardDao.updateReaderCard(card);
    }

    @Override
    public Integer deleteById(Integer id) {
        return cardDao.deleteById(id);
    }

    @Override
    public ReaderCard queryById(Integer id) {
        return cardDao.queryById(id);
    }

    @Override
    public List<ReaderCard> queryByStuId(Integer id) {
        return cardDao.queryByStuId(id);
    }
}
