package com.yc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yc.bean.restart;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface RestartMapper extends BaseMapper<restart> {
    @Delete("delete from restart where studentid=#{studentid} and courseid=#{courseid}")
    public void plusteacherdeleterestart(@Param("studentid") String studentid, @Param("courseid") String courseid);
    @Update("update restart set grade=#{grade} where studentid=#{studentid} and courseid=#{courseid}")
    public void plusteacherupdaterestart(@Param("grade") Integer grade,@Param("studentid") String studentid,@Param("courseid") String courseid);
    //=============================学生
    @Select("select courseid from restart where studentid = #{studentid} and grade<60")
    public List<String> studentdiscourseid(@Param("studentid") String studentid);
}
