package com.yc.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yc.bean.crouse;
import com.yc.vo.studentsgrades;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CrouseMapper extends BaseMapper<crouse> {
    //=================================================================学院老师操作
    @Select("select classes from crouse where teacherid = #{teacherid} GROUP BY classes;")
    public List<String> findteahcersjbj(@Param("teacherid")  String teacherid);
    @Select("select classes from crouse ${ew.customSqlSegment} GROUP BY classes;")
    public List<String> findteahcerbj(@Param(Constants.WRAPPER) Wrapper<crouse> wrapper);
    @Select("select semester from crouse ${ew.customSqlSegment} GROUP BY semester;")
    public List<String> findteahcerxq(@Param(Constants.WRAPPER) Wrapper<crouse> wrapper);
    @Select("select cname from crouse ${ew.customSqlSegment} GROUP BY cname;")
    public List<String> findteahcerck(@Param(Constants.WRAPPER) Wrapper<crouse> wrapper);
    @Select("select courseid from crouse where teacherid = #{teacherid} group by courseid")
    public List<String> findinsertkch(@Param("teacherid") String teacherid);
    @Select("select cname from crouse where courseid = #{courseid}")
    public String findinsertkcm(@Param("courseid") String courseid);
    @Select("select classes from crouse where courseid = #{courseid}")
    public String findinsertbj(@Param("courseid") String courseid);
    @Select("select a.semester from crouse a  join examination c on c.courseid = a.courseid  where a.teacherid = #{teacherid} GROUP BY a.semester")
    public List<String> findexaminationsemester(@Param("teacherid") String teacherid);
    @Select("select a.classes from crouse a  join examination c on c.courseid = a.courseid  where a.teacherid = #{teacherid} and a.semester = #{semester} GROUP BY a.classes")
    public List<String> findexaminationclasses(@Param("teacherid") String teacherid,@Param("semester") String semester);
    @Select("select a.cname from crouse a  join examination c on c.courseid = a.courseid  where a.teacherid = #{teacherid} and a.semester = #{semester} and a.classes = #{classes} GROUP BY a.cname")
    public List<String> findexaminationcname(@Param("teacherid") String teacherid,@Param("semester") String semester,@Param("classes") String classes);

    @Select("select a.semester from crouse a  join restart c on c.courseid = a.courseid  where a.teacherid = #{teacherid} GROUP BY a.semester")
    public List<String> findrestartsemester(@Param("teacherid") String teacherid);
    @Select("select a.classes from crouse a  join restart c on c.courseid = a.courseid  where a.teacherid = #{teacherid} and a.semester = #{semester} GROUP BY a.classes")
    public List<String> findrestartclasses(@Param("teacherid") String teacherid,@Param("semester") String semester);
    @Select("select a.cname from crouse a  join restart c on c.courseid = a.courseid  where a.teacherid = #{teacherid} and a.semester = #{semester} and a.classes = #{classes} GROUP BY a.cname")
    public List<String> findrestartcname(@Param("teacherid") String teacherid,@Param("semester") String semester,@Param("classes") String classes);

    @Select("select semester from crouse where teacherid= #{teacherid} GROUP BY semester")
    public List<String> teachercrousesemester(@Param("teacherid") String teacherid);
    @Select("select classes from crouse where teacherid = #{teacherid} and semester = #{semester} GROUP BY classes")
    public List<String> teachercrouseclasses(@Param("teacherid") String teacherid,@Param("semester") String semester);
    //==================================================================教务老师操作
    @Select("select a.classes from crouse a join student b on a.classes = b.classes where b.college= #{college} and a.semester = #{semester} GROUP BY a.classes;")
    public List<String> plusteacherfindclasses(@Param("college") String college,@Param("semester") String semester);
    @Select("select a.semester from crouse a join student b on a.classes = b.classes where b.college= #{college} GROUP BY semester;")
    public List<String> plusteacherfinsemester(@Param("college") String college) ;
    @Select("select a.cname from crouse a where  a.semester = #{semester} and a.classes = #{classes} GROUP BY a.cname;")
    public List<String> plusteacherfindcname(@Param("semester") String semester,@Param("classes") String classes);
    @Select("select b.semester from examination a join crouse b on a.courseid = b.courseid join student c on b.classes = c.classes where c.college= #{college} GROUP BY b.semester")
    public List<String> plusteacherfindexaminationsemester(@Param("college") String college);
    @Select("select b.classes from examination a join crouse b on a.courseid = b.courseid join student c on b.classes = c.classes where c.college= #{college} and b.semester= #{semester} GROUP BY b.classes")
    public List<String> plusteacherfindexaminationclasses(@Param("college") String college,@Param("semester") String semester);
    @Select("select b.cname from examination a join crouse b on a.courseid = b.courseid join student c on b.classes = c.classes where c.college=#{college} and b.semester=#{semester}  and b.classes= #{classes} GROUP BY b.cname")
    public List<String> plusteacherfindexaminationcname(@Param("college") String college,@Param("semester") String semester,@Param("classes") String classes);
    @Select("select b.semester from restart a join crouse b on a.courseid = b.courseid join student c on b.classes = c.classes where c.college= #{college} GROUP BY b.semester")
    public List<String> plusteacherfindrestartsemester(@Param("college") String college);
    @Select("select b.classes from restart a join crouse b on a.courseid = b.courseid join student c on b.classes = c.classes where c.college= #{college} and b.semester= #{semester} GROUP BY b.classes")
    public List<String> plusteacherfindrestartclasses(@Param("college") String college,@Param("semester") String semester);
    @Select("select b.cname from restart a join crouse b on a.courseid = b.courseid join student c on b.classes = c.classes where c.college=#{college} and b.semester=#{semester}  and b.classes= #{classes} GROUP BY b.cname")
    public List<String> plusteacherfindrestartcname(@Param("college") String college,@Param("semester") String semester,@Param("classes") String classes);
//===============================================学生操作
    @Select("select c.semester from grades a join student b on a.studentid = b.studentid join crouse c on a.courseid = c.courseid where a.studentid= #{studentid} GROUP BY c.semester")
    public List<String> studentfindgradessemester(@Param("studentid") String studentid);
    @Select("select c.semester from examination a join student b on a.studentid = b.studentid join crouse c on a.courseid = c.courseid where a.studentid= #{studentid} GROUP BY c.semester")
    public List<String> studentfindexaminationsemester(@Param("studentid") String studentid);
    @Select("select c.semester from restart a join student b on a.studentid = b.studentid join crouse c on a.courseid = c.courseid where a.studentid= #{studentid} GROUP BY c.semester")
    public List<String> studentfindrestartsemester(@Param("studentid") String studentid);

    @Select("select * from crouse where teacherid=(select teacherid from crouse where courseid='${courseid}')")
    List<crouse> Selectteacher(@Param("courseid") String courseid);

    @Select("select classes from crouse ${ew.customSqlSegment}")
    List<String> classbyteacher(@Param(Constants.WRAPPER) Wrapper<String> userWrapper);

    @Select("select classes from crouse ${ew.customSqlSegment}")
    String findclasses(@Param(Constants.WRAPPER) Wrapper<String> userWrapper);


}
