package com.yc.biz;



import com.yc.bean.cname;
import com.yc.bean.crouse;
import com.yc.bean.teacher;

import java.util.List;

public interface getteacherBiz {
    public List<cname> findcurriculumbyteacher(String tid, int week, String semester);
    public List<String> findstudyclass(String tid);
    public List<crouse> findcoursebytid(String tid, String semester);
    public void addcurriculumbyid(Integer week,Integer weekday,Integer time,String coursid,String semester);
    public String findcid(String coursid);
    public List<teacher> findallteacher();
    public void updatecurruclumbyid(String classno,String classno1,Integer week,Integer weekday,Integer time,String coursid,String semester);
}
