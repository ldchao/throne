package controller;

import model.AchievementModel;
import model.ContributionModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.ContributionService;
import serviceImpl.ContributionServiceImpl;
import java.util.ArrayList;

/**
 * Created by zs on 2016/7/28.
 */
@Controller
public class ContributionController {
    //根据用户查看用户成就累计情况
    @RequestMapping(value = "/checkContributionList")
    @ResponseBody
    public ArrayList<ContributionModel> checkContributionList(String userID){
        ContributionService con=new ContributionServiceImpl();
        ArrayList<ContributionModel> list=con.checkContributionList(userID);
        return list;
    }

    //根据用户查看用户成就统计情况
    @RequestMapping(value = "/checkContributionSum")
    @ResponseBody
    public ContributionModel checkContributionSum(String userID){
        ContributionService con=new ContributionServiceImpl();
        ContributionModel model=con.checkContributionSum(userID);
        return model;
    }

    //根据用户查看用户达到的成就点
    @RequestMapping(value = "/checkAchievement")
    @ResponseBody
    public ArrayList<AchievementModel> checkAchievement(String userID){
        ContributionService con=new ContributionServiceImpl();
        ArrayList<AchievementModel> list=con.checkAchievement(userID);
        return list;
    }
}
