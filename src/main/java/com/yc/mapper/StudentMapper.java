package com.yc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yc.bean.student;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface StudentMapper extends BaseMapper<student> {
    @Select("select studentid from student where classes = #{classes}")
    public List<String> findinsertxh(@Param("classes") String classes);
    //==========================================教务操作
    @Select("select classes from student where college = #{college} group by classes")
    public List<String> plusfindclaases(@Param("college")String college);
    @Select("select studentname from student where studentid = #{studentid}")
    public String plusfindstudentname(@Param("studentid") String studenid);

}
