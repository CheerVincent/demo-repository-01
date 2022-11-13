package com.bobo.books.service;

import com.bobo.books.bean.Student;

import java.util.List;

public interface IStudentService {
    public List<Student> list(Student stu);

    public Integer saveStudent(Student stu);

    public Integer updateStudent(Student stu);

    public Integer deleteById(Integer id);

    public Student queryById(Integer id);

    int queryByClassIdCount(String id);

    Student queryByPhone(String phone);

    List<Student> queryStudentByKeyword(String keyword);
}
