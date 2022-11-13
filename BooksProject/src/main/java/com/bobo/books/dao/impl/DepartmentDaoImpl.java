package com.bobo.books.dao.impl;

import com.bobo.books.bean.Department;
import com.bobo.books.dao.IDepartmentDao;
import com.bobo.books.utils.DelFlagE;
import com.bobo.books.utils.MyDBUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDaoImpl implements IDepartmentDao {

    QueryRunner queryRunner ;
    String sql;

    @Override
    public List<Department> list(Department dept) {
        queryRunner = MyDBUtils.getQueryRunner();
        sql = "select * from t_department where is_deleted = ?";
        try {
            return queryRunner.query(sql, new ResultSetHandler<List<Department>>() {
                @Override
                public List<Department> handle(ResultSet rs) throws SQLException {
                    List<Department> list = new ArrayList<>();
                    while(rs.next()){
                        Department dept = new Department();
                        dept.setId(rs.getInt("id"));
                        dept.setDepartment(rs.getString("department"));
                        dept.setDeptDesc(rs.getString("dept_desc"));
                        dept.setIsDeleted(rs.getInt("is_deleted"));
                        list.add(dept);
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
    public Integer saveDepartment(Department dept) {
        queryRunner = MyDBUtils.getQueryRunner();
        sql = "insert into t_department(department,dept_desc)values(?,?)";
        try {
            return queryRunner.update(sql,dept.getDepartment(),dept.getDeptDesc());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return -1;
    }

    @Override
    public Integer updateDepratment(Department dept) {
        queryRunner = MyDBUtils.getQueryRunner();
        sql = "update t_department set department = ? , dept_desc = ? where id = ?";
        try {
            return queryRunner.update(sql,dept.getDepartment(),dept.getDeptDesc(),dept.getId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer deleteById(Integer id) {
        queryRunner = MyDBUtils.getQueryRunner();
        sql = "update t_department set is_deleted = ? where id = ?";
        try {
            return queryRunner.update(sql,DelFlagE.YES.code,id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return -1;
    }

    @Override
    public Department queryById(Integer id) {
        queryRunner = MyDBUtils.getQueryRunner();
        sql = "select * from t_department where is_deleted = ? and id = ?";
        try {
            return queryRunner.query(sql, new ResultSetHandler<Department>() {
                @Override
                public Department handle(ResultSet rs) throws SQLException {
                    if (rs.next()){
                        Department dept = new Department();
                        dept.setId(rs.getInt("id"));
                        dept.setDepartment(rs.getString("department"));
                        dept.setDeptDesc(rs.getString("dept_desc"));
                        dept.setIsDeleted(rs.getInt("is_deleted"));
                        return dept;
                    }
                    return null;
                }
            }, DelFlagE.NO.code,id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
