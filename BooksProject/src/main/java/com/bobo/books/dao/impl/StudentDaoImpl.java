package com.bobo.books.dao.impl;

import com.bobo.books.bean.Student;
import com.bobo.books.dao.IStudentDao;
import com.bobo.books.utils.DelFlagE;
import com.bobo.books.utils.MyDBUtils;
import com.mysql.cj.protocol.a.MysqlBinaryValueDecoder;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements IStudentDao {

    QueryRunner queryRunner ;
    String sql;

    @Override
    public List<Student> list(Student stu) {
        queryRunner = MyDBUtils.getQueryRunner();
        sql = "select * from t_student where is_deleted = ?";
        try {
            return queryRunner.query(sql, new ResultSetHandler<List<Student>>() {
                @Override
                public List<Student> handle(ResultSet rs) throws SQLException {
                    List<Student> list = new ArrayList<>();
                    while(rs.next()){
                        Student student = new Student();
                        student.setId(rs.getInt("id"));
                        student.setStuNum(rs.getString("stu_num"));
                        student.setStuName(rs.getString("stu_name"));
                        student.setAddress(rs.getString("address"));
                        student.setPhoneNum(rs.getString("phone_num"));
                        student.setGender(rs.getString("gender"));
                        student.setDepartId(rs.getInt("departid"));
                        student.setDepartName(rs.getString("depart_name"));
                        student.setClassId(rs.getInt("classid"));
                        student.setClassName(rs.getString("class_name"));
                        student.setIsDeleted(rs.getInt("is_deleted"));
                        list.add(student);
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
    public Integer saveStudent(Student stu) {
        queryRunner = MyDBUtils.getQueryRunner();
        sql = "insert into t_student(stu_num,stu_name,phone_num,gender,address,classid,class_name,departid,depart_name)values(?,?,?,?,?,?,?,?,?)";
        try {
            return queryRunner.update(sql,stu.getStuNum(),
                    stu.getStuName(),
                    stu.getPhoneNum(),
                    stu.getGender(),
                    stu.getAddress(),
                    stu.getClassId(),
                    stu.getClassName(),
                    stu.getDepartId(),
                    stu.getDepartName());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return -1;
    }

    @Override
    public Integer updateStudent(Student stu) {
        queryRunner = MyDBUtils.getQueryRunner();
        sql = "update t_student set stu_num = ?,stu_name=?,phone_num=?,gender=?,address=?,classid=?,class_name=?,departid=?,depart_name=? where id = ?";
        try {
            return queryRunner.update(sql,stu.getStuNum(),
                    stu.getStuName(),
                    stu.getPhoneNum(),
                    stu.getGender(),
                    stu.getAddress(),
                    stu.getClassId(),
                    stu.getClassName(),
                    stu.getDepartId(),
                    stu.getDepartName(),
                    stu.getId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return -1;
    }

    @Override
    public Integer deleteById(Integer id) {

        queryRunner = MyDBUtils.getQueryRunner();
        sql = "update t_student set is_deleted = ?  where id = ?";
        try {
            return queryRunner.update(sql,DelFlagE.YES.code,id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return -1;
    }

    @Override
    public Student queryById(Integer id) {
        queryRunner = MyDBUtils.getQueryRunner();
        sql = "select * from t_student where is_deleted = ? and id = ?";
        try {
            return queryRunner.query(sql, new ResultSetHandler<Student>() {
                @Override
                public Student handle(ResultSet rs) throws SQLException {
                    if(rs.next()){
                        Student student = new Student();
                        student.setId(rs.getInt("id"));
                        student.setStuNum(rs.getString("stu_num"));
                        student.setStuName(rs.getString("stu_name"));
                        student.setAddress(rs.getString("address"));
                        student.setPhoneNum(rs.getString("phone_num"));
                        student.setGender(rs.getString("gender"));
                        student.setDepartId(rs.getInt("departid"));
                        student.setDepartName(rs.getString("depart_name"));
                        student.setClassId(rs.getInt("classid"));
                        student.setClassName(rs.getString("class_name"));
                        student.setIsDeleted(rs.getInt("is_deleted"));
                        return student;
                    }
                    return null;
                }
            }, DelFlagE.NO.code,id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    /**
     * 统计班级下的学生个数
     * @param classId
     * @return
     */
    @Override
    public int queryByClassIdCount(String classId) {
        queryRunner = MyDBUtils.getQueryRunner();
        sql = "select count(1) from t_student where is_deleted = ? and classid = ?";
        try {
            return queryRunner.query(sql, new ResultSetHandler<Integer>() {
                @Override
                public Integer handle(ResultSet rs) throws SQLException {
                    rs.next();
                    return rs.getInt(1);
                }
            },DelFlagE.NO.code,classId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    @Override
    public Student queryByPhone(String phone) {
        queryRunner = MyDBUtils.getQueryRunner();
        sql = "select * from t_student where is_deleted = ? and phone_num = ?";
        try {
            return queryRunner.query(sql, new ResultSetHandler<Student>() {
                @Override
                public Student handle(ResultSet rs) throws SQLException {
                    if(rs.next()){
                        Student student = new Student();
                        student.setId(rs.getInt("id"));
                        student.setStuNum(rs.getString("stu_num"));
                        student.setStuName(rs.getString("stu_name"));
                        student.setAddress(rs.getString("address"));
                        student.setPhoneNum(rs.getString("phone_num"));
                        student.setGender(rs.getString("gender"));
                        student.setDepartId(rs.getInt("departid"));
                        student.setDepartName(rs.getString("depart_name"));
                        student.setClassId(rs.getInt("classid"));
                        student.setClassName(rs.getString("class_name"));
                        student.setIsDeleted(rs.getInt("is_deleted"));
                        return student;
                    }
                    return null;
                }
            },DelFlagE.NO.code,phone);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Student> queryStudentByKeyword(String keyword) {
        queryRunner = MyDBUtils.getQueryRunner();
        sql = "select * from t_student where is_deleted = ? and (stu_name like ? or address like ? )";
        try {
            return queryRunner.query(sql, new ResultSetHandler<List<Student>>() {
                @Override
                public List<Student> handle(ResultSet rs) throws SQLException {
                    List<Student> list = new ArrayList<>();
                    while(rs.next()){
                        Student student = new Student();
                        student.setId(rs.getInt("id"));
                        student.setStuNum(rs.getString("stu_num"));
                        student.setStuName(rs.getString("stu_name"));
                        student.setAddress(rs.getString("address"));
                        student.setPhoneNum(rs.getString("phone_num"));
                        student.setGender(rs.getString("gender"));
                        student.setDepartId(rs.getInt("departid"));
                        student.setDepartName(rs.getString("depart_name"));
                        student.setClassId(rs.getInt("classid"));
                        student.setClassName(rs.getString("class_name"));
                        student.setIsDeleted(rs.getInt("is_deleted"));
                        list.add(student);
                    }
                    return list;
                }
            },DelFlagE.NO.code,"%"+keyword+"%","%"+keyword+"%");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
