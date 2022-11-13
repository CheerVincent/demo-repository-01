package com.bobo.books.servlet;

import com.bobo.books.bean.Classes;
import com.bobo.books.bean.Department;
import com.bobo.books.service.IClassService;
import com.bobo.books.service.IDepartmentService;
import com.bobo.books.service.impl.ClassServiceImpl;
import com.bobo.books.service.impl.DepartmentServiceImpl;
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

@WebServlet(name = "departmentServlet",urlPatterns = "/departmentServlet")
public class DepartmentServlet extends HttpServlet {

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
                // 保存院系信息
                saveOrUpdateDept(req, resp);
            }else if(Constant.SERVLET_TYPE_UPDATE.equals(type)){
                saveOrUpdateDept(req, resp);
            }else if(Constant.SERVLET_TYPE_DELETE.equals(type)){
                deleteDepartment(req, resp);
            }else if(Constant.SERVLET_TYPE_QUERYBYID.equals(type)){
                queryById(req, resp);
            }else{
                queryList(req, resp);
            }
        }else{
            queryList(req, resp);
        }
    }

    private void queryById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 根据ID查询院系信息
        String id = req.getParameter("id");
        Department department = departmentService.queryById(Integer.parseInt(id));
        // 把数据保存在Request作用域中
        req.setAttribute("dept",department);
        req.getRequestDispatcher("/depart/deptUpdate.jsp").forward(req, resp);
    }

    private void deleteDepartment(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // 根据id 删除院系信息 -- 逻辑删除
        String id = req.getParameter("id");
        // 删除院系前我们同样需要判断该院系下是否有班级信息，如果有就不让删除
        List<Classes> classes = classService.queryByDepartId(Integer.parseInt(id));
        if( classes != null && classes.size() > 0){
            // 说明该院系下有学生，那么就不能删除
            resp.setContentType("text/html;charset=UTF-8");
            PrintWriter writer = resp.getWriter();
            writer.println("<script type='text/javascript'>");
            writer.println("alert('当前院系有班级不能删除!!!');");
            writer.println("location.href='/departmentServlet?type=query';");
            writer.println("</script>");
            writer.flush();
        }else{
            departmentService.deleteById(Integer.parseInt(id));
            resp.sendRedirect("/departmentServlet?type=query");
        }
    }

    private void saveOrUpdateDept(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Department dept = RequestParameterUtils.getRequestParameterForReflect(req,Department.class);
            if(dept.getId() != null && dept.getId() > 0){
                // 更新
                departmentService.updateDepratment(dept);
            }else{
                // 添加
                departmentService.saveDepartment(dept);
            }
            // 重定向做查询操作
            resp.sendRedirect("/departmentServlet?type=query");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void queryList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 查询出所有的院系信息
        List<Department> list = departmentService.list(null);
        // 存储在Request作用域中
        req.setAttribute("list",list);
        // 然后跳转到 院系 的展示页面
        req.getRequestDispatcher("/depart/department.jsp").forward(req, resp);
    }
}
