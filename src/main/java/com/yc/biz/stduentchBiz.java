package com.yc.biz;


import com.yc.bean.newchchourse;

import java.util.List;

public interface stduentchBiz {
    public void studentaddch(String studentid,String chid);
    public boolean howstudentch(String studentid);
    public List<newchchourse> selectmych(String studentid);
    public void deletemych(String chid,String studentid);
}
