package com.yc.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.yc.bean.newchchourse;
import com.yc.bean.studentchgrade;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface StudentchgradeMapper extends BaseMapper<studentchgrade> {
    @Select("select chid,chname,grades from studentchgrade a join chcourse b on RIGHT(chid,3)=b.chcourseid ${ew.customSqlSegment}")
    List<newchchourse> selectmych(@Param(Constants.WRAPPER) Wrapper<studentchgrade> userWrapper);
}
