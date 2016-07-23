package controller;

import enums.UniversalState;
import model.PersonalReviewRecord;
import model.ProjectModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import service.ProjectService;
import service.ReviewRecordService;
import serviceImpl.ProjectServiceImpl;
import serviceImpl.ReviewRecordServiceImpl;

import java.util.List;

/**
 * Created by zs on 2016/7/23.
 */
@Controller
public class DealController {
    //进入发起者查看项目结果界面
    @RequestMapping(value = "/projects/result", method = RequestMethod.GET)
    public ModelAndView getProjectResult(String userId,int projectId) {
        //查找项目
        ProjectService ps = new ProjectServiceImpl();

        ProjectModel project = ps.checkProject(projectId);
        //项目不存在
        if (project == null) {
            return null;
        }
        //安全检查
        if(!project.getUserID().equals(userId))
            return null;

        ModelAndView modelAndView=new ModelAndView("LauncherPage");
        modelAndView.addObject("project", project);
        return modelAndView;
    }

    //查看参评所有完成评审用户名单
    @RequestMapping(value = "/getFinishUserList", method = RequestMethod.POST)
    @ResponseBody
    public List<String> getFinishUserList(int projectID){
        ReviewRecordService review=new ReviewRecordServiceImpl();
        List<String> list=review.checkProjectUserList(projectID);
        return list;
    }

    //查看评审记录
    @RequestMapping(value = "/getSummaryReview", method = RequestMethod.POST)
    @ResponseBody
    public List<PersonalReviewRecord> checkSummaryReview(int projectID){
        ReviewRecordService review=new ReviewRecordServiceImpl();
        List<PersonalReviewRecord> list=review.checkSummaryReviewRecord(projectID);
        return list;
    }

    //查看单条合并记录
    @RequestMapping(value = "/getChildReview", method = RequestMethod.POST)
    @ResponseBody
    public List<PersonalReviewRecord> getChildReview(int reviewId){
        ReviewRecordService review=new ReviewRecordServiceImpl();
        List<PersonalReviewRecord> list=review.getChildReviewRecord(reviewId);
        return list;
    }

    //结束此次项目-发布者
    @RequestMapping(value = "/confirmReview", method = RequestMethod.POST)
    @ResponseBody
    public String confirmReview(int projectID){
        ReviewRecordService review=new ReviewRecordServiceImpl();
        UniversalState state =review.confirmReviewRecord(projectID);
        if(state==UniversalState.SUCCESS)
            return "SUCCESS";
        return "FAIL";
    }
}
