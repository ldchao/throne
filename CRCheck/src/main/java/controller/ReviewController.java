package controller;

import enums.UniversalState;
import model.PersonalReviewRecord;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.ReviewRecordService;
import serviceImpl.ReviewRecordServiceImpl;
import tool.RecordTransfer;

import javax.servlet.http.HttpServletRequest;
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
    public String addReview(HttpServletRequest request) {
        String userId=request.getParameter("userId");
        int projectId=Integer.parseInt(request.getParameter("projectId"));
        String[] records=request.getParameterValues("records[]");
        ReviewRecordService review=new ReviewRecordServiceImpl();
        ArrayList<PersonalReviewRecord> list=new ArrayList<PersonalReviewRecord>();
        for(String record:records){
            String[] strs=record.split("[&]");
            PersonalReviewRecord r= RecordTransfer.change(strs);
            r.setProjectId(projectId);
            r.setUserId(userId);
            list.add(r);
        }
        UniversalState state=review.submitReviewRecord(list);
        if(state==UniversalState.SUCCESS)
            return "SUCCESS";
        return "FAIL";
    }

    //结束个人项目评审
    @RequestMapping(value = "/endReview", method = RequestMethod.POST)
    @ResponseBody
    public String endReview(String userId, int projectID) {
        ReviewRecordService review=new ReviewRecordServiceImpl();
        UniversalState state=review.finishReviewRecord(userId, projectID);
        if(state==UniversalState.SUCCESS)
            return "SUCCESS";
        return "FAIL";
    }

    //查看个人评审记录
    @RequestMapping(value = "/getPersonReviewList", method = RequestMethod.POST)
    @ResponseBody
    public List<PersonalReviewRecord> getPersonReviewList(String userId, int projectID){
        ReviewRecordService review=new ReviewRecordServiceImpl();
        List<PersonalReviewRecord> list=review.checkPersonalReviewRecord(userId,projectID);
        return list;
    }
}
