package com.yc.bean;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import com.baomidou.mybatisplus.annotation.SqlCondition;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

@Data
@ExcelTarget("grades")
public class grades implements Serializable {
    //    private String courseid;
//    //@TableField(condition = SqlCondition.EQUAL)
//    private String studentid;
//    private Integer grade;
//    private Float point;
    @Excel(name = "课程号", isImportField = "true")
    private String courseid;
    @Excel(name = "学号", isImportField = "ture")
    private String studentid;
    @Excel(name = "成绩", isImportField = "true")
    private Integer grade;
    @Excel(name = "绩点", isImportField = "true")
    private Float point;
}
