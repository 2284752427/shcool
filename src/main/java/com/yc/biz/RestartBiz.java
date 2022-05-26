package com.yc.biz;

import com.yc.bean.crouse;
import com.yc.bean.restart;
import com.yc.vo.examinationgrades;
import com.yc.vo.restartgrades;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface RestartBiz {
    public List<restartgrades> plusteacherfindrestart(String college, String semester, String cname, String classes, String param, int pages, HttpSession session);
    public void plusteacherdeleterestart(String[] studentid,String[] courseid);
    public void plusteacherupdaterestart(Integer grade,String studentid,String courseid);
    //=========================学生
    public List<restartgrades> studentfindselfrestart(String studentid, String semester, String param, int pages, HttpSession session);
    public List<crouse> studenttakerestart(String studentid);
    public void submitresstart(String[] courseid,String studentid);

    //==============教师
    public List<restartgrades> findstudentrestart(String teacherid,String semester,String classes,String cname, String param, int pages,HttpSession session);
}
