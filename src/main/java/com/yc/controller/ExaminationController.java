package com.yc.controller;

import com.yc.bean.JsonModel;
import com.yc.bean.crouse;
import com.yc.bean.student;
import com.yc.bean.teacher;
import com.yc.biz.ExaminationBiz;
import com.yc.vo.examinationgrades;
import com.yc.vo.studentsgrades;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class ExaminationController {
    @Autowired
    ExaminationBiz examinationBiz;
    @RequestMapping("/plusteacherfindexaminationgrades")
    public JsonModel plusteacherfindexaminationgrades(String semester, String cname,String classes,String param, int pages, HttpSession session){
        JsonModel jm = new JsonModel();
        teacher th = (teacher) session.getAttribute("loginadmin");
        try {
            List<examinationgrades> list = examinationBiz.plusteacherfindexamination(th.getCollege(),semester,cname,classes,param,pages,session);
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
    @RequestMapping("/plusteacherdeleteexaminationgrades")
    public JsonModel plusteacherdeleteexaminationgrades(String[] studentid,String[] courseid){
        JsonModel jm = new JsonModel();
        try{
            examinationBiz.plusteacherdeleteexaminationgrades(studentid,courseid);
            jm.setCode(1);
        }catch (Exception ex){
            jm.setCode(0);
        }
        return jm;
    }
    @RequestMapping("/plusteacherupdateexamination")
    public JsonModel plusteacherupdateexamiantion(Integer grade,String studentid,String courseid){
        JsonModel jm = new JsonModel();
        try{
            examinationBiz.plusteacherupdateexamination(grade,studentid,courseid);
            jm.setCode(1);
        }catch (Exception ex){
            jm.setCode(0);
            jm.setMsg(ex.getMessage());
        }
        return jm;
    }
    //=======================学生操作
    @RequestMapping("/studentfindselfexamination")
    public JsonModel studentfindselfexamination(String semester,String param,int pages,HttpSession session){
        JsonModel jm = new JsonModel();
        student stu = (student)session.getAttribute("loginadmin");
        try{
            List<examinationgrades> list = examinationBiz.studentfindselfexamination(stu.getStudentid(),semester,param,pages,session);
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
    @RequestMapping("/studenttakeexaminationmessages")
    public JsonModel studenttakeexaminationmessages(HttpSession session){
        JsonModel jm = new JsonModel();
        try{
            List<crouse> list = examinationBiz.studenttakeexaminationmessages(session);
            if (list.isEmpty()==false && list!=null){
                jm.setCode(1);
                jm.setData(list);
                return jm;
            }else {
                jm.setCode(0);
                return jm;
            }
        }catch (Exception ex){
            jm.setCode(0);
            jm.setMsg("无补考科目");
            return jm;
        }
    }
    @RequestMapping("/takeexaminationstudentselfmessages")
    public JsonModel takeexaminationstudentselfmessages(HttpSession session){
        JsonModel jm = new JsonModel();
        student stu = (student)session.getAttribute("loginadmin");
        jm.setCode(1);
        jm.setData(stu);
        return jm;
    }
    @RequestMapping("/submitexamination")
    public JsonModel submitexamination(HttpSession session){
        JsonModel jm = new JsonModel();
        student stu = (student)session.getAttribute("loginadmin");
        try{
            examinationBiz.submitexamination(session);
            jm.setCode(1);
        }catch (Exception ex){
            jm.setCode(0);
            jm.setMsg(ex.getMessage());
            return jm;
        }
        return jm;
    }
    ///==================教师操作
    @RequestMapping("/findstudentexamination")
    public JsonModel findstudentexamination(String semester,String classes,String cname,String param,int pages,HttpSession session){
        JsonModel jm = new JsonModel();
        teacher th = (teacher)session.getAttribute("loginadmin");
        try{
            List<examinationgrades> list = examinationBiz.findstudentexamination(th.getTeacherid(),semester,classes,cname,param,pages,session);
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
