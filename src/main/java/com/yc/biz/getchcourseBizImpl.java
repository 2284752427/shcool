package com.yc.biz;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yc.bean.chcourse;
import com.yc.mapper.ChcourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class getchcourseBizImpl implements getchcourseBiz{
    @Autowired
    ChcourseMapper chcourseMapper;

    @Override
    public List<String> getalltype() {
        QueryWrapper<String> qw=new QueryWrapper<>();
        qw.groupBy("chtype");
        return chcourseMapper.findalltype(qw);
    }

    @Override
    public void deletechcourse(Integer chcourseid) {
        QueryWrapper<chcourse> qw=new QueryWrapper<>();
        qw.eq("chcourseid",chcourseid);
        chcourseMapper.delete(qw);
    }

    @Override
    public void addchcourse(String chtype, String chname, String introduction) {
        chcourse c=new chcourse();
        c.setTypeid("A");
        c.setChname(chname);
        c.setChtype(chtype);
        c.setIntroduction(introduction);
        chcourseMapper.insert(c);
    }

    @Override
    public void updatechcourse(Integer chid,String chtype, String chname, String introduction) {
        chcourse c=new chcourse();
        c.setChname(chname);
        c.setChtype(chtype);
        c.setIntroduction(introduction);
        QueryWrapper<chcourse> qw=new QueryWrapper<>();
        qw.eq("chcourseid",chid);
        chcourseMapper.update(c,qw);
    }

    @Override
    public List<chcourse> selectchcourse(String chname,int pagename) {
        QueryWrapper<chcourse> qw=new QueryWrapper<>();
        qw.like("chname",chname);
        IPage<chcourse> ipage=new Page<>(pagename,5);
        ipage=chcourseMapper.selectPage(ipage,qw);
        List<chcourse> itemList = ipage.getRecords();
        return itemList;
    }

    @Override
    public int count(String chname) {
        QueryWrapper<chcourse> qw=new QueryWrapper<>();
        qw.like("chname",chname);
        qw.select("count(chcourseid) as chcourseid ");
        return chcourseMapper.selectOne(qw).getChcourseid();
    }


}
