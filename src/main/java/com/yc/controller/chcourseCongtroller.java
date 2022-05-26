package com.yc.controller;

import com.yc.bean.JsonModel;
import com.yc.bean.chcourse;
import com.yc.bean.newchchourse;
import com.yc.bean.student;
import com.yc.biz.getchcourseBiz;
import com.yc.biz.stduentchBiz;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class chcourseCongtroller {
    @Autowired
    com.yc.biz.getchcourseBiz getchcourseBiz;
    @Autowired
    stduentchBiz studentchBiz;

//    @RequestMapping("/action")
//    public void addsession(HttpSession session){
//        student s = new student();
//        s.setStudentid("1");
//        session.setAttribute("loginadmin",s);
//    }

    @RequestMapping("/gettype")
    public JsonModel gettype(JsonModel jm){
        List<String>  list=getchcourseBiz.getalltype();
        if (list==null){
            jm.setCode(0);
            jm.setMsg("查询失败");
        }else {
            jm.setCode(1);
            jm.setData(list);
        }
        return jm;
    }
    @RequestMapping("/deletech")
    public boolean deletech(String chcourseid){
        String Ex="[^0-9]";
        Pattern p=Pattern.compile(Ex);
        Matcher m=p.matcher(chcourseid);
        try {
            getchcourseBiz.deletechcourse(Integer.parseInt(m.replaceAll("").trim()));
            return true;
        }catch (Exception e){
            return false;
        }
    }
    @RequestMapping("/addchcourse")
    public boolean addchcourse(String chtype, String chname, String introduction){
        try {
            getchcourseBiz.addchcourse(chtype,chname,introduction);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    @RequestMapping("/updatechcourse")
    public boolean updatech(String chcourseid,String chtype,String chname,String introduction){
        String Ex="[^0-9]";
        Pattern p=Pattern.compile(Ex);
        Matcher m=p.matcher(chcourseid);
        try {
            getchcourseBiz.updatechcourse(Integer.parseInt(m.replaceAll("").trim()),chtype,chname,introduction);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    @RequestMapping("/selectch")
    public JsonModel selectch(String chname,int pagename,JsonModel jm){
        List<chcourse> page=getchcourseBiz.selectchcourse(chname,pagename);
        List<newchcourse> list2=new ArrayList<>();
        for (chcourse index:page){
            newchcourse n=new newchcourse();
            n.setChcourseid(index.getTypeid()+index.getChcourseid());
            n.setChname(index.getChname());
            n.setChtype(index.getChtype());
            n.setIntroduction(index.getIntroduction());
            list2.add(n);
        }
        if (list2==null){
            jm.setCode(0);
            jm.setMsg("查询失败");
        }else {
            jm.setCode(1);
            jm.setData(list2);
        }
        return jm;
    }
    @RequestMapping("/count")
    public int count(String chname){
        return getchcourseBiz.count(chname);
    }
    //====================================

    @RequestMapping("/studentaddch")
    public JsonModel studentaddch(String chid, HttpSession session, JsonModel jm){
        student s=(student)session.getAttribute("loginadmin");
        String studentid=s.getStudentid();
        if (studentchBiz.howstudentch(studentid)){
            try {
                studentchBiz.studentaddch(studentid,chid);
                jm.setCode(1);
                jm.setMsg("选课成功");
            }catch (Exception e){
                jm.setCode(0);
                jm.setMsg("选课失败,你已选过该课程");
            }
        }else {
            jm.setCode(0);
            jm.setMsg("选课失败,最多可选五门课");
        }
        return jm;
    }
    @RequestMapping("/selectmych")
    public JsonModel selectmych(HttpSession session, JsonModel jm){
        student s=(student)session.getAttribute("loginadmin");
        String studentid=s.getStudentid();
        List<newchchourse> list=studentchBiz.selectmych(studentid);
        if (list != null){
            jm.setCode(1);
            jm.setData(list);
        }else {
            jm.setCode(0);
            jm.setMsg("查询失败");
        }
        return jm;
    }
    @RequestMapping("/deletemych")
    public JsonModel deletemych(String chid, HttpSession session, JsonModel jm){
        student s=(student)session.getAttribute("loginadmin");
        String studentid=s.getStudentid();
        try {
            studentchBiz.deletemych(chid,studentid);
            jm.setCode(1);
            jm.setMsg("退课成功");
        }catch (Exception e){
            jm.setCode(0);
            jm.setMsg("退课失败");
        }
        return jm;
    }
}

@Data
class newchcourse{
    private String chcourseid;
    private String chtype;
    private String chname;
    private String introduction;
}

