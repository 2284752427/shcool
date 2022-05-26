package com.yc.biz;

import com.yc.bean.student;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface StudentBiz {
      public List<student> findstudent(String sjbj,String param, int pages, HttpSession session);
      public List<String> findinsertstudentid(String classes);
//========================================================================
      public List<student> plusfindstudent(String college,String classes,String political,String param,int pages,HttpSession session);
      public List<String> plusfindclasses(String college);
      public void plusupdatastudent(student student);
      public void plusdeltestudent(List<String> list);
      public void plusinsertstudent(student student);
      public List<student> plusteacherfindstudent(String classes,int pages,String param,HttpSession session);
      public String plusstudentname(String studentid);
}
