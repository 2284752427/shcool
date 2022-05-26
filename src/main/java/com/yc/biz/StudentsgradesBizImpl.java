package com.yc.biz;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.yc.bean.grades;
import com.yc.mapper.GradesMapper;
import com.yc.mapper.StudentsGradesMapper;
import com.yc.vo.studentsgrades;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
@Transactional
public class StudentsgradesBizImpl implements StudentsgradesBiz{
    @Autowired
    StudentsGradesMapper studentsGradesMapper;
    @Autowired
    GradesMapper gradesMapper;
    @Override
    public List<studentsgrades> findstudentgrades(String teacherid,String xq,String bj,String kc,String param, int pages, HttpSession session) {
//      select a.semester,a.classes,d.studentid,d.studentname,a.cname,a.credit,c.grade,c.point from crouse a join teacher b on a.teacherid = b.teacherid join grades c on c.courseid = a.courseid join student d on d.studentid = c.studentid
        QueryWrapper<studentsgrades> wrapper = new QueryWrapper<>();
        wrapper.eq("a.teacherid",teacherid);
        if (xq.isEmpty()==false){
            wrapper.eq("a.semester",xq);
        }
        if (bj.isEmpty()==false){
            wrapper.eq("a.classes",bj);
        }
        if (kc.isEmpty()==false){
            wrapper.eq("a.cname",kc);
        }
        if (param.isEmpty()==false){
            wrapper.and(q->q.eq("d.studentid",param).or().likeRight("d.studentname",param));
        }
        // 设置分页信息, 查第3页, 每页2条数据
        Page<studentsgrades> page = new Page<>(pages, 15);
        // 执行分页查询
        Page<studentsgrades> userPage = page.setRecords(this.studentsGradesMapper.findstudentgrades(wrapper,page));
       // System.out.println("总页数 = " + userPage.getPages());
        int  pagesnum = (int) userPage.getPages();
        // 获取分页查询结果
        session.removeAttribute("pagesnum");
        session.setAttribute("pagesnum",pagesnum);
        List<studentsgrades> records = userPage.getRecords();
        return records;
    }

    @Override
    public void deletestudentsgrades(String[] studentid,String[] courseid) {
       for (int i=0;i<studentid.length;i++){
           gradesMapper.deletegrades(studentid[i],courseid[i]);
       }
    }

    @Override
    public int insertstudentsgrades(grades grades) {
        return gradesMapper.insert(grades);
    }

    @Override
    public List<String> findinsertxh(String courseid) {
        return gradesMapper.findinsertxh(courseid);
    }

    @Override
    public void gradesImport(MultipartFile file) {
        List<grades> gd = Lists.newArrayList();
        try {
            ImportParams importParams = new ImportParams();
            importParams.setHeadRows(1);
//            importParams.setTitleRows(0);
            gd = ExcelImportUtil.importExcel(file.getInputStream(), grades.class, importParams);
        } catch (Exception e) {
            e.printStackTrace();
        }
       for (int i=0;i<gd.size();i++){
            grades g = gd.get(i);
           gradesMapper.insert(g);
       }

    }

    //=================================================================教务操作
    @Override
    public Integer plusteacherfindgrade(String studentid, String cname) {
        return gradesMapper.plusteacherfindgarade(studentid,cname);
    }

    @Override
    public Float plusteacherfindpoint(String studentid,String semester) {
        return gradesMapper.plusteacherfindpoint(studentid,semester);
    }

//===========================================学生操作
@Override
public List<studentsgrades> studentfindselfgrades(String studentid, String semester, String param, int pages, HttpSession session) {
    QueryWrapper<studentsgrades> wrapper = new QueryWrapper<>();
    wrapper.eq("a.studentid",studentid);
    if (semester.isEmpty()==false){
        wrapper.eq("c.semester",semester);
    }
    if (param.isEmpty()==false){
        wrapper.and(q->q.eq("c.courseid",param).or().likeRight("c.cname",param));
    }
    // 设置分页信息, 查第3页, 每页2条数据
    Page<studentsgrades> page = new Page<>(pages, 15);
    // 执行分页查询
    Page<studentsgrades> userPage = page.setRecords(this.studentsGradesMapper.studentfindselfgrades(wrapper,page));
    // System.out.println("总页数 = " + userPage.getPages());
    int  pagesnum = (int) userPage.getPages();
    // 获取分页查询结果
    session.removeAttribute("pagesnum");
    session.setAttribute("pagesnum",pagesnum);
    List<studentsgrades> records = userPage.getRecords();
    return records;
}


}
