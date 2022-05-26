package com.yc.biz;

import com.yc.bean.student;
import com.yc.bean.teacher;

public interface AdminBiz {
    public student lgstudent(String id, String pwd);
    public teacher lgteacher(String id,String pwd);
    public int register(teacher teacher);
}
