package com.yc.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yc.vo.pluscrouse;
import com.yc.vo.restartgrades;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PluscrouseMapper extends BaseMapper<pluscrouse> {
    @Select("select a.courseid,a.cname,a.classes,a.teacherid,b.teachername,a.semester,a.ctype,a.hours,a.credit from crouse a join teacher b on a.teacherid = b.teacherid join student c on c.classes = a.classes ${ew.customSqlSegment} GROUP BY a.courseid")
    public List<pluscrouse> plusfindcrouse(@Param(Constants.WRAPPER) Wrapper<pluscrouse> wrapper, Page<pluscrouse> page);
}
