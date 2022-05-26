package com.yc.biz;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yc.bean.newchchourse;
import com.yc.bean.studentchgrade;
import com.yc.mapper.StudentchgradeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class studentchBizImpl implements stduentchBiz {
    @Autowired
    StudentchgradeMapper studentchgradeMapper;
    @Override
    public void studentaddch(String studentid, String chid) {
        studentchgrade studentchgrade=new studentchgrade();
        studentchgrade.setStudentid(studentid);
        studentchgrade.setChid(chid);
        studentchgradeMapper.insert(studentchgrade);
    }

    @Override
    public boolean howstudentch(String studentid) {
        QueryWrapper<studentchgrade> qw=new QueryWrapper<>();
        qw.eq("studentid",studentid);
        qw.select("studentid");
        int a=studentchgradeMapper.selectCount(qw);
        if (a<5){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public List<newchchourse> selectmych(String studentid) {
        QueryWrapper<studentchgrade> qw=new QueryWrapper<>();
        qw.eq("studentid",studentid);
        return studentchgradeMapper.selectmych(qw);
    }

    @Override
    public void deletemych(String chid, String studentid) {
        QueryWrapper<studentchgrade> qw=new QueryWrapper<>();
        qw.eq("studentid",studentid).eq("chid",chid);
        studentchgradeMapper.delete(qw);
    }
}
