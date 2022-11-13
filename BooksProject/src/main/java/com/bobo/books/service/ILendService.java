package com.bobo.books.service;

import com.bobo.books.bean.LendList;

public interface ILendService {

    public Integer borrowLendList(LendList lendList);

    public Integer backLendList(LendList lendList);

    LendList queryByCardId(String cardId);
}
