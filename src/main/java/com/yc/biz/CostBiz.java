package com.yc.biz;

import com.yc.bean.cost;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface CostBiz {
    public List<cost> plusteacherfindcost(String college, String classes, String semester, int pages, String param, HttpSession session);
    public List<String> plusteacherfindcostclasses(String college);
    public List<String> plusteacherfindcostsemester(String college);
    public void plusinsertcost(cost cost);
    public void pluseditcost(cost cost);
    public void plusdeletecost(String[] classes,String[] semester);

}
