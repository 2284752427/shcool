package com.yc.controller;

import com.yc.bean.JsonModel;
import com.yc.bean.student;
import com.yc.bean.teacher;
import com.yc.biz.AdminBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
//new登录注册
@RestController
@ResponseBody
public class AdminController {
    @Autowired
    AdminBiz adminBiz;
    @RequestMapping("/lgstudent")
    public JsonModel lgstudent(String username, String pwd,String valcode, HttpSession session){
        JsonModel jm = new JsonModel();
        try {
            //2.运算：登录
            //从session中取出原验证
            String validateCode = session.getAttribute("validateCode").toString();
            if (!validateCode.equalsIgnoreCase(valcode)) {
                jm.setCode(0);
                jm.setMsg("验证不正确，请确认后重新输入");
                return jm;
            }
            student student= adminBiz.lgstudent(username, pwd);
            if (student!=null){
                student.setStudentpwd("");
                session.setAttribute("loginadmin",student);
                jm.setCode(1);
            }else{
                jm.setCode(0);
                jm.setMsg("用户名或密码错误！");

            }
           return jm;
        }catch (Exception e){
            e.printStackTrace();
            jm.setCode(0);
            jm.setMsg(e.getMessage());
            return jm;
        }

    }

    @RequestMapping("/lgteacher")
    public JsonModel lgteacher(String username, String pwd,String valcode, HttpSession session){
        JsonModel jm = new JsonModel();
        try {
            //2.运算：登录
            //从session中取出原验证
            String validateCode = session.getAttribute("validateCode").toString();
            if (!validateCode.equalsIgnoreCase(valcode)) {
                jm.setCode(0);
                jm.setMsg("验证不正确，请确认后重新输入");
                return jm;
            }
            teacher teacher= adminBiz.lgteacher(username, pwd);
            if (teacher!=null){
                teacher.setTeacherpwd("");
                session.setAttribute("loginadmin",teacher);
                if(teacher.getType()==1){
                    jm.setCode(2);
                }else{
                    jm.setCode(1);
                }
            }else{
                jm.setCode(0);
                jm.setMsg("用户名或密码错误！");

            }
            return jm;
        }catch (Exception e){
            e.printStackTrace();
            jm.setCode(0);
            jm.setMsg(e.getMessage());
            return jm;
        }

    }

    @RequestMapping("/register")
    public JsonModel register(String username,String pwd,String yxm,HttpSession session){
        JsonModel jm = new JsonModel();
        try{
            teacher teacher = new teacher();
            String yxm1 = session.getAttribute("yxyz").toString();
            if (yxm1.equals(yxm)==false){
                jm.setCode(0);
                jm.setMsg("验证码错误！");
                return jm;
            }
            String shouquanma = session.getAttribute("shouquanma").toString();
            teacher.setTeacherid(username);
            teacher.setTeacherpwd(pwd);
            if (shouquanma.equalsIgnoreCase("CWJNB")) {
               teacher.setType(0);
            }else {
                teacher.setType(1);
            }
           int result =  adminBiz.register(teacher);
           if (result==1){
               jm.setCode(1);
           }else {
               jm.setCode(0);
               jm.setMsg("注册失败");
           }
           return jm;
        }catch (Exception ex){
            jm.setCode(0);
            jm.setMsg("该账户已被注册！");
            return jm;
        }
    }
    @RequestMapping("/loginout")
    public JsonModel logout(HttpSession session){
        JsonModel jm = new JsonModel();
        session.removeAttribute("loginadmin");
        jm.setCode(1);
        return jm;
    }
    @RequestMapping("/goregister")
    public JsonModel goresister(String shouquanma,HttpSession session){
        session.setAttribute("shouquanma",shouquanma);
        JsonModel jm = new JsonModel();
        jm.setCode(1);
        return jm;
    }
}
