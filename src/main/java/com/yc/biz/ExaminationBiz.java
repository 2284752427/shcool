package com.yc.biz;

import com.yc.bean.crouse;
import com.yc.bean.examination;
import com.yc.vo.examinationgrades;
import com.yc.vo.studentsgrades;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface ExaminationBiz {
    public List<examinationgrades> plusteacherfindexamination(String college, String semester, String cname, String classes,String param, int pages, HttpSession session);
    public void plusteacherdeleteexaminationgrades(String[] studentid,String[] courseid);
    public void plusteacherupdateexamination(Integer grade,String studentid,String courseid);
    //=========================学生
    public List<examinationgrades> studentfindselfexamination(String studentid, String semester, String param, int pages, HttpSession session);
    public List<crouse> studenttakeexaminationmessages(HttpSession session);
    public void submitexamination(HttpSession session);
    //==============教师
    public List<examinationgrades> findstudentexamination(String teacherid,String semester,String classes,String cname, String param, int pages,HttpSession session);

}
