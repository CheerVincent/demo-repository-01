package com.bobo.books.service.impl;

import com.bobo.books.bean.Student;
import com.bobo.books.bean.User;
import com.bobo.books.dao.IStudentDao;
import com.bobo.books.dao.impl.StudentDaoImpl;
import com.bobo.books.service.IStudentService;
import com.bobo.books.service.IUserService;

import java.util.List;

public class StudentServiceImpl implements IStudentService {
    IStudentDao studentDao = new StudentDaoImpl();

    IUserService userService = new UserServiceImpl();

    @Override
    public List<Student> list(Student stu) {
        return studentDao.list(stu);
    }

    @Override
    public Integer saveStudent(Student stu) {
        // 当我们创建一个学生信息的时候 同步创建该学生的账号信息 学生姓名【手机号】 密码是默认123456
        User user = new User() ;
        user.setPassword("123456");
        user.setUserName(stu.getPhoneNum());
        user.setPhoneNum(stu.getPhoneNum());
        userService.addUser(user); // 创建账号
        return studentDao.saveStudent(stu); // 保存学生信息
    }

    @Override
    public Integer updateStudent(Student stu) {
        return studentDao.updateStudent(stu);
    }

    @Override
    public Integer deleteById(Integer id) {
        return studentDao.deleteById(id);
    }

    @Override
    public Student queryById(Integer id) {
        return studentDao.queryById(id);
    }

    @Override
    public int queryByClassIdCount(String id) {
        return studentDao.queryByClassIdCount(id);
    }

    @Override
    public Student queryByPhone(String phone) {
        return studentDao.queryByPhone(phone);
    }

    @Override
    public List<Student> queryStudentByKeyword(String keyword) {
        return studentDao.queryStudentByKeyword(keyword);
    }
}
