package com.gp03d.learn.springboot.controller;

import com.gp03d.learn.springboot.dao.CourseDao;
import com.gp03d.learn.springboot.dao.SCTDao;
import com.gp03d.learn.springboot.dao.TCDao;
import com.gp03d.learn.springboot.entities.Course;

import com.gp03d.learn.springboot.entities.SCT;
import com.gp03d.learn.springboot.entities.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collection;

@Controller
public class TeacherController {
    @Autowired
    CourseDao courseDao;
    @Autowired
    SCTDao sctDao;
    @Autowired
    TCDao tcDao;

    // 进入老师主页
    @GetMapping("/teacher_home")
    public String home(){
        return "teacher/home";
    }

    // 限制选课人数的页面
    @GetMapping("/courses")
    public String list(Model model, HttpSession session) {
        String TID =session.getAttribute("account").toString();
        Collection<Course> courses = tcDao.getAll(TID);
        model.addAttribute("courses", courses);
        return "teacher/courseLimit";
    }

    // 限制选课人数的逻辑
    @PutMapping("/course_lim/{cid}")
    public String updateLim(HttpServletRequest request,
                            @PathVariable("cid") String id){
        Integer limit = Integer.parseInt(request.getParameter("limit"));
        courseDao.setLimit(id,limit);

        return "redirect:/courses";
    }

    // 进入选课详情页面
    @GetMapping("/dashboard")
    public String scTable(Model model){
        Collection<SCT> scts = sctDao.getAll();
        model.addAttribute("scts",scts);
        // return "dashboard";
        return "teacher/list";
    }

    // 修改分数的逻辑
    @PostMapping("/chgrade")
    public String chgrade(HttpServletRequest request){

        return "redirect:/dashboard";
    }

}
