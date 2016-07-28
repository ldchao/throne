package Dao;

import POJO.Achievement;
import POJO.User;

import java.util.List;

/**
 * Created by mm on 2016/7/28.
 */
public interface AchievementDao {
    //need the "userId" , "type" and "value" ,it will return a list of "Achievement" whose value is satisfactory and the "User" doesn't have
    public List getSatisfactoryAchievement(Achievement po, String userId);

    //need the "achievementId", it will return an "Achievement"
    public Achievement findAchievementById(Achievement po);
}
