package com.yc.biz;



import com.yc.bean.chcourse;

import java.util.List;

public interface getchcourseBiz {
    public List<String>  getalltype();
    public void deletechcourse(Integer chcourseid);
    public void addchcourse(String chtype,String chname,String introduction);
    public void updatechcourse(Integer chid,String chtype,String chname,String introduction);
    public List<chcourse> selectchcourse(String chname, int pagename);
    public int count(String chname);
}
