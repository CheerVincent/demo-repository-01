package com.bobo.books.service;

import com.bobo.books.bean.Classes;

import java.util.List;

public interface IClassService {
    public List<Classes> list(Classes cls);

    public Integer saveClasses(Classes cls);

    public Integer updateClasses(Classes cls);

    public Integer deleteById(Integer id);

    public Classes queryById(Integer id);

    public List<Classes> queryByDepartId(Integer departId);
}
