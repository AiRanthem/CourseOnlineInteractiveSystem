package com.gp03d.learn.springboot.controller;

import com.gp03d.learn.springboot.dao.*;
import com.gp03d.learn.springboot.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

@Controller
public class AdminController {
    @Autowired
    StudentDao studentDao;
    @Autowired
    DepartmentDao departmentDao;
    @Autowired
    SUserDao sUserDao;
    @Autowired
    TUserDao tUserDao;
    @Autowired
    STDao stDao;

    // 进入学生管理页面
    @GetMapping("/studentManagement")
    public String list(Model model, Model model1){
        Collection<Student> students = studentDao.getAll();
        model.addAttribute("students",students);
        return "admin/new_student_management";
    }

    // 修改学生的逻辑
    @PutMapping("/addStudent")
    public String updateEmployee(HttpServletRequest request){
        studentDao.update(request);
        return "redirect:/studentManagement";
    }

    // 添加学生的逻辑
    @PostMapping("/addStudent")
    public String addEmp(HttpServletRequest request, Model model){
        String account = request.getParameter("SID");
        String password = request.getParameter("Spass");
        if(!accountAvailable(account,0))
        {
            Collection<Department> departments = departmentDao.getAll();
            model.addAttribute("depts",departments);
            model.addAttribute("msg","无效的账号！");
            return "admin/add";
        }
        sUserDao.addUser(account, password);
        studentDao.update(request);
        return "redirect:/studentManagement";
    }

    // 进入添加页面
    @GetMapping("/addStudent")
    public String toAddPage(Model model)
    {
        Collection<Department> departments = departmentDao.getAll();
        model.addAttribute("depts",departments);
        return "admin/add";
    }

    // 进入修改页面
    @GetMapping("/editStudent_{id}")
    public String toEditPage(@PathVariable("id") String SID, Model model) {
        Student student = studentDao.get(SID);
        model.addAttribute("student",student);
        Collection<Department> departments = departmentDao.getAll();
        model.addAttribute("depts",departments);
        return "admin/add";
    }

    // 删除学生的逻辑
    @DeleteMapping("/deleteStudent")
    public String deleteEmployee(HttpServletRequest request){
        String SID = request.getParameter("SID");
        studentDao.delete(SID);
        return "redirect:/studentManagement";
    }

    // 进入教师管理页面
    @GetMapping("/admin_teacher")
    public String list01(Model model02){
        Collection<TeacherUser> teachers = tUserDao.getAll();
        model02.addAttribute("teas",teachers);
        return "admin/teacher_management";
    }

    // 判断用户名是否合法
    private boolean accountAvailable(String account,int job){
        if(job==1){
            for(ST usr:stDao.getAllTeacher()){
                if(account.equals(usr.getSTID()))
                    return true;
            }
            return false;
        }
        else{
            for(ST usr:stDao.getAllStudent()){
                if(account.equals(usr.getSTID()))
                    return true;
            }
            return false;
        }
    }
}
