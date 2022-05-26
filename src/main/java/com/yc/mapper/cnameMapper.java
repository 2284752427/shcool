package com.yc.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.yc.bean.cname;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface cnameMapper extends BaseMapper<cname> {
    @Select("select a.classno,b.semester,a.week,a.weekday,a.time,cname,teachername from curriculum a join crouse " +
            "b on a.coursid=b.courseid join teacher c on c.teacherid=b.teacherid ${ew.customSqlSegment}")
    List<cname> findallcourse(@Param(Constants.WRAPPER) Wrapper<cname> userWrapper);

    @Select("select semester from crouse group by semester")
    List<String> findsemester(@Param(Constants.WRAPPER) Wrapper<cname> userWrapper);

    //=============================
    @Select("select classno,week,weekday,time,cname from curriculum a join crouse b on a.coursid=b.courseid " +
            "where teacherid='${teacherid}' and week='${week}' and a.semester='${semester}'")
    List<cname> findcoursebyteacher(@Param("teacherid") String teacherid, @Param("week") int week, @Param("semester") String semeseter);

}
