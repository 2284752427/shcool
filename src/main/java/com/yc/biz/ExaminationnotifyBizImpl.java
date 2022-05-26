package com.yc.biz;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yc.bean.examinationnotify;
import com.yc.mapper.ExaminationnotifyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ExaminationnotifyBizImpl implements ExaminationnotifyBiz{
    @Autowired
    ExaminationnotifyMapper examinationnotifyMapper;
    @Override
    public void insertexaminationnofify(examinationnotify examinationnotify) {
        examinationnotify examinationnotify1 = examinationnotifyMapper.selectById(examinationnotify.getCollege());
        if (examinationnotify1!=null){
            examinationnotifyMapper.deleteById(examinationnotify.getCollege());
        }
        examinationnotifyMapper.insert(examinationnotify);
    }

    @Override
    public examinationnotify findexaminationnotify(String college) {
        return examinationnotifyMapper.selectById(college);
    }
}
