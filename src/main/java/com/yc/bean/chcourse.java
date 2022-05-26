package com.yc.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class chcourse {
    private String typeid;
    @TableId(type = IdType.AUTO,value = "chcourseid")
    private Integer chcourseid;
    private String chtype;
    private String chname;
    private String introduction;
}
