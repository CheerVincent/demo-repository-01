package com.bobo.books.servlet;

import com.bobo.books.bean.Classes;
import com.bobo.books.bean.Department;
import com.bobo.books.service.IClassService;
import com.bobo.books.service.IDepartmentService;
import com.bobo.books.service.IStudentService;
import com.bobo.books.service.impl.ClassServiceImpl;
import com.bobo.books.service.impl.DepartmentServiceImpl;
import com.bobo.books.service.impl.StudentServiceImpl;
import com.bobo.books.utils.Constant;
import com.bobo.books.utils.RequestParameterUtils;
import com.bobo.books.utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "classServlet",urlPatterns = "/classServlet")
public class ClassServlet extends HttpServlet {

    IClassService classService = new ClassServiceImpl();

    IDepartmentService departmentService = new DepartmentServiceImpl();

    IStudentService studentService = new StudentServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1.获取请求的类型
        String type = req.getParameter(Constant.REQUEST_PARAMETER_TYPE);
        // 2.如果type不为空，那么根据type的不同值做出不同的操作
        if(StringUtils.isNotEmpty(type)){
            if(Constant.SERVLET_TYPE_QUEYR.equals(type)){
                queryList(req, resp);
            }else if(Constant.SERVLET_TYPE_SAVE.equals(type)){
                saveOrUpdateClass(req, resp);
            }else if(Constant.SERVLET_TYPE_UPDATE.equals(type)){
                // 更新数据
                saveOrUpdateClass(req, resp);
            }else if(Constant.SERVLET_TYPE_DELETE.equals(type)){
                deleteClass(req, resp);
            }else if(Constant.SERVLET_TYPE_QUERYBYID.equals(type)){
                // 进入修改页面前的操作  根据id查询对应的班级信息  同时还需要查询出所有的院系信息 更新的时候的下拉
                String id = req.getParameter("id");
                Classes classes = classService.queryById(Integer.parseInt(id));
                // 查询所有的院系信息
                List<Department> list = departmentService.list(null);
                req.setAttribute("cls",classes);
                req.setAttribute("depts",list);
                // 跳转到修改的页面
                req.getRequestDispatcher("/classes/classUpdate.jsp").forward(req,resp);
            }else if("queryAllDept".equals(type)){
                // 查询所有的院系信息
                List<Department> list = departmentService.list(null);
                req.setAttribute("depts",list);
                req.getRequestDispatcher("/classes/classUpdate.jsp").forward(req,resp);
            }else{
                queryList(req, resp);
            }
        }else{
            queryList(req, resp);
        }
    }

    private void deleteClass(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // 根据id 删除数据
        String id = req.getParameter("id");
        // 我们需要判断当前班级是否有学生？如果有就不让删除
        int count = studentService.queryByClassIdCount(id);
        if(count > 0){
            // 表示存在学生信息，不能删除班级记录
            // resp.setContentType("application/html;charset=UTF-8");
            resp.setContentType("text/html;charset=UTF-8");
            PrintWriter writer = resp.getWriter();
            writer.println("<script type='text/javascript'>");
            writer.println("alert('当前班级有学生不能删除!!!');");
            writer.println("location.href='/classServlet?type=query';");
            writer.println("</script>");
            writer.flush();
        }else{
            classService.deleteById(Integer.parseInt(id));
            resp.sendRedirect("/classServlet?type=query");
        }

    }

    private void saveOrUpdateClass(HttpServletRequest req, HttpServletResponse resp) {
        //保存 班级信息
        try {
            Classes cls = RequestParameterUtils.getRequestParameterForReflect(req, Classes.class);
            // 补充 院系的名称
            if(StringUtils.isEmpty(cls.getDeptName()) && cls.getDeptId() != null){
                // 根据院系编号查询出对应的院系名称
                Department department = departmentService.queryById(cls.getDeptId());
                cls.setDeptName(department.getDepartment());
            }
            if(cls.getId() != null && cls.getId() > 0){
                // 表示是更新操作
                classService.updateClasses(cls);
            }else{
                // 表示是添加操作
                // 保存班级信息
                classService.saveClasses(cls);
            }
            // 保存成功 就重定向查询
            resp.sendRedirect("/classServlet?type=query");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void queryList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Classes> list = classService.list(null);
        req.setAttribute("list",list);
        req.getRequestDispatcher("/classes/classes.jsp").forward(req, resp);
    }
}
