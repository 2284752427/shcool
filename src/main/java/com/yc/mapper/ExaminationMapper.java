package com.yc.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yc.bean.examination;
import com.yc.vo.examinationgrades;
import com.yc.vo.studentsgrades;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ExaminationMapper extends BaseMapper<examination> {
    @Delete("delete from examination where studentid=#{studentid} and courseid=#{courseid}")
    public void plusteacherdeleteexamination(@Param("studentid") String studentid,@Param("courseid") String courseid);
    @Update("update examination set grade=#{grade} where studentid=#{studentid} and courseid=#{courseid}")
    public void plusteacherupdateexamination(@Param("grade") Integer grade,@Param("studentid") String studentid,@Param("courseid") String courseid);
    //===================学生操作
    @Select("select courseid from examination where studentid = #{studentid} and grade<60")
    public List<String> studentdisexamination(@Param("studentid") String studentid);
    //===========教师操作



}
