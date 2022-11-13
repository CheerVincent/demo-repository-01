package com.bobo.books.servlet;

import com.alibaba.fastjson.JSON;
import com.bobo.books.bean.Book;
import com.bobo.books.bean.Classes;
import com.bobo.books.bean.Department;
import com.bobo.books.bean.Student;
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

@WebServlet(name = "studentServlet",urlPatterns = "/studentServlet")
public class StudentServlet extends HttpServlet {

    IStudentService studentService = new StudentServiceImpl();

    IDepartmentService departmentService = new DepartmentServiceImpl();

    IClassService classService = new ClassServiceImpl();

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
                saveOrUpdate(req, resp);
            }else if(Constant.SERVLET_TYPE_UPDATE.equals(type)){
                saveOrUpdate(req, resp);
            }else if(Constant.SERVLET_TYPE_DELETE.equals(type)){
                String id = req.getParameter("id");
                studentService.deleteById(Integer.parseInt(id));
                resp.sendRedirect("/studentServlet?type=query");
            }else if(Constant.SERVLET_TYPE_QUERYBYID.equals(type)){
                queryById(req, resp);
            }else if("saveBefor".equals(type)){
                // 添加操作之前 我们需要查询出所有的 院系信息
                List<Department> depts = departmentService.list(null);
                req.setAttribute("depts",depts);
                req.getRequestDispatcher("/student/studentUpdate.jsp").forward(req,resp);
            }else if("search".equals(type)){
                // 查询的关键字
                String keyword = req.getParameter("keyword");
                List<Student> list =studentService.queryStudentByKeyword(keyword);
                req.setAttribute("list",list);
                req.setAttribute("keyword",keyword);
                req.getRequestDispatcher("/student/student.jsp").forward(req, resp);
            }else  if("queryClassByDepartId".equals(type)){
                // ajax 异步提交 根据院系编号查询对应的班级信息
                String departId = req.getParameter("departId");
                // 根据院系编号查询对应的班级信息
                List<Classes> classes = classService.queryByDepartId(Integer.parseInt(departId));
                // 把集合数据转换为json数据响应
                String json = JSON.toJSONString(classes);
                resp.setContentType("application/json;charset=UTF-8");
                PrintWriter writer = resp.getWriter();
                writer.println(json);
                writer.flush();
            }else{
                queryList(req, resp);
            }
        }else{
            queryList(req, resp);
        }
    }

    private void queryById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 根据id查询出学生的相关信息
        String id = req.getParameter("id");
        Student student = studentService.queryById(Integer.parseInt(id));
        req.setAttribute("stu",student);
        List<Department> depts = departmentService.list(null);
        req.setAttribute("depts",depts);
        req.getRequestDispatcher("/student/studentUpdate.jsp").forward(req, resp);
    }

    private void saveOrUpdate(HttpServletRequest req, HttpServletResponse resp) {
        // 保存学生信息
        try {
            Student student = RequestParameterUtils.getRequestParameterForReflect(req, Student.class);
            // 根据院系编号查询名称
            if(student.getDepartId() != null){
                Department dept = departmentService.queryById(student.getDepartId());
                student.setDepartName(dept.getDepartment());
            }
            if(student.getClassId() != null){
                Classes classes = classService.queryById(student.getClassId());
                student.setClassName(classes.getClassName());
            }
            if(student.getId() != null && student.getId() > 0){
                // 更新处理
                studentService.updateStudent(student);
            }else{
                // 添加操作
                studentService.saveStudent(student);
            }

            resp.sendRedirect("/studentServlet?type=query");
            // 根据班级编号查询名称
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void queryList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 查询学生信息
        List<Student> list = studentService.list(null);
        // 把list保存在Request作用域中
        req.setAttribute("list",list);
        req.getRequestDispatcher("/student/student.jsp").forward(req, resp);
    }
}
