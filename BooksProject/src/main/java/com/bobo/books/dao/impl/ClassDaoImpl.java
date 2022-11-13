package com.bobo.books.dao.impl;

import com.bobo.books.bean.Classes;
import com.bobo.books.dao.IClassDao;
import com.bobo.books.utils.DelFlagE;
import com.bobo.books.utils.MyDBUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClassDaoImpl implements IClassDao {

    QueryRunner queryRunner ;
    String sql;

    @Override
    public List<Classes> list(Classes cls) {
        queryRunner = MyDBUtils.getQueryRunner();
        sql = "select * from t_class where is_deleted = ?";
        try {
            return queryRunner.query(sql, new ResultSetHandler<List<Classes>>() {
                @Override
                public List<Classes> handle(ResultSet rs) throws SQLException {
                    List<Classes> list = new ArrayList<>();
                    while(rs.next()){
                        Classes cls = new Classes();
                        cls.setId(rs.getInt("id"));
                        cls.setClassName(rs.getString("class_name"));
                        cls.setClassDesc(rs.getString("class_desc"));
                        cls.setDeptId(rs.getInt("dept_id"));
                        cls.setDeptName(rs.getString("dept_name"));
                        cls.setIsDeleted(rs.getInt("is_deleted"));
                        list.add(cls);
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
    public Integer saveClasses(Classes cls) {
        queryRunner = MyDBUtils.getQueryRunner();
        sql = "insert into t_class(class_name,class_desc,dept_id,dept_name)values(?,?,?,?)";
        try {
            return queryRunner.update(sql,cls.getClassName(),cls.getClassDesc(),cls.getDeptId(),cls.getDeptName());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return -1;
    }

    @Override
    public Integer updateClasses(Classes cls) {
        queryRunner = MyDBUtils.getQueryRunner();
        sql = "update t_class set class_name = ? , class_desc = ? ,dept_id=? ,dept_name = ? where id = ?";
        try {
            return queryRunner.update(sql,cls.getClassName(),cls.getClassDesc(),cls.getDeptId(),cls.getDeptName(),cls.getId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return -1;
    }

    @Override
    public Integer deleteById(Integer id) {
        queryRunner = MyDBUtils.getQueryRunner();
        sql = "update t_class set is_deleted = ?  where id = ?";
        try {
            return queryRunner.update(sql,DelFlagE.YES.code,id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return -1;
    }

    @Override
    public Classes queryById(Integer id) {
        queryRunner = MyDBUtils.getQueryRunner();
        sql = "select * from t_class where is_deleted = ? and id = ?";
        try {
            return queryRunner.query(sql, new ResultSetHandler<Classes>() {
                @Override
                public Classes handle(ResultSet rs) throws SQLException {
                    if(rs.next()){
                        Classes cls = new Classes();
                        cls.setId(rs.getInt("id"));
                        cls.setClassName(rs.getString("class_name"));
                        cls.setClassDesc(rs.getString("class_desc"));
                        cls.setDeptId(rs.getInt("dept_id"));
                        cls.setDeptName(rs.getString("dept_name"));
                        cls.setIsDeleted(rs.getInt("is_deleted"));
                        return cls;
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
    public List<Classes> queryByDepartId(Integer departId) {
        queryRunner = MyDBUtils.getQueryRunner();
        sql = "select * from t_class where is_deleted = ? and dept_id = ?";
        try {
            return queryRunner.query(sql, new ResultSetHandler<List<Classes>>() {
                @Override
                public List<Classes> handle(ResultSet rs) throws SQLException {
                    List<Classes> list = new ArrayList<>();
                    while (rs.next()){
                        Classes cls = new Classes();
                        cls.setId(rs.getInt("id"));
                        cls.setClassName(rs.getString("class_name"));
                        cls.setClassDesc(rs.getString("class_desc"));
                        cls.setDeptId(rs.getInt("dept_id"));
                        cls.setDeptName(rs.getString("dept_name"));
                        cls.setIsDeleted(rs.getInt("is_deleted"));
                        list.add(cls);
                    }
                    return list;
                }
            }, DelFlagE.NO.code,departId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
