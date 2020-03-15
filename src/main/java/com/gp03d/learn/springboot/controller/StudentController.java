package com.gp03d.learn.springboot.controller;

import com.gp03d.learn.springboot.common.ErrorText;
import com.gp03d.learn.springboot.dao.*;
import com.gp03d.learn.springboot.entities.*;
import com.gp03d.learn.springboot.filestorage.FileStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

@Controller
public class StudentController {
    @Autowired
    StudentDao studentDao;
    @Autowired
    CourseDao courseDao;
    @Autowired
    TeacherDao teacherDao;
    @Autowired
    SCTDao sctDao;
    @Autowired
    DepartmentDao departmentDao;
    @Autowired
    FileStorage fileStorage;

    // 进入学生主页
    @GetMapping("/student_home")
    public String home(){
        return "student/home";
    }

    // 进入选课页面
    @GetMapping("/courseChoosing")
    public String toEditPage(Model model, HttpSession session) {
        String id = String.valueOf(session.getAttribute("account"));
        Student student = studentDao.get(id);
        model.addAttribute("emp",student);
        Map<String, Course> courseMap = courseDao.getAll();
        for(SCT sct : sctDao.getAll()){

            if(id.equals(sct.getSID())){
                courseMap.remove(sct.getCID());
            }
        }

        model.addAttribute("courses", courseMap.values());
        return "student/courseChoosing";
    }


    // 处理选课的逻辑
    @PutMapping("/courseChoosing_{cid}_{tid}")
    public String deleteEmployee(HttpServletRequest request,
                                 @PathVariable("cid") String CID,
                                 @PathVariable("tid") String TID,
                                 Model model,HttpSession session){
        String SID = String.valueOf(session.getAttribute("account"));

        Course course = courseDao.get(CID);
        //Teacher teacher=teacherDao.get(TID);
        if(course.getCnum() < course.getClimit()) {
            // 选课成功
            course.setCnum(course.getCnum() + 1);
            sctDao.chooseCourse(SID, CID,TID);
            model.addAttribute("err",new ErrorText("选课成功","继续选课"));
            return "info";
        }
        else{
            // 选课失败
            model.addAttribute("err",new ErrorText("课程人数已满，选课失败。","重新选课"));
            return "info";
        }
    }


    //处理作业上传的逻辑
    @GetMapping("/upload")
    public String index() {
        return "student/upload";
    }

    @PostMapping("/upload")
    public String uploadMultipartFile(@RequestParam("files") MultipartFile[] files, Model model) {
        List<String> fileNames = null;

        try {
            fileNames = Arrays.asList(files)
                    .stream()
                    .map(file -> {
                        fileStorage.store1(file); // 把文件拷贝到指定位置
                        return file.getOriginalFilename(); // 返回文件的名字
                    })
                    .collect(Collectors.toList());

            model.addAttribute("message", "文件上传成功!");
            model.addAttribute("files", fileNames);
        } catch (Exception e) {
            model.addAttribute("message", "Fail!");
            model.addAttribute("files", fileNames);
        }

        return "/student/upload";
    }

    //处理课件下载的逻辑
    /*
     * 浏览所有文件信息
     */
    @GetMapping("/download")
    public String getListFiles(Model model) {
        List<FileInfo> fileInfos = fileStorage.loadFiles2()
                .map(path -> {
                    String filename = path.getFileName().toString();
                    String url = MvcUriComponentsBuilder.fromMethodName(StudentController.class,
                            "downloadFile", path.getFileName().toString()).build().toString();
                    return new FileInfo(filename, url);
                })
                .collect(Collectors.toList());

        model.addAttribute("files", fileInfos);
        return "student/download";
    }

    /*
     * 下载文件
     */
    @GetMapping("/files/{filename}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename)
            throws ServletException, IOException
    {
        Resource file = fileStorage.loadFile2(filename); // 获取文件的资源
        // 下载到指定文件
        String fileName=new String(file.getFilename().getBytes("GBK"),"ISO-8859-1");

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + fileName+ "\"")
                .body(file);
    }

    //处理成绩查询的逻辑
    @GetMapping("/gradeInquiry")
    public String gradeInquiry() {
        return "student/gradeInquiry";
    }

    //处理个人信息的逻辑
    @GetMapping("/Info")
    public String Info() {
        return "student/Info";
    }
}
