package DaoTest;

import Dao.AchievementDao;
import DaoImpl.AchievementDaoImpl;
import POJO.Achievement;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import java.util.ArrayList;

/** 
* AchievementDaoImpl Tester. 
* 
* @author <Authors name> 
* @since <pre>���� 28, 2016</pre> 
* @version 1.0 
*/ 
public class AchievementDaoImplTest {
    AchievementDaoImpl achievementDao;

@Before
public void before() throws Exception {
    achievementDao=new AchievementDaoImpl();
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: getSatisfactoryAchievement(Achievement po, double value, String userId) 
* 
*/ 
@Test
public void testGetSatisfactoryAchievement() throws Exception { 
//TODO: Test goes here...
    Achievement achievement=new Achievement();
    achievement.setType("shabichao");
    achievement.setValue(122.0);
    ArrayList<Achievement> arrayList=(ArrayList<Achievement>) achievementDao.getSatisfactoryAchievement(achievement,"chao");
    for (Achievement po:arrayList){
        System.out.println(po.getValue());
    }
}

/** 
* 
* Method: findAchievementById(Achievement po) 
* 
*/ 
@Test
public void testFindAchievementById() throws Exception { 
//TODO: Test goes here...
    Achievement achievement=new Achievement();
    achievement.setId(2);
    System.out.println(achievementDao.findAchievementById(achievement));
} 


} 
