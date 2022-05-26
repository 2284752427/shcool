package com.yc.vo;

import com.yc.bean.examination;
import lombok.Data;

@Data
public class restartgrades extends examination {
    private String semester;
    private String classes;
    private String studentname;
    private String cname;
    private Float credit;

}
