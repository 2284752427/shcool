package com.yc.biz;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yc.bean.crouse;
import com.yc.bean.examination;
import com.yc.bean.restart;
import com.yc.mapper.*;
import com.yc.vo.examinationgrades;
import com.yc.vo.restartgrades;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
@Transactional
public class RestartBizImpl implements RestartBiz{
    @Autowired
    RestartMapper restartMapper;
    @Autowired
    RestartGradesMapper restartGradesMapper;
    @Autowired
    ExaminationMapper examinationMapper;
    @Autowired
    CrouseMapper crouseMapper;
    @Override
    public List<restartgrades> plusteacherfindrestart(String college, String semester, String cname, String classes, String param, int pages, HttpSession session) {
   //     @Select("select a.courseid,c.cname,c.semester,a.studentid,b.studentname,b.classes,a.grade from examination a join student b on a.studentid = b.studentid join crouse c on a.courseid = c.courseid ${ew.customSqlSegment}")
        QueryWrapper<restartgrades> wrapper = new QueryWrapper<>();
        wrapper.eq("b.college",college);
        if (semester.isEmpty()==false){
            wrapper.eq("c.semester",semester);
        }
        if (cname.isEmpty()==false){
            wrapper.eq("c.cname",cname);
        }
        if (classes.isEmpty()==false){
            wrapper.eq("b.classes",classes);
        }
        if (param.isEmpty()==false){
            wrapper.and(q->q.eq("a.studentid",param).or().likeRight("b.studentname",param));
        }
        // 设置分页信息, 查第3页, 每页2条数据
        Page<restartgrades> page = new Page<>(pages, 15);
        // 执行分页查询
        Page<restartgrades> userPage = page.setRecords(this.restartGradesMapper.plusteacherfindrestartgrades(wrapper,page));
        // System.out.println("总页数 = " + userPage.getPages());
        int  pagesnum = (int) userPage.getPages();
        // 获取分页查询结果
        session.removeAttribute("pagesnum");
        session.setAttribute("pagesnum",pagesnum);
        List<restartgrades> records = userPage.getRecords();
        return records;
    }

    @Override
    public void plusteacherdeleterestart(String[] studentid, String[] courseid) {
        for (int i=0;i<studentid.length;i++){
           restartMapper.plusteacherdeleterestart(studentid[i],courseid[i]);
        }
    }

    @Override
    public void plusteacherupdaterestart(Integer grade, String studentid, String courseid) {
        restartMapper.plusteacherupdaterestart(grade,studentid,courseid);
    }
//=============================学生操作
    @Override
    public List<restartgrades> studentfindselfrestart(String studentid, String semester, String param, int pages, HttpSession session) {
        QueryWrapper<restartgrades> wrapper = new QueryWrapper<>();
        wrapper.eq("a.studentid",studentid);
        if (semester.isEmpty()==false){
            wrapper.eq("c.semester",semester);
        }
        if (param.isEmpty()==false){
            wrapper.and(q->q.eq("c.courseid",param).or().likeRight("c.cname",param));
        }
        // 设置分页信息, 查第3页, 每页2条数据
        Page<restartgrades> page = new Page<>(pages, 15);
        // 执行分页查询
        Page<restartgrades> userPage = page.setRecords(this.restartGradesMapper.studentfindselfrestart(wrapper,page));
        // System.out.println("总页数 = " + userPage.getPages());
        int  pagesnum = (int) userPage.getPages();
        // 获取分页查询结果
        session.removeAttribute("pagesnum");
        session.setAttribute("pagesnum",pagesnum);
        List<restartgrades> records = userPage.getRecords();
        return records;
    }

    @Override
    public List<crouse> studenttakerestart(String studentid) {
        List<String> ex = examinationMapper.studentdisexamination(studentid);
        int a=ex.size();
        int i=0;
        int b=0;
        while (b<a){
            QueryWrapper<restart> wrapper = new QueryWrapper<>();
            wrapper.eq("courseid",ex.get(i)).eq("studentid",studentid);
            QueryWrapper<examination> wrapper1 = new QueryWrapper<>();
            wrapper1.eq("courseid", ex.get(i)).eq("studentid", studentid);
            restart rs = restartMapper.selectOne(wrapper);
            examination exn = examinationMapper.selectOne(wrapper1);
            if (rs!=null && rs.getNum()==exn.getNum()){
                ex.remove(i);
                i--;
            }
            i++;
            b++;
        }
        if (ex.size()==0){
            return null;
        }else {
            return crouseMapper.selectBatchIds(ex);
        }
    }

    @Override
    public void submitresstart(String[] courseid, String studentid) {
        for (int i=0;i<courseid.length;i++){
            restartMapper.plusteacherdeleterestart(studentid,courseid[i]);
            QueryWrapper<examination> wrapper = new QueryWrapper<>();
            wrapper.eq("courseid",courseid[i]).eq("studentid",studentid);
            examination exn = examinationMapper.selectOne(wrapper);
            restart restart = new restart();
            restart.setCourseid(courseid[i]);
            restart.setStudentid(studentid);
            restart.setGrade(101);
            restart.setNum(exn.getNum());
           restartMapper.insert(restart);
        }
    }

    @Override
    public List<restartgrades> findstudentrestart(String teacherid, String semester, String classes, String cname, String param, int pages, HttpSession session) {
        QueryWrapper<restartgrades> wrapper = new QueryWrapper<>();
        wrapper.eq("a.teacherid",teacherid);
        if (semester.isEmpty()==false){
            wrapper.eq("a.semester",semester);
        }if (classes.isEmpty()==false){
            wrapper.eq("a.classes",classes);
        }if (cname.isEmpty()==false){
            wrapper.eq("a.cname",cname);
        }if (param.isEmpty()==false){
            wrapper.and(q->q.eq("d.studentid",param).or().eq("d.studentname",param));
        }
        // 设置分页信息, 查第3页, 每页2条数据
        Page<restartgrades> page = new Page<>(pages, 15);
        // 执行分页查询
        Page<restartgrades> userPage = page.setRecords(this.restartGradesMapper.findstudentrestart(wrapper,page));
        // System.out.println("总页数 = " + userPage.getPages());
        int  pagesnum = (int) userPage.getPages();
        // 获取分页查询结果
        session.removeAttribute("pagesnum");
        session.setAttribute("pagesnum",pagesnum);
        List<restartgrades> records = userPage.getRecords();
        return records;
    }

}
