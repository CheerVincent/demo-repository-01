package com.bobo.books.service;

import com.bobo.books.bean.ReaderCard;

import java.util.List;

public interface IReaderCardService {


    public List<ReaderCard> list(ReaderCard card);

    public Integer saveReaderCard(ReaderCard card);

    public Integer updateReaderCard(ReaderCard card);

    public Integer deleteById(Integer id);

    public ReaderCard queryById(Integer id);

    List<ReaderCard> queryByStuId(Integer id);
}
