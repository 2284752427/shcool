package com.yc.bean;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
@Data
public class student implements Serializable {
    @TableId
    String studentid;
    String classes;
    String studentpwd;
    String studentname;
    String personid;
    String photo;
    String college;
    String birthday;
    String sex;
    String natives;
    String political;
    String intime;
    String address;
    String zip;
    String phone;
    String parentphone;


}
