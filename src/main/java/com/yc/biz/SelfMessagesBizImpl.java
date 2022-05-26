package com.yc.biz;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yc.bean.student;
import com.yc.bean.teacher;
import com.yc.mapper.StudentMapper;
import com.yc.mapper.TeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;

//老师或学生个人信息查询
@Service
@Transactional
public class SelfMessagesBizImpl implements SelfMessagesBiz {
    @Autowired
    TeacherMapper teacherMapper;
    @Autowired
    StudentMapper studentMapper;

    @Override
    public teacher teacherselfmessages(String id) {
        QueryWrapper<teacher> wrapper = new QueryWrapper();
        wrapper.eq("teacherid",id);
        return teacherMapper.selectOne(wrapper);
    }

    @Override
    public void updatateacherselfmessages(teacher teacher) {
        teacherMapper.updateById(teacher);
    }

    @Override
    public student studentselfmessages(String studentid) {
        QueryWrapper<student> wrapper = new QueryWrapper<>();
        wrapper.eq("studentid",studentid);
        return studentMapper.selectOne(wrapper);
    }

    @Override
    public void updatastudentselfmessages(student student) {
        studentMapper.updateById(student);
    }

    @Override
    public int testUploadFile(MultipartFile file, HttpSession session) {
        //0代表上传失败
        if(file.isEmpty()){
            return 0;
        }
        //得到原本的（前端传过来的）文件名
        String OriginalFilename = file.getOriginalFilename();
        System.out.println(OriginalFilename);
        //加上时间戳 新名字 = 时间戳.原文件后缀名
        String fileName = System.currentTimeMillis()+"."+OriginalFilename.substring(OriginalFilename.lastIndexOf(".")+1);
        //文件存放路径。现在存在本地，之后可以存在数据库中  路径的最后别忘了加两个\\ 如果不加，字符串拼接会拼到父目录里面去
        session.setAttribute("touxiang",fileName);
//        String filePath = "D:\\tomcat85\\webapps\\img\\";
        String filePath = "\\var\\lib\\tomcat8\\webapps\\images\\";
        // String filePath = "182.92.151.98:8080\\images\\";
        File dest = new File(filePath+fileName);
        //如果不存在该目录就创建文件夹
        if(!dest.getParentFile().exists())
            dest.getParentFile().mkdirs();
        try {
            //保存上传的文件
            file.transferTo(dest);
        }catch (Exception e){
            e.printStackTrace();
            return 0;   //抓到异常就上传失败
        }
        //没有异常就上传成功
        return 1;

    }

}
