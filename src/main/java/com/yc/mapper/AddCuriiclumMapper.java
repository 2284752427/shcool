package com.yc.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.yc.bean.addcurriculum;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AddCuriiclumMapper extends BaseMapper<addcurriculum> {
    @Select("select courseid,cname,teachername from crouse a join teacher b on a.teacherid=b.teacherid ${ew.customSqlSegment}")
    List<addcurriculum> findallcourse(@Param(Constants.WRAPPER) Wrapper<addcurriculum> userWrapper);
}
