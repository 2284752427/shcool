package com.yc.bean;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class examinationnotify {
    @TableId
    public String college;
    public String startdate;
    public String enddate;
}
