package com.yc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.yc.bean.grades;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface GradesMapper extends BaseMapper<grades> {
//    @Update("update grades set grade = #{grade} where cno = #{cno} and sno = #{sno}")
//    int updatagrades(@Param("grade") String grade,@Param("cno") String cno,@Param("sno") String sno);
    @Delete("delete from grades where studentid = #{studentid} and courseid = #{courseid}")
    int deletegrades(@Param("studentid") String studentid,@Param("courseid") String courseid);
    @Select("select studentid from grades where courseid = #{courseid}")
    public List<String> findinsertxh(@Param("courseid") String courseid);
//=========================================教务操作
    @Delete("delete from grades where studentid = #{studentdi}")
    public void plusdeletestudentgrades(@Param("studentid") String studentid);
    @Select("select a.grade from grades a join student b on a.studentid=b.studentid join crouse c on c.courseid = a.courseid where a.studentid= #{studentid} and c.cname= #{cname}")
    public Integer plusteacherfindgarade(@Param("studentid") String studentid,@Param("cname") String cname);
    @Select("select round(avg(point),2) from grades a join crouse b on a.courseid = b. courseid where a.studentid= #{studentid} and b.semester = #{semester}")
    public Float plusteacherfindpoint(@Param("studentid") String studentid,@Param("semester") String semester);
//============================================学生
    @Select("select courseid from grades where studentid = #{studentid} and grade<60")
    public List<String> studentdiscourseid(@Param("studentid") String studentid);

}
