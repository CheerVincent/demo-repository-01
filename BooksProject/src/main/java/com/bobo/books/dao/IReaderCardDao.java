package com.bobo.books.dao;

import com.bobo.books.bean.ReaderCard;

import java.util.List;

/**
 * 借书卡的Dao接口
 */
public interface IReaderCardDao {

    public List<ReaderCard> list(ReaderCard card);

    public Integer saveReaderCard(ReaderCard card);

    public Integer updateReaderCard(ReaderCard card);

    public Integer deleteById(Integer id);

    public ReaderCard queryById(Integer id);


    List<ReaderCard> queryByStuId(Integer id);
}
