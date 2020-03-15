package com.gp03d.learn.springboot.controller;

import com.gp03d.learn.springboot.dao.*;
import com.gp03d.learn.springboot.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.Map;

@Controller
public class LoginController {
    @Autowired
    TUserDao tUserDao;
    @Autowired
    SUserDao sUserDao;
    @Autowired
    DepartmentDao departmentDao;
    @Autowired
    StudentDao studentDao;
    @Autowired
    TeacherDao teacherDao;
    @Autowired
    STDao stDao;

    // 实现登录的逻辑
    @PostMapping(value = "/user/login")
    public String login(@RequestParam("account") String account,
                        @RequestParam("password") String password,
                        HttpServletRequest request,
                        Map<String, Object> map, HttpSession session) {
        String job_s = request.getParameter("job");
        int job = Integer.parseInt(job_s);
        String username = loginSucceed(account, password, job);
        if (!username.equals("_error")) {
            // 登录成功
            session.setAttribute("loginUser", username);
            session.setAttribute("job",job);
            session.setAttribute("account", account);
            if(job == 1)
                session.setAttribute("title","教师服务系统");
            else
                session.setAttribute("title","学生服务系统");
            return "redirect:/hello";
        } else {
            // 登录失败
            map.put("msg", "用户名或密码错误");
            return "login";
        }
    }

    // 登陆后用户分流
    @GetMapping("/hello")
    public String hello(HttpSession session){
        int job=Integer.parseInt(session.getAttribute("job").toString());
        if(job == 1){
            return "redirect:/teacher_home";
        }
        else{
            return "redirect:/student_home";
        }
    }

    // 判断是否登录成功并返回用户姓名，7.11改
    public String loginSucceed(String account, String password, int job) {
        if (job == 1) {
            Collection<TeacherUser> users = tUserDao.getAll();
            for (TeacherUser user : users) {
                if (account.equals(user.getTID()) && password.equals(user.getTPass()))
                    return user.getTname();
            }
            return "_error";
        }
        else {
            Collection<StudentUser> users = sUserDao.getAll();
            for (StudentUser user : users) {
                if (account.equals(user.getSID()) && password.equals(user.getSpass()))
                    return user.getSname();
            }
            return "_error";
        }
    }

    // 进入注册页面
    @GetMapping("/register")
    public String toRegisterPage() {
        return "/register";
    }

    // 实现注册的逻辑 老师1，学生0
    @PostMapping("/register")
    public String register(HttpServletRequest request, Model model, HttpSession session) {
        String account = request.getParameter("account");
        String password = request.getParameter("password");
        String job_s = request.getParameter("job");
        int job = Integer.parseInt(job_s);
        if(!accountAvailable(account,job)){
            model.addAttribute("msg","无效的账号！");
            return "redirect:/";
        }
        if (job == 1) {
            tUserDao.addUser(account, password);
            return "redirect:/";
        }
        else {
            sUserDao.addUser(account, password);
            model.addAttribute("sid",account);
            Collection<Department> departments = departmentDao.getAll();
            model.addAttribute("depts",departments);
            return "setInfo";
        }
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

    // 注册账号后添加用户
    @PostMapping("/setInfo")
    public String addEmp(HttpServletRequest request){
        studentDao.update(request);
        return "redirect:/";
    }

    // 用户登出
    @GetMapping("/signout")
    public String sighOut(HttpSession session)
    {
        session.removeAttribute("job");
        session.removeAttribute("loginUser");
        session.removeAttribute("id");
        session.removeAttribute("title");
        return "redirect:/";
    }

    // 管理员登陆页面
    @GetMapping("/admin")
    public String toAdminPage(){
        return "adminLogin";
    }

    // 管理员登陆的逻辑
    @PostMapping("/admin")
    public String adminLogin(HttpServletRequest request, Model model, HttpSession session){
        String passwd=request.getParameter("password");
        if(passwd.equals("GP03d"))
        {
            // 登陆成功
            session.setAttribute("loginUser", "admin");
            session.setAttribute("job",2);
            session.setAttribute("account", "admin");
            session.setAttribute("title","管理员系统");

            return "redirect:/studentManagement";
        }
        else
        {
            // 登陆失败
            model.addAttribute("msg","密码错误");
            return "adminLogin";
        }
    }
}
