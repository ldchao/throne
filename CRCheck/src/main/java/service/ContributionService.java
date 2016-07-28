package service;

import enums.UniversalState;
import model.AchievementModel;
import model.ContributionModel;

import java.util.ArrayList;

/**
 * Created by lvdechao on 2016/7/28.
 */
public interface ContributionService {

    //评审者完成项目评审，系统将评审人员的提交情况添加到成就表
    public UniversalState addContribution(int projectID);

    //根据用户查看用户成就累计情况
    public ArrayList<ContributionModel> checkContributionList(String userID);

    //根据用户查看用户成就统计情况
    public ContributionModel checkContributionSum(String userID);

    //根据用户查看用户达到的成就点
    public ArrayList<AchievementModel> checkAchievement(String userID);



}
