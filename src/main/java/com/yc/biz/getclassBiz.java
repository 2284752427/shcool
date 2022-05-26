package com.yc.biz;


import com.yc.bean.*;

import java.util.List;

public interface getclassBiz {
    public List<cname> findaweek(String cid, String week, String semester);
    public List<String> findsemester();
    public List<student> findallclass();
    public List<addcurriculum> findallcourse(String classid, String semester);
    public void addnewcurriculum(String classno,Integer week,Integer weekday,Integer time,String coursid,String semester);
    public List<curriculum> SelectTeacherCurriculum(String coursid, Integer week, Integer weekday, Integer time, String semester);
    public void deletecourse(String classno,Integer week,Integer weekday,Integer time,String semester);
    public void updatecourse(String coursid,String classno,Integer week,Integer weekday,Integer time,String semester);
    public List<crouse> findteacher(String coursid);
    public List<String> findcid(String classno,Integer week,Integer weekday,Integer time,String semester);
}
