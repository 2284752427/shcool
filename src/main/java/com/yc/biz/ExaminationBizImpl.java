package com.yc.biz;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yc.bean.crouse;
import com.yc.bean.examination;
import com.yc.bean.restart;
import com.yc.bean.student;
import com.yc.mapper.*;
import com.yc.vo.examinationgrades;
import com.yc.vo.studentsgrades;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.sql.Wrapper;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ExaminationBizImpl implements ExaminationBiz {
    @Autowired
    ExaminationMapper examinationMapper;
    @Autowired
    ExaminationGradesMapper examinationGradesMapper;
    @Autowired
    GradesMapper gradesMapper;
    @Autowired
    RestartMapper restartMapper;
    @Autowired
    CrouseMapper crouseMapper;

    @Override
    public List<examinationgrades> plusteacherfindexamination(String college, String semester, String cname, String classes, String param, int pages, HttpSession session) {
        //     @Select("select a.courseid,c.cname,c.semester,a.studentid,b.studentname,b.classes,a.grade from examination a join student b on a.studentid = b.studentid join crouse c on a.courseid = c.courseid ${ew.customSqlSegment}")
        QueryWrapper<examinationgrades> wrapper = new QueryWrapper<>();
        wrapper.eq("b.college", college);
        if (semester.isEmpty() == false) {
            wrapper.eq("c.semester", semester);
        }
        if (cname.isEmpty() == false) {
            wrapper.eq("c.cname", cname);
        }
        if (classes.isEmpty() == false) {
            wrapper.eq("b.classes", classes);
        }
        if (param.isEmpty() == false) {
            wrapper.and(q -> q.eq("a.studentid", param).or().likeRight("b.studentname", param));
        }
        // 设置分页信息, 查第3页, 每页2条数据
        Page<examinationgrades> page = new Page<>(pages, 15);
        // 执行分页查询
        Page<examinationgrades> userPage = page.setRecords(this.examinationGradesMapper.plusteacherfindeaminationgrades(wrapper, page));
        // System.out.println("总页数 = " + userPage.getPages());
        int pagesnum = (int) userPage.getPages();
        // 获取分页查询结果
        session.removeAttribute("pagesnum");
        session.setAttribute("pagesnum", pagesnum);
        List<examinationgrades> records = userPage.getRecords();
        return records;
    }

    @Override
    public void plusteacherdeleteexaminationgrades(String[] studentid, String[] courseid) {
        for (int i = 0; i < studentid.length; i++) {
            examinationMapper.plusteacherdeleteexamination(studentid[i], courseid[i]);
        }
    }

    @Override
    public void plusteacherupdateexamination(Integer grade, String studentid, String courseid) {
        examinationMapper.plusteacherupdateexamination(grade, studentid, courseid);
    }

    //==========================学生
    @Override
    public List<examinationgrades> studentfindselfexamination(String studentid, String semester, String param, int pages, HttpSession session) {
        QueryWrapper<examinationgrades> wrapper = new QueryWrapper<>();
        wrapper.eq("a.studentid", studentid);
        if (semester.isEmpty() == false) {
            wrapper.eq("c.semester", semester);
        }
        if (param.isEmpty() == false) {
            wrapper.and(q -> q.eq("c.courseid", param).or().likeRight("c.cname", param));
        }
        // 设置分页信息, 查第3页, 每页2条数据
        Page<examinationgrades> page = new Page<>(pages, 15);
        // 执行分页查询
        Page<examinationgrades> userPage = page.setRecords(this.examinationGradesMapper.studentfindselfexamination(wrapper, page));
        // System.out.println("总页数 = " + userPage.getPages());
        int pagesnum = (int) userPage.getPages();
        // 获取分页查询结果
        session.removeAttribute("pagesnum");
        session.setAttribute("pagesnum", pagesnum);
        List<examinationgrades> records = userPage.getRecords();
        return records;
    }

    @Override
    public List<crouse> studenttakeexaminationmessages(HttpSession session) {
        student stu = (student) session.getAttribute("loginadmin");
        List<String> gradescourseid = gradesMapper.studentdiscourseid(stu.getStudentid());
        List<String> restartcourseid = restartMapper.studentdiscourseid(stu.getStudentid());
        List<String> list = new ArrayList<>();
        for (int i = 0; i < gradescourseid.size(); i++) {
            for (int j = 0; j < restartcourseid.size(); j++) {
                if (restartcourseid.get(j).equals(gradescourseid.get(i))) {
                    gradescourseid.remove(i);
                }
            }
        }
        int a = restartcourseid.size();
        int i = 0;
        int b = 0;
        while (b < a) {
            QueryWrapper<examination> wrapper = new QueryWrapper<>();
            wrapper.eq("courseid", restartcourseid.get(i)).eq("studentid", stu.getStudentid());
            QueryWrapper<restart> wrapper1 = new QueryWrapper<>();
            wrapper1.eq("courseid", restartcourseid.get(i)).eq("studentid", stu.getStudentid());
            examination ex = examinationMapper.selectOne(wrapper);
            restart rt = restartMapper.selectOne(wrapper1);
            if (ex != null && ex.getNum() - rt.getNum() == 1) {
                restartcourseid.remove(i);
                i--;
            }
            i++;
            b++;
        }
        a = gradescourseid.size();
        i = 0;
        b = 0;
        while (b < a) {
            QueryWrapper<examination> wrapper = new QueryWrapper<>();
            wrapper.eq("courseid", gradescourseid.get(i)).eq("studentid", stu.getStudentid());
            examination ex = examinationMapper.selectOne(wrapper);
            if (ex != null) {
                restartcourseid.remove(i);
                i--;
            }
            i++;
            b++;
        }
        session.setAttribute("txgc", gradescourseid);
        session.setAttribute("txrc", restartcourseid);
        list.addAll(gradescourseid);
        list.addAll(restartcourseid);
        if (list.size() == 0) {
            return null;
        } else {
            return crouseMapper.selectBatchIds(list);
        }
    }

    @Override
    public void submitexamination(HttpSession session) {
        student stu = (student) session.getAttribute("loginadmin");
        List<String> gradescourseid = (List<String>) session.getAttribute("txgc");
        List<String> restartcourseid = (List<String>) session.getAttribute("txrc");
        for (int i = 0; i < restartcourseid.size(); i++) {
            examinationMapper.plusteacherdeleteexamination( stu.getStudentid(),restartcourseid.get(i));
            QueryWrapper<restart> wrapper1 = new QueryWrapper<>();
            wrapper1.eq("courseid", restartcourseid.get(i)).eq("studentid", stu.getStudentid());
            restart rt = restartMapper.selectOne(wrapper1);
            examination ex = new examination();
            ex.setCourseid(restartcourseid.get(i));
            ex.setStudentid(stu.getStudentid());
            ex.setGrade(101);
            ex.setNum(rt.getNum() + 1);
            examinationMapper.insert(ex);
        }
        for (int i = 0; i < gradescourseid.size(); i++) {
            examinationMapper.plusteacherdeleteexamination( stu.getStudentid(),gradescourseid.get(i));
            examination ex = new examination();
            ex.setCourseid(gradescourseid.get(i));
            ex.setStudentid(stu.getStudentid());
            ex.setGrade(101);
            ex.setNum(1);
            examinationMapper.insert(ex);

        }
    }
//===================================教师操作
    @Override
    public List<examinationgrades> findstudentexamination(String teacherid, String semester, String classes, String cname, String param, int pages, HttpSession session) {
        QueryWrapper<examinationgrades> wrapper = new QueryWrapper<>();
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
        Page<examinationgrades> page = new Page<>(pages, 15);
        // 执行分页查询
        Page<examinationgrades> userPage = page.setRecords(this.examinationGradesMapper.findstudentexamination(wrapper,page));
        // System.out.println("总页数 = " + userPage.getPages());
        int  pagesnum = (int) userPage.getPages();
        // 获取分页查询结果
        session.removeAttribute("pagesnum");
        session.setAttribute("pagesnum",pagesnum);
        List<examinationgrades> records = userPage.getRecords();
        return records;
    }
}
