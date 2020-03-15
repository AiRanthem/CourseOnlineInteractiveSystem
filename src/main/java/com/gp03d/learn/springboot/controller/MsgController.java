package com.gp03d.learn.springboot.controller;


import com.gp03d.learn.springboot.dao.*;
import com.gp03d.learn.springboot.entities.GroupCourse;
import com.gp03d.learn.springboot.entities.GroupMsg;
import com.gp03d.learn.springboot.entities.Msg;
import com.gp03d.learn.springboot.entities.SCT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collection;

@Controller
public class MsgController {
    @Autowired
    GroupMsgDao groupMsgDao;
    @Autowired
    GroupCourseDao groupCourseDao;
    @Autowired
    CourseDao courseDao;
    @Autowired
    SCTDao sctDao;

    @GetMapping("/student_course")
    public String CL(Model model, HttpSession session){
        Collection<SCT> scts = sctDao.getAll(session.getAttribute("account").toString());
        model.addAttribute("scts",scts);

        return "msg/course";
    }

    @GetMapping("/group_{id}")
    public String gotoGroup( @PathVariable("id") String CID,
            Model model){

        Collection<GroupCourse> groups = groupCourseDao.getAll(CID);
        model.addAttribute("groups",groups);
        model.addAttribute("CID",CID);
        return "msg/group";
    }

    @GetMapping("/message_{id}")
    public String gotoMsg(@PathVariable("id") String GID, Model model){
        Collection<Msg> msgs = groupMsgDao.getAll(GID);
        model.addAttribute("msgs",msgs);
        model.addAttribute("GID",GID);

        return "msg/message";
    }

    @PostMapping("/message")
    public String leaveMsg(HttpServletRequest request, HttpSession session){

        String message = request.getParameter("message");
        String mGID = request.getParameter("mGID");

        groupMsgDao.save(session.getAttribute("account").toString(),session.getAttribute("loginUser").toString(),message, mGID);

        return "redirect:/message_"+mGID;
    }

}
