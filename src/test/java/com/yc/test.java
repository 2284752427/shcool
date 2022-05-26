package com.yc;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yc.bean.*;
import com.yc.mapper.*;

import com.yc.vo.studentsgrades;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
@MapperScan("com.yc.mapper")
@RunWith(SpringRunner.class)
public class test {
    @Autowired
    TeacherMapper teacherMapper;
    @Autowired
    CrouseMapper crouseMapper;
    @Test
    public void testupdata(){
       QueryWrapper<crouse> wrapper = new QueryWrapper<>();
       wrapper.eq("teacherid","201904141");
       wrapper.eq("classes","软件1902");
       List<crouse> list = crouseMapper.selectList(wrapper);
        System.out.println(list);

    }
//    @Test
//    public static void main(String args[]){
//        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
//        Date date = new Date();
//        System.out.println(date);
//        try {
//            Date d1=df.parse("2022-06-03");
//            Date d2=df.parse(df.format(date));
//
//            if(d1.compareTo(d2)==0){
//                System.out.println("Date1 and Date2 are equal");
//            }
//            else if(d1.compareTo(d2)<0){
//                System.out.println("Date1 is before Date2");
//            }
//            else if(d1.compareTo(d2)>0){
//                System.out.println("Date1 is after Date2");
//            }
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//    }
    @Autowired
    ExaminationMapper examinationMapper;
    @Autowired
    ExaminationGradesMapper examinationGradesMapper;
    @Autowired
    GradesMapper gradesMapper;
    @Autowired
    RestartMapper restartMapper;
    @Test
    public void studenttakeexaminationmessages() {
        List<String> ex = examinationMapper.studentdisexamination("190201");
        int a=ex.size();
        int i=0;
        int b=0;
        while (b<a){
            QueryWrapper<restart> wrapper = new QueryWrapper<>();
            wrapper.eq("courseid",ex.get(i)).eq("studentid","190201");
            QueryWrapper<examination> wrapper1 = new QueryWrapper<>();
            wrapper.eq("courseid", ex.get(i)).eq("studentid", "190201");
            restart rs = restartMapper.selectOne(wrapper);
            examination exn = examinationMapper.selectOne(wrapper1);
            System.out.println(exn);
            if (rs!=null && rs.getNum()==exn.getNum()){
                ex.remove(i);
                i--;
            }
            i++;
            b++;
        }
    }



}
