package com.yc.bean;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

@Data
public class teacher implements Serializable {
    @TableId
    String teacherid;
    String teacherpwd;
    String teachername;
    String personid;
    String photo;
    String birthday;
    String sex;
    String natives;
    String college;
    String political;
    String intime;
    String address;
    String zip;
    String phone;
    String job;
    Integer type;

}
