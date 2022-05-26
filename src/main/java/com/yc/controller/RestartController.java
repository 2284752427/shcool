package com.yc.controller;

import com.yc.bean.JsonModel;
import com.yc.bean.crouse;
import com.yc.bean.student;
import com.yc.bean.teacher;
import com.yc.biz.ExaminationBiz;
import com.yc.biz.RestartBiz;
import com.yc.vo.examinationgrades;
import com.yc.vo.restartgrades;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class RestartController {
    @Autowired
    RestartBiz restartBiz;

    @RequestMapping("/plusteacherfindrestart")
    public JsonModel plusteacherfindrestart(String semester, String cname, String classes, String param, int pages, HttpSession session) {
        JsonModel jm = new JsonModel();
        teacher th = (teacher) session.getAttribute("loginadmin");
        try {
            List<restartgrades> list = restartBiz.plusteacherfindrestart(th.getCollege(), semester, cname, classes, param, pages, session);
            if (list == null || list.isEmpty() == true) {
                jm.setCode(0);
                jm.setMsg("查无此消息");
                return jm;
            } else {
                jm.setCode((Integer) session.getAttribute("pagesnum"));
                jm.setData(list);
                return jm;
            }

        } catch (Exception ex) {
            jm.setCode(0);
            jm.setMsg(ex.getMessage());
            return jm;
        }
    }

    @RequestMapping("/plusteacherdeleterestart")
    public JsonModel plusteacherdeleterestart(String[] studentid, String[] courseid) {
        JsonModel jm = new JsonModel();
        try {
            restartBiz.plusteacherdeleterestart(studentid, courseid);
            jm.setCode(1);
        } catch (Exception ex) {
            jm.setCode(0);
        }
        return jm;
    }

    @RequestMapping("/plusteacherupdaterestart")
    public JsonModel plusteacherupdaterestart(Integer grade, String studentid, String courseid) {
        JsonModel jm = new JsonModel();
        try {
            restartBiz.plusteacherupdaterestart(grade, studentid, courseid);
            jm.setCode(1);
        } catch (Exception ex) {
            jm.setCode(0);
            jm.setMsg(ex.getMessage());
        }
        return jm;
    }

    //=======================学生操作
    @RequestMapping("/studentfindselfrestart")
    public JsonModel studentfindselfrestart(String semester, String param, int pages, HttpSession session) {
        JsonModel jm = new JsonModel();
        student stu = (student) session.getAttribute("loginadmin");
        try {
            List<restartgrades> list = restartBiz.studentfindselfrestart(stu.getStudentid(), semester, param, pages, session);
            if (list == null || list.isEmpty() == true) {
                jm.setCode(0);
                jm.setMsg("查无此消息");
                return jm;
            } else {
                jm.setCode((Integer) session.getAttribute("pagesnum"));
                jm.setData(list);
                return jm;
            }
        } catch (Exception ex) {
            jm.setCode(0);
            jm.setMsg(ex.getMessage());
            return jm;
        }
    }

    @RequestMapping("/studenttakerestart")
    public JsonModel studenttakerestart(HttpSession session) {
        JsonModel jm = new JsonModel();
        student stu = (student) session.getAttribute("loginadmin");
        try {
            List<crouse> list = restartBiz.studenttakerestart(stu.getStudentid());
            if (list.isEmpty() == false && list != null) {
                jm.setCode(1);
                jm.setData(list);
                return jm;
            } else {
                jm.setCode(0);
                return jm;
            }
        } catch (Exception ex) {
            jm.setCode(0);
            jm.setMsg("无重修科目");
            return jm;
        }
    }
    @RequestMapping("/submitresstart")
    public JsonModel submitresstart(HttpSession session){
        JsonModel jm = new JsonModel();
        student stu = (student) session.getAttribute("loginadmin");
        String result = (String) session.getAttribute("result");
        try{
            if (result!=null){
            restartBiz.submitresstart((String[]) session.getAttribute("sendcourseid"),stu.getStudentid());
            jm.setCode(1);
            session.removeAttribute("result");
            }else {
                jm.setCode(0);
            }
        }catch (Exception ex){
            jm.setCode(0);
        }
        return jm;
    }
    @RequestMapping("/sendcourseid")
    public void sendcourseid(String[] courseid,HttpSession session){
        session.setAttribute("sendcourseid",courseid);
    }

    ///==================教师操作
    @RequestMapping("/findstudentrestart")
    public JsonModel findstudentrestart(String semester,String classes,String cname,String param,int pages,HttpSession session){
        JsonModel jm = new JsonModel();
        teacher th = (teacher)session.getAttribute("loginadmin");
        try{
            List<restartgrades> list = restartBiz.findstudentrestart(th.getTeacherid(),semester,classes,cname,param,pages,session);
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
