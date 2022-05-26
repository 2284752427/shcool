package com.yc.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yc.vo.examinationgrades;
import com.yc.vo.studentsgrades;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ExaminationGradesMapper extends BaseMapper<examinationgrades> {
    @Select("select c.semester,b.classes,a.studentid,b.studentname,c.courseid,c.cname,c.credit,a.grade from examination a join student b on a.studentid = b.studentid join crouse c on a.courseid = c.courseid ${ew.customSqlSegment}")
    public List<examinationgrades> plusteacherfindeaminationgrades(@Param(Constants.WRAPPER) Wrapper<examinationgrades> wrapper, Page<examinationgrades> page);
    @Select("select c.semester,a.courseid,c.cname,c.credit,a.grade from examination a join student b on a.studentid = b.studentid join crouse c on a.courseid = c.courseid ${ew.customSqlSegment}")
    List<examinationgrades> studentfindselfexamination(@Param(Constants.WRAPPER) Wrapper<examinationgrades> wrapper, Page<examinationgrades> page);
    //===================教师操作
    @Select("select a.courseid,a.semester,a.classes,d.studentid,d.studentname,a.cname,a.credit,c.grade from crouse a  join examination c on c.courseid = a.courseid join student d on d.studentid = c.studentid ${ew.customSqlSegment}")
    public List<examinationgrades> findstudentexamination(@Param(Constants.WRAPPER) Wrapper<examinationgrades> wrapper,Page<examinationgrades> page);

}
