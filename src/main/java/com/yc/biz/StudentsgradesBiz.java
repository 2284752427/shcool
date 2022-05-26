package com.yc.biz;

import com.yc.bean.grades;
import com.yc.vo.studentsgrades;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public interface StudentsgradesBiz {
    public List<studentsgrades> findstudentgrades(String teacherid,String xq,String bj,String kc,String param, int pages, HttpSession session);
    public void deletestudentsgrades(String[] studentid,String[] courseid);
    public int insertstudentsgrades(grades grades);
    public List<String> findinsertxh(String courseid);
    public void gradesImport(MultipartFile file);
//=================================教务操作
    public Integer plusteacherfindgrade(String studentid,String cname);
    public Float plusteacherfindpoint(String studentid,String semester);
//====================================学生操作
    public List<studentsgrades> studentfindselfgrades(String studentid,String semester,String param,int pages,HttpSession session);
}
