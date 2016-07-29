package controller;
import model.PersonalQualityModel;
import model.ProjectModel;
import model.ProjectQualityModel;
import model.ScatterDiagramModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import service.ProjectFeedBackService;
import service.ProjectService;
import serviceImpl.ProjectFeedBackServiceImpl;
import serviceImpl.ProjectServiceImpl;
import tool.DateHelper;

import java.util.ArrayList;

/**
 * Created by lvdechao on 2016/7/22.
 */

@Controller
public class ProjectFeedBackController {
    //进入质量反馈界面
    @RequestMapping(value = "/pages/feedBack")
    public ModelAndView feedBack(int projectId){
        ModelAndView model=new ModelAndView("FeedbackPage");
        //查找项目
        ProjectService ps = new ProjectServiceImpl();
        ProjectModel project = ps.checkProject(projectId);
        //项目不存在
        if (project == null) {
            return null;
        }
        //计算项目剩余时间
        String day = DateHelper.daysAnalyse(project.getStartDate(), project.getEndDate());
        model.addObject("project", project);
        model.addObject("day", day);
        return model;
    }

    @RequestMapping(value = "/scatterDiagram", method = RequestMethod.POST)
    @ResponseBody
    public ScatterDiagramModel getScatterDiagramData(int projectId) {
        ProjectFeedBackService projectFeedBackService=new ProjectFeedBackServiceImpl();
         ScatterDiagramModel scatterDiagramModel=projectFeedBackService.getScatterDiagramData(projectId);
        return scatterDiagramModel;
    }

    @RequestMapping(value = "/statisticsChart", method = RequestMethod.POST)
    @ResponseBody
    public ArrayList<PersonalQualityModel> getStatisticsChartData(int projectId) {
        ProjectFeedBackService projectFeedBackService=new ProjectFeedBackServiceImpl();
        ArrayList<PersonalQualityModel> result= projectFeedBackService.getStatisticChartData(projectId);
        return result;
    }

    @RequestMapping(value = "/lineChart", method = RequestMethod.POST)
    @ResponseBody
    public ArrayList<ProjectQualityModel> getLineChartData(int projectId) {
        ProjectFeedBackService projectFeedBackService=new ProjectFeedBackServiceImpl();
        ArrayList<ProjectQualityModel> result=projectFeedBackService.getLineChartData(projectId);
        return result;
    }



}
