package com.yc.biz;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yc.bean.cost;
import com.yc.mapper.CostMapper;
import com.yc.vo.examinationgrades;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.List;
@Service
@Transactional
public class CostBizImpl implements CostBiz{
    @Autowired
    CostMapper costMapper;
    @Override
    public List<cost> plusteacherfindcost(String college, String classes, String semester, int pages, String param, HttpSession session) {
        QueryWrapper<cost> wrapper = new QueryWrapper<>();
        wrapper.eq("b.college",college);
        if (classes.isEmpty()==false){
            wrapper.eq("a.classes",classes);
        }
        if (semester.isEmpty()==false) {
            wrapper.eq("a.semester",semester);
        }
        if (param.isEmpty()==false){
            wrapper.and(q->q.likeRight("a.classes",classes));
        }
        // 设置分页信息, 查第3页, 每页2条数据
        Page<cost> page = new Page<>(pages, 15);
        // 执行分页查询
        Page<cost> userPage = page.setRecords(this.costMapper.plusteacherfindcost(wrapper,page));
        // System.out.println("总页数 = " + userPage.getPages());
        int  pagesnum = (int) userPage.getPages();
        // 获取分页查询结果
        session.removeAttribute("pagesnum");
        session.setAttribute("pagesnum",pagesnum);
        List<cost> records = userPage.getRecords();
        return records;
    }

    @Override
    public List<String> plusteacherfindcostclasses(String college) {
        return costMapper.plusteacherfindcostclasses(college);
    }

    @Override
    public List<String> plusteacherfindcostsemester(String college) {
        return costMapper.plusteacherfindcostsemester(college);
    }

    @Override
    public void plusinsertcost(cost cost) {
        costMapper.insert(cost);
    }

    @Override
    public void plusdeletecost(String[] classes,String[] semester) {
        for (int i=0;i<classes.length;i++){
            costMapper.plusteacherdeletecost(classes[i],semester[i]);
        }
    }

    @Override
    public void pluseditcost(cost cost) {
        costMapper.plusteacherupdatacost(cost.getAllcost(),cost.getBookcost(),cost.getSurpluscost(),cost.getClasses(),cost.getSemester());

    }
}
