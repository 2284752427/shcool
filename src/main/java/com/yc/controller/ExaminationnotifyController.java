package com.yc.controller;

import com.yc.bean.JsonModel;
import com.yc.bean.examinationnotify;
import com.yc.bean.student;
import com.yc.bean.teacher;
import com.yc.biz.ExaminationnotifyBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.xml.bind.util.JAXBSource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class ExaminationnotifyController {
    @Autowired
    ExaminationnotifyBiz examinationnotifyBiz;
    @RequestMapping("/insertexaminationnotify")
    public JsonModel insertexaminationnotfy(Date startdate, Date enddate, String college){
        JsonModel jm = new JsonModel();
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        String date1 = df.format(startdate);
        String date2 = df.format(enddate);
        examinationnotify ex = new examinationnotify();
        try{
            ex.setStartdate(date1);
            ex.setEnddate(date2);
            ex.setCollege(college);
            examinationnotifyBiz.insertexaminationnofify(ex);
            jm.setCode(1);
            return jm;
        }catch (Exception e){
            jm.setCode(0);
            return jm;
        }
    }
    @RequestMapping("/examinationnotifycollege")
    public JsonModel examinationnotifycollege(HttpSession session){
        JsonModel jm = new JsonModel();
        teacher th = (teacher)session.getAttribute("loginadmin");
        jm.setCode(1);
        jm.setData(th.getCollege());
        return jm;
    }
    @RequestMapping("/findexaminationnotify")
    public JsonModel findexminationfotify(HttpSession session){
        JsonModel jm = new JsonModel();
        student stu = (student)session.getAttribute("loginadmin");
        examinationnotify en = examinationnotifyBiz.findexaminationnotify(stu.getCollege());
        if (en!=null) {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            try {
                Date d1 = df.parse(en.getStartdate());
                Date d2 = df.parse(df.format(date));
                Date d3 = df.parse(en.getEnddate());
                if ((d2.compareTo(d1) == 0 || d2.compareTo(d1) > 0) && (d2.compareTo(d3) == 0 || d2.compareTo(d3) < 0)) {
                    jm.setCode(1);
                    jm.setData(en);
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
