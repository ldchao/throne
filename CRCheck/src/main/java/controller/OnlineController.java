package controller;

import model.PersonalReviewRecord;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.OnlineReviewRecordService;
import serviceImpl.OnlineReviewRecordServiceImpl;

import java.util.ArrayList;

/**
 * Created by zs on 2016/7/28.
 */
@Controller
public class OnlineController {
    //根据projectID，userID，path(项目id后的路径）--取得当前用户当前项目在当前路径文件的个人缺陷列表
    @RequestMapping(value = "/getOnlineUserRecordList")
    @ResponseBody
    public ArrayList<PersonalReviewRecord> getUserRecordList(int projectID, String userID, String path){
        OnlineReviewRecordService service=new OnlineReviewRecordServiceImpl();
        return service.getUserRecordList(projectID,userID,path);
    }

    //根据projectID，path取得当前项目在当前路径文件的汇总缺陷列表
    @RequestMapping(value = "/getOnlineProjectRecordList")
    @ResponseBody
    public ArrayList<PersonalReviewRecord> getOnlineProjectRecordList(int projectID,String path){
        OnlineReviewRecordService service=new OnlineReviewRecordServiceImpl();
        return service.getProjectRecordList(projectID,path);
    }
}
