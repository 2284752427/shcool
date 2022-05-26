package com.yc.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yc.bean.cost;
import com.yc.vo.examinationgrades;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface CostMapper extends BaseMapper<cost> {
    @Select("select a.* from cost a join student b on a.classes=b.classes ${ew.customSqlSegment} GROUP BY a.classes,a.semester")
    public List<cost> plusteacherfindcost(@Param(Constants.WRAPPER) Wrapper<cost> wrapper, Page<cost> page);
    @Select("select a.classes from cost a join student b on a.classes =b.classes where b.college = #{college} group by a.classes")
    public List<String> plusteacherfindcostclasses(@Param("college") String college);
    @Select("select a.semester from cost a join student b on a.classes =b.classes where b.college = #{college} group by a.semester")
    public List<String> plusteacherfindcostsemester(@Param("college") String college);
    @Update("update cost set allcost = #{allcost} , bookcost = #{bookcost},surpluscost = #{surpluscost} where classes = #{classes} and semester = #{semester}")
    public void plusteacherupdatacost(@Param("allcost") Float allcost,@Param("bookcost") Float bookcost,@Param("surpluscost") Float surpluscost,@Param("classes") String classes,@Param("semester") String semester);
    @Delete("delete from cost where classes = #{classes} and semester = #{semester}")
    public void plusteacherdeletecost(@Param("classes") String classes,@Param("semester") String semester);
}
