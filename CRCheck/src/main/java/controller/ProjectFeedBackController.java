package controller;
import model.ScatterDiagramModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.ProjectFeedBackService;
import serviceImpl.ProjectFeedBackServiceImpl;

/**
 * Created by lvdechao on 2016/7/22.
 */

@Controller
public class ProjectFeedBackController {

    @RequestMapping(value = "/scatterDiagram", method = RequestMethod.POST)
    @ResponseBody
    public ScatterDiagramModel addReview(int projectId) {
        ProjectFeedBackService projectFeedBackService=new ProjectFeedBackServiceImpl();
         ScatterDiagramModel scatterDiagramModel=projectFeedBackService.getScatterDiagramData(projectId);
        return scatterDiagramModel;
    }

}
