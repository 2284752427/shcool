package com.yc.biz;

import com.yc.bean.student;
import com.yc.bean.teacher;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

public interface SelfMessagesBiz {
    public teacher teacherselfmessages(String id);
    public void updatateacherselfmessages(teacher teacher);
    public student studentselfmessages(String studentid);
    public void updatastudentselfmessages(student student);
    public int testUploadFile(MultipartFile file, HttpSession session);
}
