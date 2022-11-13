package com.bobo.books.dao.impl;

import com.bobo.books.bean.LendList;
import com.bobo.books.dao.ILendListDao;
import com.bobo.books.utils.MyDBUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class LendListDaoImpl implements ILendListDao {
    @Override
    public Integer borrowLendList(LendList lendList) {
        QueryRunner queryRunner = MyDBUtils.getQueryRunner();
        String sql = "insert into t_lend_list(book_id,card_id,lend_date)values(?,?,?)";
        try {
            return queryRunner.update(sql,lendList.getBookId(),lendList.getCardId(),lendList.getLendDate());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return -1;
    }

    @Override
    public Integer backLendList(LendList lendList) {
        QueryRunner queryRunner = MyDBUtils.getQueryRunner();
        String sql = "update t_lend_list set back_date=? where id = ?";
        try {
            return queryRunner.update(sql,lendList.getBackDate(),lendList.getId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public LendList queryByCardId(String cardId) {
        QueryRunner queryRunner = MyDBUtils.getQueryRunner();
        String sql = "select * from t_lend_list where card_id = ?";
        try {
            return queryRunner.query(sql, new ResultSetHandler<LendList>() {
                @Override
                public LendList handle(ResultSet rs) throws SQLException {
                    if(rs.next()){
                        LendList lendList = new LendList();
                        lendList.setId(rs.getInt("id"));
                        lendList.setCardId(cardId);
                        lendList.setBookId(rs.getInt("book_id"));
                        lendList.setBackDate(new Date());
                        lendList.setLendDate(rs.getDate("lend_date"));
                        return lendList;
                    }
                    return null;
                }
            },cardId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
