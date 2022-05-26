package com.yc.biz;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yc.bean.student;
import com.yc.bean.teacher;
import com.yc.mapper.TeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.List;
@Service
@Transactional
public class TeacherBizImpl implements TeacherBiz{
    @Autowired
    TeacherMapper teacherMapper;
    @Override
    public List<teacher> findteacherAll(String college, String job, String param, int pages, HttpSession session) {
        LambdaQueryWrapper<teacher> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(teacher::getCollege,college);
        if (job.isEmpty()==false){
            wrapper.eq(teacher::getJob,job);
        }
        if (param.isEmpty()==false){
            wrapper.and(q->q.eq(teacher::getTeacherid,param).or().likeRight(teacher::getTeachername,param));
        }
        Page<teacher> page = new Page<>(pages,10);

        Page<teacher> userPage = teacherMapper.selectPage(page, wrapper);

        int pagesnum = (int) userPage.getPages();
        // 获取分页查询结果
        session.removeAttribute("pagesum");
        session.setAttribute("pagesnum", pagesnum);
        List<teacher> records = userPage.getRecords();
        return records;
    }

    @Override
    public List<String> findjob(String college) {
        return teacherMapper.findjob(college);
    }

    @Override
    public void updatateacher(teacher teacher) {
        teacherMapper.updateById(teacher);
    }

    @Override
    public void deleteteacher(List<String> tid) {
        teacherMapper.deleteBatchIds(tid);
    }

    @Override
    public String pluscrouseteachername(String teacherid) {
        return teacherMapper.pluscrouseteachername(teacherid);
    }

    @Override
    public List<String> pluscrouseteacherid() {
        return teacherMapper.pluscrouseteacherid();
    }
}
