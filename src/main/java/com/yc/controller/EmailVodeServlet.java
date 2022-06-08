package com.yc.controller;

import com.yc.bean.JsonModel;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Random;

@Controller
public class EmailVodeServlet {
    @RequestMapping("/email")
   public void email(String sendperson, HttpSession session) throws EmailException {
            HtmlEmail email = new HtmlEmail();//创建一个HtmlEmail实例对象
            email.setHostName("smtp.qq.com");
            email.setCharset("utf-8");//设置发送的字符类型
            email.addTo(sendperson);//设置收件人
            email.setFrom("2284752427@qq.com", "小陈教务系统");//发送人的邮箱为自己的，用户名可以随便填
            email.setAuthentication("2284752427@qq.com", "zcckjmhiedbuecic");//设置发送人到的邮箱和用户名和授权码(授权码是自己设置的)
            email.setSubject("验证码");//设置发送主题
            Random r = new Random();
            int x = 10000 + r.nextInt(90000);
            String s = String.valueOf(x);
            session.setAttribute("yxyz",s);
            email.setMsg("【小陈教务系统】验证码"+s+"，此验证码仅用于注册该系统账号，请在20分钟内完成注册。将验证码提供给他人将导致被恶意注册帐号。如果非本人操作，请忽略。");//设置发送内容
            email.send();//进行发送
    }
}
