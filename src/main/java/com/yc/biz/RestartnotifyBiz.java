package com.yc.biz;

import com.yc.bean.examinationnotify;
import com.yc.bean.restartnotify;

public interface RestartnotifyBiz {
    public void insertrestartnofify(restartnotify restartnotify);
    public restartnotify findrestartnotify(String college);
}
