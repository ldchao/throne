package DaoTest;

import Dao.PersonalAchievementDao;
import DaoImpl.PersonalAchievementDaoImpl;
import POJO.PersonalAchievement;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import java.util.ArrayList;

/** 
* PersonalAchievementDaoImpl Tester. 
* 
* @author <Authors name> 
* @since <pre>���� 28, 2016</pre> 
* @version 1.0 
*/ 
public class PersonalAchievementDaoImplTest {
    PersonalAchievementDaoImpl personalAchievementDao;

@Before
public void before() throws Exception {
    personalAchievementDao=new PersonalAchievementDaoImpl();
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: getAchievements(PersonalAchievement po) 
* 
*/ 
@Test
public void testGetAchievements() throws Exception { 
//TODO: Test goes here..
    PersonalAchievement personalAchievement=new PersonalAchievement();
    personalAchievement.setUserId("sure");
    ArrayList<Integer> arrayList=personalAchievementDao.getAchievements(personalAchievement);
    for(int temp:arrayList){
        System.out.println(temp);
    }
} 

/** 
* 
* Method: addPersonalAchievement(PersonalAchievement po) 
* 
*/ 
@Test
public void testAddPersonalAchievement() throws Exception { 
//TODO: Test goes here...
    PersonalAchievement po=new PersonalAchievement();
    po.setId(3);
    po.setUserId("chao");
    po.setAchievementId(2);
    System.out.println(personalAchievementDao.addPersonalAchievement(po));
} 

/** 
* 
* Method: findPersonalAchievementById(PersonalAchievement po) 
* 
*/ 
@Test
public void testFindPersonalAchievementById() throws Exception { 
//TODO: Test goes here... 
} 


} 
