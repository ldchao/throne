package controller;

import enums.UniversalState;
import model.PersonalReviewRecord;
import model.SummaryReviewRecord;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.ReviewRecordService;
import serviceImpl.ReviewRecordServiceImpl;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zs on 2016/7/14.
 */
@Controller
public class ReviewController {
    //添加评审
    @RequestMapping(value = "/addReview", method = RequestMethod.POST)
    @ResponseBody
    public String addReview(ArrayList<PersonalReviewRecord> records) {
        ReviewRecordService review=new ReviewRecordServiceImpl();
        UniversalState state=review.submitReviewRecord(records);
        if(state==UniversalState.SUCCESS)
            return "SUCCESS";
        return "FAIL";
    }
    
    //结束项目评审
    @RequestMapping(value = "/endReview", method = RequestMethod.POST)
    @ResponseBody
    public String endReview(String userID, int projectID) {
        ReviewRecordService review=new ReviewRecordServiceImpl();
        UniversalState state=review.finishReviewRecord(userID, projectID);
        if(state==UniversalState.SUCCESS)
            return "SUCCESS";
        return "FAIL";
    }

    //查看个人评审记录
    @RequestMapping(value = "/getPersonReviewList", method = RequestMethod.POST)
    @ResponseBody
    public List<PersonalReviewRecord> getPersonReviewList(String userID, int projectID){
        ReviewRecordService review=new ReviewRecordServiceImpl();
        List<PersonalReviewRecord> list=review.checkPersonalReviewRecord(userID,projectID);
        return list;
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
    @RequestMapping(value = "/getFinishUserList", method = RequestMethod.POST)
    @ResponseBody
    public List<SummaryReviewRecord> checkSummaryReview(int projectID){
        ReviewRecordService review=new ReviewRecordServiceImpl();
        List<SummaryReviewRecord> list=review.checkSummaryReviewRecord(projectID);
        return list;
    }
}
