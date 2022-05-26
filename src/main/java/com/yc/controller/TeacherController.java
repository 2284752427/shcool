package com.yc.controller;

import com.yc.bean.JsonModel;
import com.yc.bean.teacher;
import com.yc.biz.TeacherBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
public class TeacherController {
    @Autowired
    TeacherBiz teacherBiz;

    @RequestMapping("/findteacherAll")
    public JsonModel findteacherAll(String job, String param, int pages, HttpSession session){
        JsonModel jm = new JsonModel();
        teacher th = (teacher) session.getAttribute("loginadmin");
        try{
            List<teacher> list = teacherBiz.findteacherAll(th.getCollege(),job,param,pages,session);
            if (list==null || list.isEmpty()==true){
                jm.setCode(0);
                jm.setMsg("查无此消息！");
                return jm;
            }else{
                jm.setCode((Integer) session.getAttribute("pagesnum"));
                jm.setData(list);
                return jm;
            }
        }catch (Exception ex){
            jm.setCode(0);
            jm.setMsg(ex.getMessage());
            return jm;
        }

    }
    @RequestMapping("/findjob")
    public JsonModel findjob(HttpSession session){
        JsonModel jm = new JsonModel();
        teacher th = (teacher) session.getAttribute("loginadmin");
        try{
            List<String> list = teacherBiz.findjob(th.getCollege());
            jm.setCode(1);
            jm.setData(list);
            return jm;
        }catch (Exception ex){
            jm.setCode(0);
            jm.setMsg(ex.getMessage());
            return jm;
        }
    }
    @RequestMapping("/updatateacher")
    public JsonModel updatateacher(String teacherid,String teacherpwd,String teachername,String personid,String birthday,String sex,String natives,String college,String political,String intime,String address,String zip,String phone,String job,Integer type){
        JsonModel jm = new JsonModel();
        teacher teacher = new teacher();
        teacher.setTeacherid(teacherid);
        teacher.setTeacherpwd(teacherpwd);
        teacher.setTeachername(teachername);
        teacher.setPersonid(personid);
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
            teacherBiz.updatateacher(teacher);
            jm.setCode(1);
            return jm;
        }catch (Exception ex){
            jm.setCode(0);
            jm.setMsg(ex.getMessage());
            return jm;
        }
    }
    @RequestMapping("/deleteteacher")
    public JsonModel deleteteacher(String[] tid){
        JsonModel jm = new JsonModel();
        List<String> list = new ArrayList<>();
        for (int i=0;i<tid.length;i++){
            list.add(tid[i]);
        }
        try{
            teacherBiz.deleteteacher(list);
            jm.setCode(1);
            return jm;
        }catch (Exception ex){
            jm.setCode(0);
            jm.setMsg(ex.getMessage());
            return jm;
        }
    }
    @RequestMapping("/pluscrouseteachername")
    public JsonModel pluscrouseteachername(String teacherid){
        JsonModel jm = new JsonModel();
        try{
            String s = teacherBiz.pluscrouseteachername(teacherid);
            jm.setCode(1);
            jm.setData(s);
        }catch (Exception ex){
            jm.setCode(0);
        }
        return jm;
    }
    @RequestMapping("/pluscrouseteacherid")
    public JsonModel pluscrouseteacherid(){
        JsonModel jm = new JsonModel();
        try{
            List<String> s = teacherBiz.pluscrouseteacherid();
            jm.setCode(1);
            jm.setData(s);
        }catch (Exception ex){
            jm.setCode(0);
        }
        return jm;
    }

}
