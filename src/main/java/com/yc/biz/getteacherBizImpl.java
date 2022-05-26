package com.yc.biz;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.yc.bean.cname;
import com.yc.bean.crouse;
import com.yc.bean.curriculum;
import com.yc.bean.teacher;
import com.yc.mapper.CrouseMapper;
import com.yc.mapper.CurriculumMapper;
import com.yc.mapper.TeacherMapper;
import com.yc.mapper.cnameMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class getteacherBizImpl implements getteacherBiz {

    @Autowired
    com.yc.mapper.cnameMapper cnameMapper;
    @Autowired
    CrouseMapper crouseMapper;
    @Autowired
    CurriculumMapper curriculumMapper;
    @Autowired
    TeacherMapper teacherMapper;

    @Override
    public List<cname> findcurriculumbyteacher(String tid, int week, String semester) {
        List<cname> list=cnameMapper.findcoursebyteacher(tid,week,semester);
        return list;
    }

    @Override
    public List<String> findstudyclass(String tid) {
        QueryWrapper<String> qw=new QueryWrapper<>();
        qw.eq("teacherid",tid);
        qw.groupBy("classes");
        List<String> list= crouseMapper.classbyteacher(qw);
        return list;
    }

    @Override
    public List<crouse> findcoursebytid(String tid, String semester) {
        QueryWrapper<crouse> qw=new QueryWrapper<>();
        qw.eq("teacherid",tid).eq("semester",semester);
        return crouseMapper.selectList(qw);
    }

    @Override
    public void addcurriculumbyid(Integer week, Integer weekday, Integer time, String coursid, String semester) {
        curriculum c=new curriculum();
        c.setCoursid(coursid);
        c.setSemester(semester);
        c.setTime(time);
        c.setWeekday(weekday);
        c.setClassno(findcid(coursid));
        c.setWeek(week);
        curriculumMapper.insert(c);
    }

    @Override
    public String findcid(String coursid) {
        QueryWrapper qw=new QueryWrapper();
        qw.eq("courseid",coursid);
        return crouseMapper.findclasses(qw);
    }

    @Override
    public List<teacher> findallteacher() {
        QueryWrapper qw=new QueryWrapper();
        return teacherMapper.selectList(qw);
    }

    @Override
    public void updatecurruclumbyid(String classno,String classno1, Integer week, Integer weekday, Integer time, String coursid,String semester) {
        QueryWrapper<curriculum> qw=new QueryWrapper<>();
        curriculum curriculum=new curriculum();
        curriculum.setCoursid(coursid);
        curriculum.setClassno(classno1);
        qw.eq("classno",classno).eq("week",week).eq("weekday",weekday)
                .eq("time",time).eq("semester",semester);
        curriculumMapper.update(curriculum,qw);
    }
}
