package com.yc.biz;

import com.yc.bean.examinationnotify;

public interface ExaminationnotifyBiz {
    public void insertexaminationnofify(examinationnotify examinationnotify);
    public examinationnotify findexaminationnotify(String college);
}
