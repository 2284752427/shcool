package com.yc.controller;


import com.yc.bean.*;
import com.yc.biz.getclassBiz;
import com.yc.biz.getteacherBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class CurriculumController {
    @Autowired
    com.yc.biz.getclassBiz getclassBiz;
    @Autowired
    com.yc.biz.getteacherBiz getteacherBiz;
    @RequestMapping("/findaweek")
    public JsonModel findcurri(String cid, String week, String semester, JsonModel jm){
        List<cname> list=getclassBiz.findaweek(cid,week,semester);
        if (list==null){
            jm.setCode(0);
            jm.setMsg("查询失败");
        }else {
            jm.setCode(1);
            jm.setData(list);
        }
        return jm;
    }
    @RequestMapping("/studentfindaweek")
    public JsonModel studentfindcurri( String week, String semester, JsonModel jm,HttpSession session){
        String cid = ((student)session.getAttribute("loginadmin")).getClasses();
        List<cname> list=getclassBiz.findaweek(cid,week,semester);
        if (list==null){
            jm.setCode(0);
            jm.setMsg("查询失败");
        }else {
            jm.setCode(1);
            jm.setData(list);
        }
        return jm;
    }
    @RequestMapping("/findsemester")
    public JsonModel findsemester(JsonModel jm){
        List<String> list=getclassBiz.findsemester();
        if (list==null){
            jm.setCode(0);
            jm.setMsg("查询失败");
        }else {
            jm.setCode(1);
            jm.setData(list);
        }
        return jm;
    }
    @RequestMapping("/findallclass")
    public JsonModel findallclass(JsonModel jm){
        List<student> list=getclassBiz.findallclass();
        if (list==null){
            jm.setCode(0);
            jm.setMsg("查询失败");
        }else {
            jm.setCode(1);
            jm.setData(list);
        }
        return jm;
    }
    @RequestMapping("/findallcourse")
    public JsonModel findallcourse(String classid,String semester,JsonModel jm){
        List<addcurriculum> list=getclassBiz.findallcourse(classid,semester);
        if (list==null){
            jm.setCode(0);
            jm.setMsg("查询失败");
        }else {
            jm.setCode(1);
            jm.setData(list);
        }
        return jm;
    }
    //CollectionUtils.isEmpty(list)
    @RequestMapping("/addcurriculum")
    public boolean addcurriculum(String classno,Integer week,Integer weekday,Integer time,String coursid,String semester){
        String a=coursid;
        String regEx="[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(a);
        List<curriculum> list=getclassBiz.SelectTeacherCurriculum(m.replaceAll("").trim(),week,weekday,time,semester);
        if (list.isEmpty()){
            getclassBiz.addnewcurriculum(classno,week,weekday,time,m.replaceAll("").trim(),semester);
            return true;
        }else {
            return false;
        }
    }
    @RequestMapping("/try")
    public List SelectTeacherCurriculum(Integer week,Integer weekday,Integer time,String coursid,String semester){
        List<curriculum> list=getclassBiz.SelectTeacherCurriculum(coursid,week,weekday,time,semester);
        return list;
    }
    @RequestMapping("/delete")
    public void deleteacourse(String classno, Integer week, Integer weekday, Integer time, String semester){
        getclassBiz.deletecourse(classno,week,weekday,time,semester);
    }
    @RequestMapping("/update")
    public boolean deleteacourse(String coursid,String classno, Integer week, Integer weekday, Integer time, String semester) {
        String a = coursid;
        String regEx = "[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(a);
        List<curriculum> list = getclassBiz.SelectTeacherCurriculum(m.replaceAll("").trim(), week, weekday, time, semester);
        //获取该节课的cid
        List<String> cid = getclassBiz.findcid(classno,week,weekday,time,semester);
        List<crouse> list2=null;
        if (cid!=null){
            list2 = teachercourse(m.replaceAll("").trim());
        }
        boolean f = false;
        for (crouse crouse : list2) {
            if (crouse.getCourseid().equals(cid.get(0))) {
                f = true;//同一个老师的课
            }
        }
        if (f && list.size() == 1) {
            getclassBiz.updatecourse(m.replaceAll("").trim(), classno, week, weekday, time, semester);
            return true;
        } else {
            if (list.isEmpty()){
                getclassBiz.updatecourse(m.replaceAll("").trim(), classno, week, weekday, time, semester);
                return true;
            }else {
                return false;
            }
        }

    }

    public List<crouse> teachercourse(String coursid){
        return getclassBiz.findteacher(coursid);
    }


    //===========================================================

    @RequestMapping("/coursebyteacher")
    public JsonModel courseteacher(String tid,int week,String semester,JsonModel jm){
        String a=tid.split(",")[1];
       List<cname> list = getteacherBiz.findcurriculumbyteacher(a,week,semester);
        if (list==null){
            jm.setCode(0);
            jm.setMsg("查询失败");
        }else {
            jm.setCode(1);
            jm.setData(list);
        }
        return jm;
    }
    @RequestMapping("/teachercoursebyteacher")
    public JsonModel teachercourseteacher(int week, String semester, JsonModel jm, HttpSession session){
        String a = ((teacher)session.getAttribute("loginadmin")).getTeacherid();
        List<cname> list = getteacherBiz.findcurriculumbyteacher(a,week,semester);
        if (list==null){
            jm.setCode(0);
            jm.setMsg("查询失败");
        }else {
            jm.setCode(1);
            jm.setData(list);
        }
        return jm;
    }
    @RequestMapping("/findstudyclass")
    public JsonModel findstudyclass(String tid,JsonModel jm){
        List<String> list=getteacherBiz.findstudyclass(tid);
        if (list==null){
            jm.setCode(0);
            jm.setMsg("查询失败");
        }else {
            jm.setCode(1);
            jm.setData(list);
        }
        return jm;
    }
    @RequestMapping("/findcoursebytid")
    public JsonModel findcousebytid(String tid,String semester,JsonModel jm){
        String b=tid.split(",")[1];
        List<crouse> list=getteacherBiz.findcoursebytid(b,semester);
        if (list==null){
            jm.setCode(0);
            jm.setMsg("查询失败");
        }else {
            jm.setCode(1);
            jm.setData(list);
        }
        return jm;
    }
    @RequestMapping("/addcubyid")
    public boolean addcurriculumbyid(Integer week,Integer weekday,Integer time,String coursid,String semester){
        String b=coursid.split(",")[0];
        try {
            getteacherBiz.addcurriculumbyid(week,weekday,time,b,semester);
            return true;
        }catch(Exception e) {
            return false;
        }
    }
    @RequestMapping("/allteacher")
    public JsonModel allteacher(JsonModel jm){
        List<teacher> list=getteacherBiz.findallteacher();
        if (list==null){
            jm.setCode(0);
            jm.setMsg("查询失败");
        }else {
            jm.setCode(1);
            jm.setData(list);
        }
        return jm;
    }
    @RequestMapping("/deletebyid")
    public void deleteacoursebytid(String classno, Integer week, Integer weekday, Integer time, String semester){
        String a=classno.split("\n")[1];
        getclassBiz.deletecourse(a,week,weekday,time,semester);
    }
    @RequestMapping("/updatebytid")
    public boolean updatecoursebyid(String cname,String coursid, Integer week, Integer weekday, Integer time, String semester) {
        String b=coursid.split(",")[0];
        try {
            getteacherBiz.updatecurruclumbyid(cname.split("\n")[1],coursid.split(",")[2],week,weekday,time,b,semester);
            return true;
        }catch(Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
