package com.yc.biz;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yc.bean.examinationnotify;
import com.yc.bean.restartnotify;
import com.yc.mapper.ExaminationnotifyMapper;
import com.yc.mapper.RestartMapper;
import com.yc.mapper.RestartnotifyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RestartnotifyBizImpl implements RestartnotifyBiz{
    @Autowired
    RestartnotifyMapper restartnotifyMapper;
    @Override
    public void insertrestartnofify(restartnotify restartnotify){
        restartnotify  restartnotify1 = restartnotifyMapper.selectById(restartnotify.getCollege());
            if ( restartnotify1!=null){
                restartnotifyMapper.deleteById(restartnotify.getCollege());
            }
           restartnotifyMapper.insert(restartnotify);
    }

    @Override
    public restartnotify findrestartnotify(String college) {
        return restartnotifyMapper.selectById(college);
    }
}
