package com.yc.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yc.bean.curriculum;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CurriculumMapper extends BaseMapper<curriculum> {
    @Select("select * from curriculum where coursid=any(select courseid from crouse where teacherid " +
            "in (select teacherid from crouse where courseid= '${courseid}')) and week= '${week}' " +
            "and weekday= '${weekday}' and time= '${time}' and semester= '${semester}'")
    List<curriculum> SelectTeacherCurriculum(@Param("courseid")String courseid, @Param("week")Integer week,
                                             @Param("weekday")Integer weekday, @Param("time")Integer time,
                                             @Param("semester")String semester);
    @Select("select coursid from curriculum where  classno= '${classno}' and week= '${week}' and " +
            "weekday= '${weekday}' and time= '${time}' and semester= '${semester}'")
    List<String> findcid(@Param("classno")String classno, @Param("week")Integer week,
                         @Param("weekday")Integer weekday, @Param("time")Integer time,
                         @Param("semester")String semester);

}
