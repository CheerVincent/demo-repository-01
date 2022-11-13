package com.bobo.books.service;

import com.bobo.books.bean.Book;
import com.bobo.books.bean.Department;

import java.util.List;

public interface IDepartmentService {

    public List<Department> list(Department dept);

    public Integer saveDepartment(Department dept);

    public Integer updateDepratment(Department dept);

    public Integer deleteById(Integer id);

    public Department queryById(Integer id);


}
