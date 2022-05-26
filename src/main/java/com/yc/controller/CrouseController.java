package com.yc.controller;

import com.yc.bean.JsonModel;
import com.yc.bean.crouse;
import com.yc.bean.student;
import com.yc.bean.teacher;
import com.yc.biz.CrouseBiz;
import com.yc.biz.StudentBiz;
import com.yc.biz.StudentsgradesBiz;
import com.yc.vo.pluscrouse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CrouseController {
    @Autowired
    CrouseBiz crouseBiz;
    @Autowired
    StudentBiz studentBiz;
    @Autowired
    StudentsgradesBiz studentsgradesBiz;
    @RequestMapping("/findteachersjbj")
    public JsonModel findteachersjbj(HttpSession session){
        JsonModel jm = new JsonModel();
        teacher th = (teacher) session.getAttribute("loginadmin");
        try{
           List<String> list =  crouseBiz.findteachersjbj(th.getTeacherid());
           if (list!=null && list.isEmpty()==false){
               jm.setCode(1);
               jm.setData(list);
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
    @RequestMapping("/findteacherbj")
    public JsonModel findteacherbj( String semester, String cname,  HttpSession session){
        JsonModel jm = new JsonModel();
        teacher th = (teacher) session.getAttribute("loginadmin");
        try{
            List<String> list = crouseBiz.findteacherbj(th.getTeacherid(),semester,cname);
            if (list!=null && list.isEmpty()==false){
                jm.setCode(1);
                jm.setData(list);
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
    @RequestMapping("/findteacherxq")
    public JsonModel findteacherxq( String cname, String classes, HttpSession session){
        JsonModel jm = new JsonModel();
        teacher th = (teacher) session.getAttribute("loginadmin");
        try{
            List<String> list = crouseBiz.findteacherxq(th.getTeacherid(),cname,classes);
            if (list!=null && list.isEmpty()==false){
                jm.setCode(1);
                jm.setData(list);
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
    @RequestMapping("/findteacherck")
    public JsonModel findteacherck( String semester, String classes, HttpSession session){
        JsonModel jm = new JsonModel();
        teacher th = (teacher) session.getAttribute("loginadmin");
        try{
            List<String> list = crouseBiz.findteacherck(th.getTeacherid(),semester,classes);
            if (list!=null && list.isEmpty()==false){
                jm.setCode(1);
                jm.setData(list);
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
    @RequestMapping("/findinsertkch")
    public JsonModel findinsertkch(HttpSession session){
        JsonModel jm = new JsonModel();
        teacher th = (teacher) session.getAttribute("loginadmin");
        try{
            List<String> list = crouseBiz.findinsertkch(th.getTeacherid());
            jm.setCode(1);
            jm.setData(list);
            return jm;
        }catch (Exception ex){
            jm.setCode(0);
            jm.setMsg(ex.getMessage());
            return jm;
        }
    }
    @RequestMapping("/findinsertkcm")
    public JsonModel findinsertkcm(String courseid){
        JsonModel jm = new JsonModel();
        try{
            String list = crouseBiz.findinsertkcm(courseid);
            jm.setCode(1);
            jm.setData(list);
            return jm;
        }catch (Exception ex){
            jm.setCode(0);
            jm.setMsg(ex.getMessage());
            return jm;
        }
    }
    @RequestMapping("/findinsertxh")
    public JsonModel findinsertxh(String courseid){
        JsonModel jm = new JsonModel();
        try{
            String classes = crouseBiz.findinsertbj(courseid);
            List<String> listx = studentsgradesBiz.findinsertxh(courseid);
            List<String> list = studentBiz.findinsertstudentid(classes);
            for (int i=0;i<list.size();i++){
                for (int j=0;j<listx.size();j++){
                    if (list.get(i).equals(listx.get(j))){
                        list.remove(i);
                    }
                }
            }
            jm.setCode(1);
            jm.setData(list);
            return jm;
        }catch (Exception ex){
            jm.setCode(0);
            return jm;
        }
    }
    @RequestMapping("/findexaminationsemester")
    public JsonModel findexaminationsemester(HttpSession session){
        JsonModel jm = new JsonModel();
        teacher th = (teacher)session.getAttribute("loginadmin");
        List<String> list = crouseBiz.findexaminationsemester(th.getTeacherid());
        jm.setCode(1);
        jm.setData(list);
        return jm;
    }
    @RequestMapping("/findexaminationclasses")
    public JsonModel findexaminationclasses(String semester,HttpSession session){
        JsonModel jm = new JsonModel();
        teacher th = (teacher)session.getAttribute("loginadmin");
        List<String> list = crouseBiz.findexaminationclasses(th.getTeacherid(),semester);
        jm.setCode(1);
        jm.setData(list);
        return jm;
    }
    @RequestMapping("/findexaminationcname")
    public JsonModel findexaminationcname(String semester,String classes,HttpSession session){
        JsonModel jm = new JsonModel();
        teacher th = (teacher)session.getAttribute("loginadmin");
        List<String> list = crouseBiz.findexaminationcname(th.getTeacherid(),semester,classes);
        jm.setCode(1);
        jm.setData(list);
        return jm;
    }

    @RequestMapping("/findrestartsemester")
    public JsonModel findrestartsemester(HttpSession session){
        JsonModel jm = new JsonModel();
        teacher th = (teacher)session.getAttribute("loginadmin");
        List<String> list = crouseBiz.findrestartsemester(th.getTeacherid());
        jm.setCode(1);
        jm.setData(list);
        return jm;
    }
    @RequestMapping("/findrestartclasses")
    public JsonModel findrestartclasses(String semester,HttpSession session){
        JsonModel jm = new JsonModel();
        teacher th = (teacher)session.getAttribute("loginadmin");
        List<String> list = crouseBiz.findrestartclasses(th.getTeacherid(),semester);
        jm.setCode(1);
        jm.setData(list);
        return jm;
    }
    @RequestMapping("/findrestartcname")
    public JsonModel findrestartcname(String semester,String classes,HttpSession session){
        JsonModel jm = new JsonModel();
        teacher th = (teacher)session.getAttribute("loginadmin");
        List<String> list = crouseBiz.findrestartcname(th.getTeacherid(),semester,classes);
        jm.setCode(1);
        jm.setData(list);
        return jm;
    }

    @RequestMapping("/teacherfindcrouse")
    public JsonModel teacherfindcrouse(String semester,String classes,String param,int pages,HttpSession session){
        JsonModel jm = new JsonModel();
        try{
            List<crouse> list = crouseBiz.teachercrouse(semester,classes,param,pages,session);
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
    @RequestMapping("/teachercrousesemester")
    public JsonModel teachercrousesemester(HttpSession session){
        JsonModel jm = new JsonModel();
        teacher th = (teacher)session.getAttribute("loginadmin");
        List<String> list = crouseBiz.teachercrousesemester(th.getTeacherid());
        jm.setCode(1);
        jm.setData(list);
        return jm;
    }
    @RequestMapping("/teachercrouseclasses")
    public JsonModel teachercrouseclasses(String semester,HttpSession session){
        JsonModel jm = new JsonModel();
        teacher th  = (teacher)session.getAttribute("loginadmin");
        List<String> list = crouseBiz.teachercrouseclasses(th.getTeacherid(),semester);
        jm.setCode(1);
        jm.setData(list);
        return jm;
    }
//=============================================================================教务操作
    @RequestMapping("/plusteacherfindclasses")
    public JsonModel plusteacherfindclasses(String semester,HttpSession session){
        JsonModel jm =new JsonModel();
        try{
            List<String> list = crouseBiz.plusteacherfindclasses(semester,session);
            jm.setCode(1);
            jm.setData(list);
            return jm;
        }catch (Exception ex){
            jm.setCode(0);
            jm.setMsg(ex.getMessage());
            return jm;
        }
    }
    @RequestMapping("/plusteacherfindsemester")
    public JsonModel plusteacherfindsemester(HttpSession session){
        JsonModel jm =new JsonModel();
        try{
            List<String> list = crouseBiz.plusteacherfindsemester(session);
            jm.setCode(1);
            jm.setData(list);
            return jm;
        }catch (Exception ex){
            jm.setCode(0);
            jm.setMsg(ex.getMessage());
            return jm;
        }
    }
    @RequestMapping("/plusteacherfindcname")
    public JsonModel plusteacherfindcname(String semester,String classes){
            JsonModel jm = new JsonModel();
        try{
            List<String> list = crouseBiz.plusteacherfindcname(semester,classes);
            jm.setCode(1);
            jm.setData(list);
            return jm;
        }catch (Exception ex){
            jm.setCode(0);
            jm.setMsg(ex.getMessage());
            return jm;
        }
    }
    @RequestMapping("/plusteacherfindexaminationsemester")
    public JsonModel plusteacherfindexaminationsemester(HttpSession session){
        JsonModel jm = new JsonModel();
        teacher th = (teacher) session.getAttribute("loginadmin");
        try{
            List<String> list = crouseBiz.plusteacherfindexaminationsemester(th.getCollege());
            jm.setCode(1);
            jm.setData(list);
            return jm;
        }catch (Exception ex){
            jm.setCode(0);
            return jm;
        }
    }
    @RequestMapping("/plusteacherfindexaminationclasses")
    public JsonModel plusteacherfindexaminationclasses(String semester,HttpSession session){
        JsonModel jm = new JsonModel();
        teacher th = (teacher) session.getAttribute("loginadmin");
        try{
            List<String> list = crouseBiz.plusteacherfindexaminationclasses(th.getCollege(),semester);
            jm.setCode(1);
            jm.setData(list);
            return jm;
        }catch (Exception ex){
            jm.setCode(0);
            return jm;
        }
    }
    @RequestMapping("/plusteacherfindexaminationcname")
    public JsonModel plusteacherfindexaminationcname(String semester,String classes,HttpSession session){
        JsonModel jm = new JsonModel();
        teacher th = (teacher) session.getAttribute("loginadmin");
        try{
            List<String> list = crouseBiz.plusteacherfindexaminationcname(th.getCollege(),semester,classes);
            jm.setCode(1);
            jm.setData(list);
            return jm;
        }catch (Exception ex){
            jm.setCode(0);
            return jm;
        }
    }

    @RequestMapping("/plusteacherfindrestartsemester")
    public JsonModel plusteacherfindrestartsemester(HttpSession session){
        JsonModel jm = new JsonModel();
        teacher th = (teacher) session.getAttribute("loginadmin");
        try{
            List<String> list = crouseBiz.plusteacherfindrestartsemester(th.getCollege());
            jm.setCode(1);
            jm.setData(list);
            return jm;
        }catch (Exception ex){
            jm.setCode(0);
            return jm;
        }
    }
    @RequestMapping("/plusteacherfindrestartclasses")
    public JsonModel plusteacherfindrestartclasses(String semester,HttpSession session){
        JsonModel jm = new JsonModel();
        teacher th = (teacher) session.getAttribute("loginadmin");
        try{
            List<String> list = crouseBiz.plusteacherfindrestartclasses(th.getCollege(),semester);
            jm.setCode(1);
            jm.setData(list);
            return jm;
        }catch (Exception ex){
            jm.setCode(0);
            return jm;
        }
    }
    @RequestMapping("/plusteacherfindrestartcname")
    public JsonModel plusteacherfindrestartcname(String semester,String classes,HttpSession session){
        JsonModel jm = new JsonModel();
        teacher th = (teacher) session.getAttribute("loginadmin");
        try{
            List<String> list = crouseBiz.plusteacherfindrestartcname(th.getCollege(),semester,classes);
            jm.setCode(1);
            jm.setData(list);
            return jm;
        }catch (Exception ex){
            jm.setCode(0);
            return jm;
        }
    }

    @RequestMapping("/plusfindcrouse")
    public JsonModel plusfindcrouse(String semester,String classes,String param,int pages,HttpSession session){
        JsonModel jm = new JsonModel();
        teacher th = (teacher) session.getAttribute("loginadmin");
        try{
            List<pluscrouse> list = crouseBiz.plusfindcrouse(th.getCollege(),semester,classes,param,pages,session);
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
            jm.setCode(1);
            jm.setMsg(ex.getMessage());
            return jm;
        }

    }
    @RequestMapping("/plusupdatacrouse")
    public JsonModel plusupdatacrouse(String courseid,String cname,String classes,String teacherid,String semester,String ctype,Integer hours,Float credit){
        JsonModel jm = new JsonModel();
        crouse crouse = new crouse();
        try{
            crouse.setCname(cname);
            crouse.setCourseid(courseid);
            crouse.setCredit(credit);
            crouse.setClasses(classes);
            crouse.setTeacherid(teacherid);
            crouse.setSemester(semester);
            crouse.setCtype(ctype);
            crouse.setHours(hours);
           crouseBiz.plusupdatacrouse(crouse);
           jm.setCode(1);
            return jm;
        }catch (Exception ex){
            jm.setCode(0);
            jm.setMsg(ex.getMessage());
            return jm;
        }
    }
    @RequestMapping("/plusdeleteAllcrouse")
    public JsonModel deleteAllcrouse(String[] courseid){
        JsonModel jm = new JsonModel();
        List<String> list = new ArrayList();
        for (int i=0;i<courseid.length;i++){
            list.add(courseid[i]);
        }
        try{
            crouseBiz.plusdeleteAllcrouse(list);
            jm.setCode(1);
            return  jm;
        }catch (Exception ex){
            jm.setCode(0);
            jm.setMsg(ex.getMessage());
            return jm;
        }
    }
    @RequestMapping("/plusuinsertcrouse")
    public JsonModel plusinsertcrouse(String courseid,String cname,String classes,String teacherid,String semester,String ctype,Integer hours,Float credit){
        JsonModel jm = new JsonModel();
        crouse crouse = new crouse();
        try{
            crouse.setCname(cname);
            crouse.setCourseid(courseid);
            crouse.setCredit(credit);
            crouse.setClasses(classes);
            crouse.setTeacherid(teacherid);
            crouse.setSemester(semester);
            crouse.setCtype(ctype);
            crouse.setHours(hours);
            crouseBiz.plusinsertcrouse(crouse);
            jm.setCode(1);
            return jm;
        }catch (Exception ex){
            jm.setCode(0);
            jm.setMsg("课程号已存在或格式不对！");
            return jm;
        }
    }
    //=============================学生操作
    @RequestMapping("/studentfindgradessemester")
    public JsonModel studentfindgradessemester(HttpSession session){
        JsonModel jm = new JsonModel();
        student stu = (student)session.getAttribute("loginadmin");
        try {
            List<String> list = crouseBiz.studentfindgradessemester(stu.getStudentid());
            jm.setCode(1);
            jm.setData(list);
            return jm;
        }catch (Exception ex){
            jm.setCode(0);
            jm.setMsg(ex.getMessage());
            return jm;
        }
    }
    @RequestMapping("/studentfindexaminationsemester")
    public JsonModel studentfindexaminationsemester(HttpSession session){
        JsonModel jm = new JsonModel();
        student stu = (student)session.getAttribute("loginadmin");
        try {
            List<String> list = crouseBiz.studentfindexaminationsemester(stu.getStudentid());
            jm.setCode(1);
            jm.setData(list);
            return jm;
        }catch (Exception ex){
            jm.setCode(0);
            jm.setMsg(ex.getMessage());
            return jm;
        }
    }
    @RequestMapping("/studentfindrestartsemester")
    public JsonModel studentfindrestartnsemester(HttpSession session){
        JsonModel jm = new JsonModel();
        student stu = (student)session.getAttribute("loginadmin");
        try {
            List<String> list = crouseBiz.studentfindrestartsemester(stu.getStudentid());
            jm.setCode(1);
            jm.setData(list);
            return jm;
        }catch (Exception ex){
            jm.setCode(0);
            jm.setMsg(ex.getMessage());
            return jm;
        }
    }
}
