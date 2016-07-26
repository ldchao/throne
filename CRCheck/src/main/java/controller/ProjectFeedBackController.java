package controller;
import model.PersonalQualityModel;
import model.ProjectQualityModel;
import model.ScatterDiagramModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.ProjectFeedBackService;
import serviceImpl.ProjectFeedBackServiceImpl;

import java.util.ArrayList;

/**
 * Created by lvdechao on 2016/7/22.
 */

@Controller
public class ProjectFeedBackController {

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
