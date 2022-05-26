package com.yc.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class JsonModel implements Serializable {
    private Integer code;
    private  String msg;
    private Object data;

}
