package com.yc.controller;

import com.yc.bean.*;
import com.yc.biz.RestartnotifyBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class RestartnotifyController {
    @Autowired
    RestartnotifyBiz restartnotifyBiz;
    @RequestMapping("/insertrestartnotify")
    public JsonModel insertrestartnotify(Date startdate,Date enddate,String college){
        JsonModel jm = new JsonModel();
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        String date1 = df.format(startdate);
        String date2 = df.format(enddate);
        restartnotify re = new restartnotify();
        try{
            re.setStartdate(date1);
            re.setEnddate(date2);
            re.setCollege(college);
            restartnotifyBiz.insertrestartnofify(re);
            jm.setCode(1);
            return jm;
        }catch (Exception e){
            jm.setCode(0);
            return jm;
        }
    }
    @RequestMapping("/restartnotifycollege")
    public JsonModel restartnotifycollege(HttpSession session){
        JsonModel jm = new JsonModel();
        teacher th = (teacher)session.getAttribute("loginadmin");
        jm.setCode(1);
        jm.setData(th.getCollege());
        return jm;
    }
    @RequestMapping("/findrestartnotify")
    public JsonModel findrestartnotify(HttpSession session){
        JsonModel jm = new JsonModel();
        student stu = (student)session.getAttribute("loginadmin");
        restartnotify ref = restartnotifyBiz.findrestartnotify(stu.getCollege());
        if (ref!=null) {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            try {
                Date d1 = df.parse(ref.getStartdate());
                Date d2 = df.parse(df.format(date));
                Date d3 = df.parse(ref.getEnddate());
                if ((d2.compareTo(d1) == 0 || d2.compareTo(d1) > 0) && (d2.compareTo(d3) == 0 || d2.compareTo(d3) < 0)) {
                    jm.setCode(1);
                    jm.setData(ref);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }else {
            jm.setCode(0);
        }
        return jm;
    }
}
