package com.yc.controller;

import com.yc.bean.JsonModel;
import com.yc.bean.grades;
import com.yc.bean.student;
import com.yc.bean.teacher;
import com.yc.biz.StudentBiz;
import com.yc.biz.StudentsgradesBiz;
import com.yc.vo.allstudentgrades;
import com.yc.vo.studentsgrades;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class StudentsGradesConetroller {
    @Autowired
    StudentsgradesBiz studentsgradesBiz;
    @Autowired
    StudentBiz studentBiz;
    @RequestMapping("/findstudentsgrades")
    public JsonModel findAllgrades(String xq,String bj,String kc,String param,int pages, HttpSession session){
        JsonModel jm = new JsonModel();
        teacher th = (teacher) session.getAttribute("loginadmin");
            try{
                List<studentsgrades> list = studentsgradesBiz.findstudentgrades(th.getTeacherid(),xq,bj,kc,param,pages,session);
                if (list==null || list.isEmpty()==true){
                    jm.setCode(0);
                    jm.setMsg("查无此消息");
                    return jm;
                }else {
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


    @RequestMapping("/deletestudentsgrades")
    public JsonModel deletestudentsgrades(String[] studentid,String[] crouseid){
        JsonModel jm = new JsonModel();
        try{
            studentsgradesBiz.deletestudentsgrades(studentid,crouseid);
            jm.setCode(1);
            return jm;
        }catch (Exception ex){
            jm.setCode(0);
            jm.setMsg(ex.getMessage());
            return jm;
        }
    }
    @RequestMapping("/insertstudentsgrades")
    public JsonModel insertstudentsgrades(String studentid,String courseid,int  grade, float point){
        JsonModel jm = new JsonModel();
        grades grades = new grades();
        try{
            grades.setStudentid(studentid);
            grades.setCourseid(courseid);
            grades.setPoint(point);
            grades.setGrade(grade);
            int result = studentsgradesBiz.insertstudentsgrades(grades);
            if (result==1){
                jm.setCode(1);
            }else {
                jm.setCode(0);
            }
            return jm;
        }catch (Exception ex){
            jm.setCode(0);
            jm.setMsg(ex.getMessage());
            return jm;
        }
    }
    @RequestMapping("/importExcel")
    public void importExcel(@RequestParam("file") MultipartFile file) {
       studentsgradesBiz.gradesImport(file);
    }

//========================================================   教务操作
     @RequestMapping("/plusteacherfindgrade")
    public JsonModel plusteacherfindgrade(String[] studentid,String[] cname,String semester){
        JsonModel jm = new JsonModel();
        try{
             List<allstudentgrades> list = new ArrayList<>();
             for (int j = 0 ;j<studentid.length;j++) {
                 allstudentgrades stu = new allstudentgrades();
                 Integer[] a = new Integer[cname.length];
                 for (int i = 0; i < cname.length; i++) {
                    a[i] =  studentsgradesBiz.plusteacherfindgrade(studentid[j], cname[i]);
                 }
                 stu.setStudentid(studentid[j]);
                 stu.setStudentname(studentBiz.plusstudentname(studentid[j]));
                 stu.setGrade(a);
                 stu.setPoint(studentsgradesBiz.plusteacherfindpoint(studentid[j],semester));
                 list.add(stu);
             }
            jm.setCode(1);
            jm.setData(list);
        }catch (Exception ex){
            jm.setCode(0);
        }
        return jm;
     }
//=========================================学生操作
    @RequestMapping("/studentfindselfgrades")
    public JsonModel studentfindselfgrades(String semester,String param,int pages,HttpSession session){
        JsonModel jm = new JsonModel();
        student stu = (student)session.getAttribute("loginadmin");
        try{
            List<studentsgrades> list = studentsgradesBiz.studentfindselfgrades(stu.getStudentid(),semester,param,pages,session);
            if (list==null || list.isEmpty()==true){
                jm.setCode(0);
                jm.setMsg("查无此消息");
                return jm;
            }else {
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

}
