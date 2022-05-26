package com.yc.controller;

import com.yc.bean.JsonModel;
import com.yc.bean.student;
import com.yc.bean.teacher;
import com.yc.biz.StudentBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentsController {
    @Autowired
    StudentBiz studentBiz;
    @RequestMapping("/findstudent")
    public JsonModel findstudent(String sjbj,String param, int pages, HttpSession session){
      JsonModel jm = new JsonModel();
       try{
           List<student> stu = studentBiz.findstudent(sjbj,param,pages,session);
           if (stu==null || stu.isEmpty()==true){
               jm.setCode(0);
               jm.setMsg("查无此消息！");
               return jm;
           }else{
               jm.setCode((Integer) session.getAttribute("pagesnum"));
               jm.setData(stu);
               return jm;
           }
       }catch (Exception ex){
           jm.setCode(0);
           jm.setMsg(ex.getMessage());
           return jm;
       }
    }


    //=====================================================教务操作
    @RequestMapping("/plusfindstudent")
    public JsonModel plusfindstudent(String classes,String political,String param,int pages,HttpSession session){
        JsonModel jm = new JsonModel();
        teacher th = (teacher)session.getAttribute("loginadmin");
        try{
            List<student> list = studentBiz.plusfindstudent(th.getCollege(),classes,political,param,pages,session);
            if (list.isEmpty()==false && list!=null){
                jm.setCode(1);
                jm.setData(list);
            }else {
                jm.setCode(0);
                jm.setMsg("查无此消息");
            }
            return jm;
        }catch (Exception ex){
            jm.setCode(0);
            jm.setMsg(ex.getMessage());
            return jm;
        }
    }
    @RequestMapping("/plusfindclasses")
    public  JsonModel plusfindclasses(HttpSession session){
        JsonModel jm = new JsonModel();
        teacher th = (teacher) session.getAttribute("loginadmin");
        try {
            List<String> list = studentBiz.plusfindclasses(th.getCollege());
            jm.setCode(1);
            jm.setData(list);
            return jm;
        }catch (Exception ex){
            jm.setCode(0);
            jm.setMsg(ex.getMessage());
            return  jm;
        }
    }


    @RequestMapping("/plusupdatastudent")
    public JsonModel plusupdatestudent(String studentid,String classes,String studentpwd,String studentname,String personid,String college,String birthday,String sex,String natives,String political,String intime,String address,String zip,String phone,String parentphone){
        JsonModel jm = new JsonModel();
        student stu = new student();
        stu.setStudentid(studentid);
        stu.setClasses(classes);
        stu.setStudentpwd(studentpwd);
        stu.setStudentname(studentname);
        stu.setPersonid(personid);
        stu.setSex(sex);
        stu.setCollege(college);
        stu.setAddress(address);
        stu.setBirthday(birthday);
        stu.setNatives(natives);
        stu.setPolitical(political);
        stu.setZip(zip);
        stu.setPhone(phone);
        stu.setParentphone(parentphone);
        stu.setIntime(intime);
        try{
             studentBiz.plusupdatastudent(stu);
             jm.setCode(1);
             return jm;
        }catch (Exception ex){
            jm.setCode(0);
            jm.setMsg(ex.getMessage());
            return jm;
        }

    }

    @RequestMapping("/plusdeletestudent")
    public JsonModel plusdeletestudent(String[] studentid){
         List<String> list = new ArrayList<>();
         for (int i=0;i<studentid.length;i++){
             list.add(studentid[i]);
         }
         JsonModel jm = new JsonModel();
         try{
             studentBiz.plusdeltestudent(list);
             jm.setCode(1);
             return jm;
         }catch (Exception ex){
             jm.setCode(0);
             jm.setMsg(ex.getMessage());
             return  jm;
         }

    }
    @RequestMapping("/plusinsertstudent")
    public JsonModel plusinsertstudent(String studentid,String classes,String studentpwd,String studentname,String personid,String birthday,String sex,String natives,String political,String intime,String address,String zip,String phone,String parentphone,HttpSession session){
        JsonModel jm = new JsonModel();
        teacher th = (teacher) session.getAttribute("loginadmin");
        student stu= new student();
        stu.setStudentid(studentid);
        stu.setClasses(classes);
        stu.setStudentpwd(studentpwd);
        stu.setStudentname(studentname);
        stu.setPersonid(personid);
        stu.setCollege(th.getCollege());
        stu.setSex(sex);
        stu.setAddress(address);
        stu.setBirthday(birthday);
        stu.setNatives(natives);
        stu.setPolitical(political);
        stu.setZip(zip);
        stu.setPhone(phone);
        stu.setParentphone(parentphone);
        stu.setIntime(intime);
        try{
            studentBiz.plusinsertstudent(stu);
            jm.setCode(1);
            return jm;
        }catch (Exception ex){
            jm.setCode(0);
            jm.setMsg("学号不能重复");
            return jm;
        }

    }
    @RequestMapping("/plusteacherfindstudent")
    public JsonModel plusteacherfindstudent(String classes,int pages,String param,HttpSession session){
        JsonModel jm = new JsonModel();
        try{
            List<student> list = studentBiz.plusteacherfindstudent(classes,pages,param,session);
            jm.setCode((Integer) session.getAttribute("pagesnum"));
            jm.setData(list);
            return jm;
        }catch (Exception ex){
            jm.setCode(0);
            jm.setMsg(ex.getMessage());
            return jm;
        }
    }
}
