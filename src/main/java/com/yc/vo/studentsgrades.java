package com.yc.vo;

import com.yc.bean.grades;
import lombok.Data;

import java.io.Serializable;
@Data
public class studentsgrades extends grades implements Serializable {
       private String semester;
       private String classes;
       private String studentname;
       private String cname;
       private Float credit;


}
