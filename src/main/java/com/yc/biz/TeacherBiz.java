package com.yc.biz;

import com.yc.bean.teacher;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface TeacherBiz {
    public List<teacher> findteacherAll(String college, String job, String param,int pages, HttpSession session);
    public List<String> findjob(String college);
    public void updatateacher(teacher teacher);
    public void deleteteacher(List<String> tid);
    public String pluscrouseteachername(String teacherid);
    public List<String> pluscrouseteacherid();
}
