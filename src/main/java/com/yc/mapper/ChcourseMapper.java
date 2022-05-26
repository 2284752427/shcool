package com.yc.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.yc.bean.chcourse;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ChcourseMapper extends BaseMapper<chcourse> {
    @Select("select chtype from chcourse ${ew.customSqlSegment}")
    List<String> findalltype(@Param(Constants.WRAPPER) Wrapper<String> userWrapper);
}
