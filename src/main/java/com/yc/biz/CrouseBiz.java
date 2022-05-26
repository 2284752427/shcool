package com.yc.biz;

import com.yc.bean.crouse;
import com.yc.vo.pluscrouse;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface CrouseBiz {

    public List<String> findteachersjbj(String teacherid);

    public List<String> findteacherbj(String teacherid, String semester, String cname);

    public List<String> findteacherxq(String teacherid,  String cname, String classes);

    public List<String> findteacherck(String teacherid, String semester, String classes);

    public List<String> findinsertkch(String teacherid);
    public String findinsertkcm(String courseid);
    public String findinsertbj(String courseid);
    public List<String> findexaminationsemester(String teacherid);
    public List<String> findexaminationclasses(String teacherid,String semester);
    public List<String> findexaminationcname(String teacherid,String semester,String classes);


    public List<String> findrestartsemester(String teacherid);
    public List<String> findrestartclasses(String teacherid,String semester);
    public List<String> findrestartcname(String teacherid,String semester,String classes);

    public List<crouse> teachercrouse(String semester,String classes,String param,int pages,HttpSession session);
    public List<String> teachercrousesemester(String teacherid);
    public List<String> teachercrouseclasses(String teacherid,String semester);
    //=======================================教务操作
    public List<String> plusteacherfindclasses(String semester,HttpSession session);
    public List<String> plusteacherfindsemester(HttpSession session);
    public List<String> plusteacherfindcname(String semester,String classes);
    public List<String> plusteacherfindexaminationsemester(String college);
    public List<String> plusteacherfindexaminationclasses(String college,String semester);
    public List<String> plusteacherfindexaminationcname(String college,String semester,String classes);
    public List<String> plusteacherfindrestartsemester(String college);
    public List<String> plusteacherfindrestartclasses(String college,String semester);
    public List<String> plusteacherfindrestartcname(String college,String semester,String classes);
    public List<pluscrouse> plusfindcrouse(String college, String semester, String classes, String param, int pages, HttpSession session);
    public void plusupdatacrouse(crouse crouse);
    public void plusdeleteAllcrouse(List<String> list);
    public void plusinsertcrouse(crouse crouse);
    //===============================================学生操作
    public List<String> studentfindgradessemester(String studentid);
    public List<String> studentfindexaminationsemester(String studentid);
    public List<String> studentfindrestartsemester(String studentid);


}
