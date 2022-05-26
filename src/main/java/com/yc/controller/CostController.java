package com.yc.controller;

import com.yc.bean.JsonModel;
import com.yc.bean.cost;
import com.yc.bean.teacher;
import com.yc.biz.CostBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.swing.*;
import java.util.List;

@RestController
public class CostController {
    @Autowired
    CostBiz costBiz;
    @RequestMapping("/plusteacherfindcost")
    public JsonModel plusteacherfindcost(String classes, String semester, String param, int pages, HttpSession session){
        JsonModel jm = new JsonModel();
        teacher th = (teacher)session.getAttribute("loginadmin");
        try{
            List<cost> list = costBiz.plusteacherfindcost(th.getCollege(),classes,semester,pages,param,session);
            if (list.isEmpty()==false){
                jm.setCode((Integer) session.getAttribute("pagesnum"));
                jm.setData(list);
            }else {
                jm.setCode(0);
                jm.setMsg("查无此消息");
            }
            return jm;
        }catch (Exception ex){
            jm.setCode(0);
            jm.setMsg(ex.getMessage());
            return jm;
        }
    }
    @RequestMapping("/plusteacherfindcostclasses")
    public JsonModel plusteacherfindcostclasses(HttpSession session){
        JsonModel jm = new JsonModel();
        teacher th = (teacher)session.getAttribute("loginadmin");
        try{
            List<String> list = costBiz.plusteacherfindcostclasses(th.getCollege());
            jm.setCode(1);
            jm.setData(list);
        }catch (Exception ex){
            jm.setCode(0);
        }
        return jm;
    }
    @RequestMapping("/plusteacherfindcostsemester")
    public JsonModel plusteacherfindcostsemester(HttpSession session){
        JsonModel jm = new JsonModel();
        teacher th = (teacher)session.getAttribute("loginadmin");
        try{
            List<String> list = costBiz.plusteacherfindcostsemester(th.getCollege());
            jm.setCode(1);
            jm.setData(list);
        }catch (Exception ex){
            jm.setCode(0);
        }
        return jm;
    }
    @RequestMapping("/pluseditcost")
    public JsonModel pluseditcost(String classes,String semester,Float allcost,Float bookcost,Float surpluscost){
        cost cost = new cost();
        cost.setAllcost(allcost);
        cost.setClasses(classes);
        cost.setSemester(semester);
        cost.setBookcost(bookcost);
        cost.setSurpluscost(surpluscost);
        JsonModel jm = new JsonModel();
        try{
            costBiz.pluseditcost(cost);
            jm.setCode(1);
        }catch (Exception ex){
            jm.setCode(0);
            jm.setMsg(ex.getMessage());
        }
        return jm;
    }
    @RequestMapping("/plusdeletecost")
    public JsonModel plusdeletecost(String[] classes,String[] semester){
        JsonModel jm = new JsonModel();
        try{
            costBiz.plusdeletecost(classes,semester);
            jm.setCode(1);
        }catch (Exception ex){
            jm.setCode(0);
            jm.setMsg(ex.getMessage());
        }
        return jm;
    }
    @RequestMapping("/plusinsertcost")
    public JsonModel plusinsertcost(String classes,String semester,Float allcost,Float bookcost,Float surpluscost){
        cost cost = new cost();
        cost.setAllcost(allcost);
        cost.setClasses(classes);
        cost.setSemester(semester);
        cost.setBookcost(bookcost);
        cost.setSurpluscost(surpluscost);
        JsonModel jm = new JsonModel();
        try{
            costBiz.plusinsertcost(cost);
            jm.setCode(1);
        }catch (Exception ex){
            jm.setCode(0);
            jm.setMsg("不能重复插入数据或数据格式不正确");
        }
        return jm;
    }

}
