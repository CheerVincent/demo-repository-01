package com.bobo.books.dao.impl;

import com.bobo.books.bean.ReaderCard;
import com.bobo.books.dao.IReaderCardDao;
import com.bobo.books.utils.DelFlagE;
import com.bobo.books.utils.MyDBUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReaderCardImpl implements IReaderCardDao {

    QueryRunner queryRunner = null;
    String sql = "";
    @Override
    public List<ReaderCard> list(ReaderCard card) {
        queryRunner = MyDBUtils.getQueryRunner();
        sql = "select * from t_reader_card where is_deleted = ? ";
        try {
            return queryRunner.query(sql, new ResultSetHandler<List<ReaderCard>>() {
                @Override
                public List<ReaderCard> handle(ResultSet rs) throws SQLException {
                    List<ReaderCard> list = new ArrayList<>();
                    while (rs.next()){
                        ReaderCard c = new ReaderCard();
                        c.setId(rs.getInt("id"));
                        c.setStuName(rs.getString("stu_name"));
                        c.setStuId(rs.getInt("stu_id"));
                        c.setPassword(rs.getString("password"));
                        c.setState(rs.getInt("state"));
                        c.setIsDeleted(rs.getInt("is_deleted"));
                        list.add(c);
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
    public Integer saveReaderCard(ReaderCard card) {
        queryRunner = MyDBUtils.getQueryRunner();
        sql = "insert into t_reader_card(stu_id,stu_name,password,state)values(?,?,?,?)";
        try {
            return queryRunner.update(sql,card.getStuId(),card.getStuName(),card.getPassword(),card.getState());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return -1;
    }

    @Override
    public Integer updateReaderCard(ReaderCard card) {
        queryRunner = MyDBUtils.getQueryRunner();
        sql = "update t_reader_card set stu_id=? ,stu_name=?,password=?,state=? where id = ?";
        try {
            return queryRunner.update(sql,card.getStuId(),card.getStuName(),card.getPassword(),card.getState(),card.getId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return -1;
    }

    @Override
    public Integer deleteById(Integer id) {
        queryRunner = MyDBUtils.getQueryRunner();
        sql = "update t_reader_card set is_deleted=? where id = ?";
        try {
            return queryRunner.update(sql,DelFlagE.YES.code,id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return -1;
    }

    @Override
    public ReaderCard queryById(Integer id) {
        queryRunner = MyDBUtils.getQueryRunner();
        sql = "select * from t_reader_card where is_deleted = ? and id = ? ";
        try {
            return queryRunner.query(sql, new ResultSetHandler<ReaderCard>() {
                @Override
                public ReaderCard handle(ResultSet rs) throws SQLException {
                    if (rs.next()){
                        ReaderCard c = new ReaderCard();
                        c.setId(rs.getInt("id"));
                        c.setStuName(rs.getString("stu_name"));
                        c.setStuId(rs.getInt("stu_id"));
                        c.setPassword(rs.getString("password"));
                        c.setState(rs.getInt("state"));
                        c.setIsDeleted(rs.getInt("is_deleted"));
                        return c;
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
    public List<ReaderCard> queryByStuId(Integer id) {
        queryRunner = MyDBUtils.getQueryRunner();
        sql = "select * from t_reader_card where is_deleted = ? and stu_id = ? ";
        try {
            return queryRunner.query(sql, new ResultSetHandler<List<ReaderCard>>() {
                @Override
                public List<ReaderCard> handle(ResultSet rs) throws SQLException {
                    List<ReaderCard> list = new ArrayList<>();
                    while (rs.next()){
                        ReaderCard c = new ReaderCard();
                        c.setId(rs.getInt("id"));
                        c.setStuName(rs.getString("stu_name"));
                        c.setStuId(rs.getInt("stu_id"));
                        c.setPassword(rs.getString("password"));
                        c.setState(rs.getInt("state"));
                        c.setIsDeleted(rs.getInt("is_deleted"));
                        list.add(c);
                    }
                    return list;
                }
            }, DelFlagE.NO.code,id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
