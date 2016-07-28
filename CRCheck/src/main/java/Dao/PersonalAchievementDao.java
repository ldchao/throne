package Dao;

import POJO.PersonalAchievement;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by mm on 2016/7/28.
 */
public interface PersonalAchievementDao {
    //need the "userId",it will return an arrayList of "achievementId" which are ordered by "achievementId"
    public ArrayList<Integer> getAchievements(PersonalAchievement po);

    //need all the attributes
    public boolean addPersonalAchievement(PersonalAchievement po);
}
