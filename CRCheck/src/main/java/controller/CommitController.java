package controller;

import Dao.AttendanceDao;
import enums.UniversalState;
import model.CommitRecordModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.AttendanceService;
import service.CommitService;
import serviceImpl.AttendanceServiceImpl;
import serviceImpl.CommitServiceImpl;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by lvdechao on 2016/7/26.
 */
@Controller
public class CommitController {

    //提交每次评审的行数，用时等
    @RequestMapping(value = "/commit")
    @ResponseBody
    public String commit(HttpServletRequest request){
        //获得信息
        String userId = request.getParameter("userId");
        int projectId = Integer.parseInt(request.getParameter("projectId"));
        int codeLine = Integer.parseInt(request.getParameter("codeLine"));
        String reviewType = request.getParameter("reviewType");
        int time = Integer.parseInt(request.getParameter("time"));
        //转换信息
        CommitRecordModel model = new CommitRecordModel();
        model.setUserId(userId);
        model.setProjectId(projectId);
        model.setCodeLine(codeLine);
        model.setReviewType(reviewType);
        model.setTime(time);
        //调用接口
        CommitService service=new CommitServiceImpl();
        UniversalState state=service.commit(model);
        if(state==UniversalState.SUCCESS)
            return "SUCCESS";
        return "FAIL";
    }

    @RequestMapping(value = "/checkReviewState", method = RequestMethod.POST)
    @ResponseBody
    public String checkReviewState(String userID,int projectID) {
        AttendanceService attendanceService=new AttendanceServiceImpl();
        return attendanceService.getState(userID,projectID);
    }

}
