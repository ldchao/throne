package controller;

import Dao.AttendanceDao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.AttendanceService;
import serviceImpl.AttendanceServiceImpl;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by lvdechao on 2016/7/26.
 */
@Controller
public class CommitController {

    @RequestMapping(value = "/checkReviewState", method = RequestMethod.POST)
    @ResponseBody
    public String checkReviewState(String userID,int projectID) {
        AttendanceService attendanceService=new AttendanceServiceImpl();
        return attendanceService.getState(userID,projectID);
    }

}
