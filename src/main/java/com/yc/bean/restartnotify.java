package com.yc.bean;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class restartnotify {
    @TableId
    public String college;
    public String startdate;
    public String enddate;

}
