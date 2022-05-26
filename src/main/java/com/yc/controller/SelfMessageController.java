package com.yc.controller;

import com.yc.bean.JsonModel;
import com.yc.bean.student;
import com.yc.bean.teacher;
import com.yc.biz.SelfMessagesBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@RestController
public class SelfMessageController {
    @Autowired
    SelfMessagesBiz selfMessagesBiz;

    @RequestMapping("/teacherselfMessages")
    public JsonModel teacherselfMessages(HttpSession session) {
        JsonModel jm = new JsonModel();
        teacher th = (teacher) session.getAttribute("loginadmin");
        try {
            teacher th1 = selfMessagesBiz.teacherselfmessages(th.getTeacherid());
            if (th1 != null) {
                jm.setCode(1);
                jm.setData(th1);
            } else {
                jm.setCode(0);
            }
            return jm;

        } catch (Exception ex) {
            jm.setCode(0);
            jm.setMsg(ex.getMessage());
            return jm;
        }
    }
    @RequestMapping("/updatateacherselfmessages")
    public JsonModel updatateacherselfmessages(String teacherid,String teachername,String personid,String photo,String birthday,String sex,String natives,String college,String political,String intime,String address,String zip,String phone,String job,Integer type){
        JsonModel jm = new JsonModel();
        teacher teacher = new teacher();
        teacher.setTeacherid(teacherid);
        teacher.setTeachername(teachername);
        teacher.setPersonid(personid);
        teacher.setPhoto(photo);
        teacher.setBirthday(birthday);
        teacher.setSex(sex);
        teacher.setNatives(natives);
        teacher.setCollege(college);
        teacher.setPolitical(political);
        teacher.setIntime(intime);
        teacher.setAddress(address);
        teacher.setZip(zip);
        teacher.setPhone(phone);
        teacher.setJob(job);
        teacher.setType(type);
        try{
            selfMessagesBiz.updatateacherselfmessages(teacher);
            jm.setCode(1);
            return jm;
        }catch (Exception ex){
            jm.setCode(0);
            jm.setMsg(ex.getMessage());
            return jm;
        }
    }
    @RequestMapping("/studentselfMessages")
    public JsonModel studentselfMessages(HttpSession session) {
        JsonModel jm = new JsonModel();
        student th = (student) session.getAttribute("loginadmin");
        try {
            student stu = selfMessagesBiz.studentselfmessages(th.getStudentid());
            if (stu != null) {
                jm.setCode(1);
                jm.setData(stu);
            } else {
                jm.setCode(0);
            }
            return jm;

        } catch (Exception ex) {
            jm.setCode(0);
            jm.setMsg(ex.getMessage());
            return jm;
        }
    }
    @RequestMapping("/updatastudentselfmessages")
    public JsonModel updatastudentselfmessages(String studentid,String studentname,String personid,String photo,String birthday,String sex,String natives,String political,String intime,String address,String zip,String phone,String parentphone){
        JsonModel jm = new JsonModel();
        student stu = new student();
        stu.setStudentid(studentid);
        stu.setStudentname(studentname);
        stu.setPersonid(personid);
        stu.setPhoto(photo);
        stu.setSex(sex);
        stu.setBirthday(birthday);
        stu.setNatives(natives);
        stu.setPolitical(political);
        stu.setIntime(intime);
        stu.setAddress(address);
        stu.setZip(zip);
        stu.setPhone(phone);
        stu.setParentphone(parentphone);
        try{
            selfMessagesBiz.updatastudentselfmessages(stu);
            jm.setCode(1);
            return jm;
        }catch (Exception ex){
            jm.setCode(0);
            jm.setMsg(ex.getMessage());
            return jm;
        }
    }
    @RequestMapping("/upload")
    public void upload(@RequestParam("uploadFile")MultipartFile uploadFile,HttpSession session){
       selfMessagesBiz.testUploadFile(uploadFile,session);
    }
    @RequestMapping("/geturl")
    public JsonModel geturl(HttpSession session){
        JsonModel jm = new JsonModel();
        jm.setCode(1);
        jm.setData(session.getAttribute("touxiang"));
        return jm;
    }



}
