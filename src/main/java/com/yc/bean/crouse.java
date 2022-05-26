package com.yc.bean;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
@Data
public class crouse implements Serializable {
    @TableId
    private String courseid;
    private String cname;
    private String classes;
    private String teacherid;
    private String semester;
    private String ctype;
    private Integer hours;
    private Float credit;
}
