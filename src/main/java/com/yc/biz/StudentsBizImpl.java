package com.yc.biz;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yc.bean.grades;
import com.yc.bean.student;
import com.yc.bean.teacher;
import com.yc.mapper.CrouseMapper;
import com.yc.mapper.GradesMapper;
import com.yc.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
@Transactional
public class StudentsBizImpl implements StudentBiz {
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    CrouseMapper crouseMapper;
    @Autowired
    GradesMapper gradesMapper;

    //    @Autowired
//    GradesMapper gradesMapper;
//
//
    @Override
    public List<student> findstudent(String sjbj, String param, int pages, HttpSession session) {
//        QueryWrapper<students> wrapper = new QueryWrapper<>();
//        wrapper.eq("sno",param).or().likeRight("sname",param).or().eq("sgrade",param).or().eq("sdept",param);
//        return studentsMapper.selectList(wrapper);
        LambdaQueryWrapper<student> wrapper = new LambdaQueryWrapper<>();
        if (param.isEmpty() && sjbj.isEmpty()==false) {
            wrapper.eq(student::getClasses, sjbj);
        }
        if(param.isEmpty() && sjbj.isEmpty()) {
            teacher th = (teacher) session.getAttribute("loginadmin");
            List<String> list = crouseMapper.findteahcersjbj(th.getTeacherid());
            if (list.isEmpty()==true || list==null){
                return null;
            }
            for (String s : list){
                wrapper.or().eq(student::getClasses,s);
            }
        }
        if (sjbj.isEmpty() && param.isEmpty()==false){
            wrapper.eq(student::getStudentid, param).or().likeRight(student::getStudentname, param);
        }
        if (sjbj.isEmpty()==false && param.isEmpty()==false){
            wrapper.eq(student::getClasses, sjbj).and(q->q.eq(student::getStudentid, param).or().likeRight(student::getStudentname, param));
        }

        // 设置分页信息, 查第3页, 每页2条数据
        Page<student> page = new Page<>(pages, 10);
        // 执行分页查询
        Page<student> userPage = studentMapper.selectPage(page, wrapper);
        int pagesnum = (int) userPage.getPages();
        // 获取分页查询结果
        session.removeAttribute("pagesum");
        session.setAttribute("pagesnum", pagesnum);
        List<student> records = userPage.getRecords();
        return records;
    }
    @Override
    public List<String> findinsertstudentid(String classes) {
        return studentMapper.findinsertxh(classes);
    }


//=============================================================教务操作
    @Override
    public List<student> plusfindstudent(String college, String classes,String political, String param, int pages, HttpSession session) {
        LambdaQueryWrapper<student> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(student::getCollege,college);
        if (classes.isEmpty()==false){
            wrapper.eq(student::getClasses,classes);
        }
        if (political.isEmpty()==false){
            wrapper.eq(student::getPolitical,political);
        }
        if (param.isEmpty()==false){
            wrapper.and(q->q.eq(student::getStudentid,param).or().likeRight(student::getStudentname,param));
        }
        Page<student> page = new Page<>(pages,10);
        Page<student> userPage = studentMapper.selectPage(page,wrapper);
        int pagesnum = (int) userPage.getPages();
        session.removeAttribute("pagesum");
        session.setAttribute("pagesnum", pagesnum);
        List<student> records = userPage.getRecords();
        return records;
    }

    @Override
    public List<String> plusfindclasses(String college) {
        return studentMapper.plusfindclaases(college);
    }

    @Override
    public void plusupdatastudent(student student) {
        studentMapper.updateById(student);
    }

    @Override
    public void plusdeltestudent(List<String> list) {
//        for (int i=0;i<list.size();i++){
//            gradesMapper.plusdeletestudentgrades(list.get(i));
//        }
        studentMapper.deleteBatchIds(list);
    }

    @Override
    public void plusinsertstudent(student student) {
        studentMapper.insert(student);
    }

    @Override
    public List<student> plusteacherfindstudent(String classes,int pages,String param,HttpSession session) {
        LambdaQueryWrapper<student> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(student::getClasses,classes);
        if (param.isEmpty()==false){
            wrapper.and(q->q.eq(student::getStudentid,param).or().likeRight(student::getStudentname,param));
        }
        // 设置分页信息, 查第3页, 每页2条数据
        Page<student> page = new Page<>(pages, 10);
        // 执行分页查询
        Page<student> userPage = studentMapper.selectPage(page, wrapper);
        int pagesnum = (int) userPage.getPages();
        // 获取分页查询结果
        session.removeAttribute("pagesum");
        session.setAttribute("pagesnum", pagesnum);
        List<student> records = userPage.getRecords();
        return records;

    }

    @Override
    public String plusstudentname(String studentid) {
        return studentMapper.plusfindstudentname(studentid);
    }


}
