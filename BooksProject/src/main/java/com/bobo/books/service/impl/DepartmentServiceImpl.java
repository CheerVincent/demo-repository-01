package com.bobo.books.service.impl;

import com.bobo.books.bean.Book;
import com.bobo.books.bean.Department;
import com.bobo.books.dao.IDepartmentDao;
import com.bobo.books.dao.impl.DepartmentDaoImpl;
import com.bobo.books.service.IDepartmentService;

import java.util.List;

public class DepartmentServiceImpl implements IDepartmentService {

    IDepartmentDao departmentDao = new DepartmentDaoImpl();

    @Override
    public List<Department> list(Department dept) {
        return departmentDao.list(dept);
    }

    @Override
    public Integer saveDepartment(Department dept) {
        return departmentDao.saveDepartment(dept);
    }

    @Override
    public Integer updateDepratment(Department dept) {
        return departmentDao.updateDepratment(dept);
    }

    @Override
    public Integer deleteById(Integer id) {
        return departmentDao.deleteById(id);
    }

    @Override
    public Department queryById(Integer id) {
        return departmentDao.queryById(id);
    }


}
