package com.yc.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class allstudentgrades implements Serializable {
    String studentid;
    String studentname;
    Integer[] grade;
    Float point;
}
