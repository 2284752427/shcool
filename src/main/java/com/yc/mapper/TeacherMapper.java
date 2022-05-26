package com.yc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yc.bean.teacher;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TeacherMapper extends BaseMapper<teacher> {
    @Select("select job from teacher where college = #{college} group by job")
    public List<String> findjob(@Param("college") String college);
    @Select("select teachername from teacher where teacherid = #{teacherid}")
    public String pluscrouseteachername(@Param("teacherid") String  teacherid);
    @Select("select teacherid from teacher")
    public List<String> pluscrouseteacherid();

}
