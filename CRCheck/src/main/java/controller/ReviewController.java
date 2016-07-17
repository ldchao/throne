package controller;

import enums.UniversalState;
import model.PersonalReviewRecord;
import model.SummaryReviewRecord;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.ReviewRecordService;
import serviceImpl.ReviewRecordServiceImpl;

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
        String[] records=request.getParameterValues("records");
        ReviewRecordService review=new ReviewRecordServiceImpl();
        ArrayList<PersonalReviewRecord> list=new ArrayList<PersonalReviewRecord>();
        System.out.println(records.length);
        for(String record:records){
            String[] strs=record.split("[&]");
            PersonalReviewRecord r=new PersonalReviewRecord();
            r.setId(projectId);
            r.setUserId(userId);
            r.setPath(strs[0]);
            r.setLineNum(strs[1]);
            r.setType(strs[2]);
            r.setDescription(strs[3]);
            list.add(r);
        }
        UniversalState state=review.submitReviewRecord(list);
        if(state==UniversalState.SUCCESS)
            return "SUCCESS";
        return "FAIL";
    }
    
    //结束项目评审
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
    public List<SummaryReviewRecord> checkSummaryReview(int projectID){
        ReviewRecordService review=new ReviewRecordServiceImpl();
        List<SummaryReviewRecord> list=review.checkSummaryReviewRecord(projectID);
        return list;
    }
}
