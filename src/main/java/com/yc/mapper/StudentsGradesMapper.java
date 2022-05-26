package com.yc.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yc.vo.studentsgrades;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface StudentsGradesMapper extends BaseMapper<studentsgrades> {
    @Select("select a.courseid,a.semester,a.classes,d.studentid,d.studentname,a.cname,a.credit,c.grade,c.point from crouse a join teacher b on a.teacherid = b.teacherid join grades c on c.courseid = a.courseid join student d on d.studentid = c.studentid ${ew.customSqlSegment}")
    List<studentsgrades> findstudentgrades(@Param(Constants.WRAPPER) Wrapper<studentsgrades> wrapper, Page<studentsgrades> page);
    @Select("select c.semester,a.courseid,c.cname,c.credit,a.grade,a.point from grades a join student b on a.studentid = b.studentid join crouse c on a.courseid = c.courseid ${ew.customSqlSegment}")
    List<studentsgrades> studentfindselfgrades(@Param(Constants.WRAPPER) Wrapper<studentsgrades> wrapper, Page<studentsgrades> page);

}
