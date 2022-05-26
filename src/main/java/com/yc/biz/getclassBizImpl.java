package com.yc.biz;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yc.bean.*;
import com.yc.mapper.AddCuriiclumMapper;
import com.yc.mapper.CrouseMapper;
import com.yc.mapper.CurriculumMapper;
import com.yc.mapper.StudentMapper;
import com.yc.mapper.cnameMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class getclassBizImpl implements getclassBiz{

    @Autowired
    cnameMapper cnameMapper;
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    AddCuriiclumMapper addCuriiclumMapper;
    @Autowired
    CurriculumMapper curriculumMapper;
    @Autowired
    CrouseMapper crouseMapper;

    @Override
    public List<cname> findaweek(String cid, String week, String semester) {
        QueryWrapper<cname> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("classno",cid).eq("week",week).eq("b.semester",semester);
        return cnameMapper.findallcourse(queryWrapper);
    }

    @Override
    public List<String> findsemester() {
        QueryWrapper<cname> queryWrapper=new QueryWrapper<>();
        return cnameMapper.findsemester(queryWrapper);
    }

    @Override
    public List<student> findallclass() {
        QueryWrapper<student> queryWrapper=new QueryWrapper<>();
        queryWrapper.groupBy("classes");
        return studentMapper.selectList(queryWrapper);
    }

    @Override
    public List<addcurriculum> findallcourse(String classid, String semester) {
        QueryWrapper<addcurriculum> qw=new QueryWrapper<>();
        qw.eq("classes",classid).eq("semester",semester);
        qw.orderByAsc("courseid");
        return addCuriiclumMapper.findallcourse(qw);
    }

    @Override
    public void addnewcurriculum(String classno, Integer week, Integer weekday, Integer time, String coursid,String semester) {
        curriculum c=new curriculum();
        c.setClassno(classno);
        c.setCoursid(coursid);
        c.setWeek(week);
        c.setWeekday(weekday);
        c.setTime(time);
        c.setSemester(semester);
        curriculumMapper.insert(c);
    }

    @Override
    public List<curriculum> SelectTeacherCurriculum(String coursid, Integer week, Integer weekday, Integer time, String semester) {
        return curriculumMapper.SelectTeacherCurriculum(coursid,week,weekday,time,semester);
    }

    @Override
    public void deletecourse(String classno, Integer week, Integer weekday, Integer time, String semester) {
        QueryWrapper<curriculum> qw=new QueryWrapper<>();
        qw.eq("classno",classno).eq("week",week).eq("weekday",weekday)
                .eq("time",time).eq("semester",semester);
        curriculumMapper.delete(qw);
    }

    @Override
    public void updatecourse(String coursid,String classno, Integer week, Integer weekday, Integer time, String semester) {
        QueryWrapper<curriculum> qw=new QueryWrapper<>();
        curriculum curriculum=new curriculum();
        curriculum.setCoursid(coursid);
        qw.eq("classno",classno).eq("week",week).eq("weekday",weekday)
                .eq("time",time).eq("semester",semester);
        curriculumMapper.update(curriculum,qw);
    }

    @Override
    public List<crouse> findteacher(String coursid) {
        return crouseMapper.Selectteacher(coursid);
    }

    @Override
    public List<String> findcid(String classno,Integer week, Integer weekday, Integer time, String semester) {
        QueryWrapper<curriculum> qw=new QueryWrapper();
        qw.eq("week",week).eq("weekday",weekday)
                .eq("time",time).eq("semester",semester);
        return curriculumMapper.findcid(classno,week,weekday,time,semester);
    }


}
