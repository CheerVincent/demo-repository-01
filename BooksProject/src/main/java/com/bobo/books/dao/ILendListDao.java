package com.bobo.books.dao;

import com.bobo.books.bean.LendList;

public interface ILendListDao {
    public Integer borrowLendList(LendList lendList);

    public Integer backLendList(LendList lendList);

    LendList queryByCardId(String cardId);
}
