package com.yc.biz;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.yc.bean.student;
import com.yc.bean.teacher;
import com.yc.mapper.StudentMapper;
import com.yc.mapper.TeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdminBizImpl implements AdminBiz{
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private TeacherMapper teacherMapper;


    @Override
    public student lgstudent(String id, String pwd) {
        QueryWrapper<student> wrapper = new QueryWrapper<>();
        wrapper.eq("studentid",id).eq("studentpwd",pwd);
        return studentMapper.selectOne(wrapper);
    }

    @Override
    public teacher lgteacher(String id, String pwd) {
        QueryWrapper<teacher> wrapper = new QueryWrapper<>();
        wrapper.eq("teacherid",id).eq("teacherpwd",pwd);
        return teacherMapper.selectOne(wrapper);
    }

    @Override
    public int register(teacher teacher) {
        return teacherMapper.insert(teacher);
    }
}
