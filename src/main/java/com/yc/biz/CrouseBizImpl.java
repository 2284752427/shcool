package com.yc.biz;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yc.bean.crouse;
import com.yc.bean.student;
import com.yc.bean.teacher;
import com.yc.mapper.CrouseMapper;
import com.yc.mapper.PluscrouseMapper;
import com.yc.vo.pluscrouse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
@Transactional
public class CrouseBizImpl implements CrouseBiz {
    @Autowired
    CrouseMapper crouseMapper;
    @Autowired
    PluscrouseMapper pluscrouseMapper;

    @Override
    public List<String> findteachersjbj(String teacherid) {
        return crouseMapper.findteahcersjbj(teacherid);
    }

    @Override
    public List<String> findteacherbj(String teacherid, String semester, String cname) {
        QueryWrapper<crouse> wrapper = new QueryWrapper<>();
        if (semester.isEmpty() && cname.isEmpty()) {
            wrapper.eq("teacherid", teacherid);
        } else if (semester.isEmpty() && cname.isEmpty() == false) {
            wrapper.eq("teacherid", teacherid).eq("cname", cname);
        } else if (semester.isEmpty() == false && cname.isEmpty()) {
            wrapper.eq("teacherid", teacherid).eq("semester", semester);
        } else {
            wrapper.eq("teacherid", teacherid).eq("semester", semester).eq("cname", cname);
        }
        return crouseMapper.findteahcerbj(wrapper);
    }

    @Override
    public List<String> findteacherxq(String teacherid, String cname, String classes) {
        QueryWrapper<crouse> wrapper = new QueryWrapper<>();
        if (cname.isEmpty() && classes.isEmpty()) {
            wrapper.eq("teacherid", teacherid);
        } else if (classes.isEmpty() && cname.isEmpty() == false) {
            wrapper.eq("teacherid", teacherid).eq("cname", cname);
        } else if (classes.isEmpty() == false && cname.isEmpty()) {
            wrapper.eq("teacherid", teacherid).eq("classes", classes);
        } else {
            wrapper.eq("teacherid", teacherid).eq("classes", classes).eq("cname", cname);
        }

        return crouseMapper.findteahcerxq(wrapper);
    }

    @Override
    public List<String> findteacherck(String teacherid, String semester, String classes) {
        QueryWrapper<crouse> wrapper = new QueryWrapper<>();
        if (semester.isEmpty() && classes.isEmpty()) {
            wrapper.eq("teacherid", teacherid);
        } else if (semester.isEmpty() && classes.isEmpty() == false) {
            wrapper.eq("teacherid", teacherid).eq("classes", classes);
        } else if (semester.isEmpty() == false && classes.isEmpty()) {
            wrapper.eq("teacherid", teacherid).eq("semester", semester);
        } else {
            wrapper.eq("teacherid", teacherid).eq("semester", semester).eq("classes", classes);
        }
        return crouseMapper.findteahcerck(wrapper);
    }

    @Override
    public List<String> findinsertkch(String teacherid) {
        return crouseMapper.findinsertkch(teacherid);
    }

    @Override
    public String findinsertkcm(String courseid) {
        return crouseMapper.findinsertkcm(courseid);
    }

    @Override
    public String findinsertbj(String courseid) {
        return crouseMapper.findinsertbj(courseid);
    }

    @Override
    public List<String> findexaminationsemester(String teacherid) {
        return crouseMapper.findexaminationsemester(teacherid);
    }

    @Override
    public List<String> findexaminationclasses(String teacherid, String semester) {
        return crouseMapper.findexaminationclasses(teacherid,semester);
    }

    @Override
    public List<String> findexaminationcname(String teacherid, String semester, String classes) {
        return crouseMapper.findexaminationcname(teacherid,semester,classes);
    }

    @Override
    public List<String> findrestartsemester(String teacherid) {
        return crouseMapper.findrestartsemester(teacherid);
    }

    @Override
    public List<String> findrestartclasses(String teacherid, String semester) {
        return crouseMapper.findrestartclasses(teacherid,semester);
    }

    @Override
    public List<String> findrestartcname(String teacherid, String semester, String classes) {
        return crouseMapper.findrestartcname(teacherid,semester,classes);
    }

    @Override
    public List<crouse> teachercrouse(String semester,String classes,String param,int pages,HttpSession session) {
        LambdaQueryWrapper<crouse> wrapper = new LambdaQueryWrapper<>();
        teacher th = (teacher)session.getAttribute("loginadmin");
        wrapper.eq(crouse::getTeacherid,th.getTeacherid());
        if (semester.isEmpty()==false){
            wrapper.eq(crouse::getSemester,semester);
        }
        if (classes.isEmpty()==false){
            wrapper.eq(crouse::getClasses,classes);
        }
        if (param.isEmpty()==false){
            wrapper.and(q->q.eq(crouse::getCourseid,param).or().likeRight(crouse::getCname,param));
        }
        // 设置分页信息, 查第3页, 每页2条数据
        Page<crouse> page = new Page<>(pages, 10);
        // 执行分页查询
        Page<crouse> userPage = crouseMapper.selectPage(page, wrapper);
        int pagesnum = (int) userPage.getPages();
        // 获取分页查询结果
        session.removeAttribute("pagesum");
        session.setAttribute("pagesnum", pagesnum);
        List<crouse> records = userPage.getRecords();
        return records;
    }

    @Override
    public List<String> teachercrousesemester(String teacherid) {
        return crouseMapper.teachercrousesemester(teacherid);
    }

    @Override
    public List<String> teachercrouseclasses(String teacherid, String semester) {
        return crouseMapper.teachercrouseclasses(teacherid,semester);
    }

    //====================================================================================教务操作
    @Override
    public List<String> plusteacherfindclasses(String semester,HttpSession session) {
        teacher th = (teacher) session.getAttribute("loginadmin");
        return crouseMapper.plusteacherfindclasses(th.getCollege(),semester);
    }

    @Override
    public List<String> plusteacherfindsemester(HttpSession session){
        teacher th = (teacher) session.getAttribute("loginadmin");
        return crouseMapper.plusteacherfinsemester(th.getCollege());
    }

    @Override
    public List<String> plusteacherfindcname(String semester, String classes) {
        return crouseMapper.plusteacherfindcname(semester,classes);
    }

    @Override
    public List<String> plusteacherfindexaminationsemester(String college) {
        return crouseMapper.plusteacherfindexaminationsemester(college);
    }

    @Override
    public List<String> plusteacherfindexaminationclasses(String college, String semester) {
        return crouseMapper.plusteacherfindexaminationclasses(college,semester);
    }

    @Override
    public List<String> plusteacherfindexaminationcname(String college, String semester, String classes) {
        return crouseMapper.plusteacherfindexaminationcname(college,semester,classes);
    }
    @Override
    public List<String> plusteacherfindrestartsemester(String college) {
        return crouseMapper.plusteacherfindrestartsemester(college);
    }

    @Override
    public List<String> plusteacherfindrestartclasses(String college, String semester) {
        return crouseMapper.plusteacherfindrestartclasses(college,semester);
    }

    @Override
    public List<String> plusteacherfindrestartcname(String college, String semester, String classes) {
        return crouseMapper.plusteacherfindrestartcname(college,semester,classes);
    }


    @Override
    public List<pluscrouse> plusfindcrouse(String college,String semester,String classes,String param, int pages, HttpSession session) {
       QueryWrapper<pluscrouse> wrapper = new QueryWrapper<>();
        wrapper.eq("c.college",college);
        if (semester.isEmpty()==false){
            wrapper.eq("a.semester",semester);
        }
        if (classes.isEmpty()==false){
            wrapper.eq("a.classes",classes);
        }if (param.isEmpty()==false){
            wrapper.and(q->q.eq("a.courseid",param).or().likeRight("a.cname",param));
        }
        // 设置分页信息, 查第3页, 每页2条数据
        Page<pluscrouse> page = new Page<>(pages, 10);
        // 执行分页查询
        Page<pluscrouse> userPage = page.setRecords(this.pluscrouseMapper.plusfindcrouse(wrapper,page));
        int  pagesnum = (int) userPage.getPages();
        // 获取分页查询结果
        session.removeAttribute("pagesum");
        session.setAttribute("pagesnum",pagesnum);
        List<pluscrouse> records = userPage.getRecords();
        return records;
    }

    @Override
    public void plusupdatacrouse(crouse crouse) {
       crouseMapper.updateById(crouse);
    }

    @Override
    public void plusdeleteAllcrouse(List<String> list) {
        crouseMapper.deleteBatchIds(list);
    }

    @Override
    public void plusinsertcrouse(crouse crouse) {
        crouseMapper.insert(crouse);
    }
//=====================================学生操作
    @Override
    public List<String> studentfindgradessemester(String studentid) {
        return crouseMapper.studentfindgradessemester(studentid);
    }

    @Override
    public List<String> studentfindexaminationsemester(String studentid) {
        return crouseMapper.studentfindexaminationsemester(studentid);
    }

    @Override
    public List<String> studentfindrestartsemester(String studentid) {
        return crouseMapper.studentfindrestartsemester(studentid);
    }

}
