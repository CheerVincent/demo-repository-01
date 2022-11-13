package com.bobo.books.service.impl;

import com.bobo.books.bean.Classes;
import com.bobo.books.dao.IClassDao;
import com.bobo.books.dao.impl.ClassDaoImpl;
import com.bobo.books.service.IClassService;

import java.util.List;

public class ClassServiceImpl implements IClassService {
    IClassDao classDao = new ClassDaoImpl();

    @Override
    public List<Classes> list(Classes cls) {
        return classDao.list(cls);
    }

    @Override
    public Integer saveClasses(Classes cls) {
        return classDao.saveClasses(cls);
    }

    @Override
    public Integer updateClasses(Classes cls) {
        return classDao.updateClasses(cls);
    }

    @Override
    public Integer deleteById(Integer id) {
        return classDao.deleteById(id);
    }

    @Override
    public Classes queryById(Integer id) {
        return classDao.queryById(id);
    }

    @Override
    public List<Classes> queryByDepartId(Integer departId) {
        return classDao.queryByDepartId(departId);
    }
}
