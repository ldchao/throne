package DaoTest;

import DaoImpl.CreateIdDaoImpl;
import DaoImpl.PersonalreviewDaoImpl;
import POJO.Personalreview;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import java.util.ArrayList;
import java.util.List;

/** 
* PersonalreviewDaoImpl Tester. 
* 
* @author <Authors name> 
* @since <pre>���� 21, 2016</pre> 
* @version 1.0 
*/ 
public class PersonalreviewDaoImplTest {
    PersonalreviewDaoImpl personalreviewDao;
//    CreateIdDaoImpl createIdDao;

@Before
public void before() throws Exception {
    personalreviewDao=new PersonalreviewDaoImpl();
//    createIdDao=new CreateIdDaoImpl();
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: addPersionalreview(Personalreview po) 
* 
*/ 
@Test
public void testAddPersionalreview() throws Exception { 
//TODO: Test goes here...
    Personalreview personalreview=new Personalreview();
    personalreview.setId(10);
    personalreview.setUserId("sure");
    personalreview.setProjectId(8);
    personalreview.setCommitTime("123");
    personalreview.setLocation("1");
    personalreview.setType("123");
    personalreview.setDescription("fei");
    personalreview.setState("Done");
    personalreview.setResult("Unapprove");
    personalreview.setFileType("sdfa");
    System.out.println(personalreviewDao.addPersionalreview(personalreview));
} 

/** 
* 
* Method: deletePersonalreview(Personalreview po) 
* 
*/ 
@Test
public void testDeletePersonalreview() throws Exception { 
//TODO: Test goes here...
    Personalreview personalreview=new Personalreview();
    personalreview.setId(10);
    System.out.println(personalreviewDao.deletePersonalreview(personalreview));
} 

/** 
* 
* Method: findValidPersonalReview(Personalreview po) 
* 
*/ 
@Test
public void testFindValidPersonalReview() throws Exception { 
//TODO: Test goes here...
    Personalreview personalreview=new Personalreview();
    personalreview.setProjectId(14);
    List list=personalreviewDao.findValidPersonalReview(personalreview);
    ArrayList<Personalreview> arrayList=(ArrayList<Personalreview>)list;
    for (Personalreview po:arrayList
         ) {
        System.out.println(po.getId());
    }
}

/** 
* 
* Method: findPersonalreviewById(Personalreview po) 
* 
*/ 
@Test
public void testFindPersonalreviewById() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: updateState(Personalreview po) 
* 
*/ 
@Test
public void testUpdateState() throws Exception {
//TODO: Test goes here...
    Personalreview personalreview=new Personalreview();
    personalreview.setId(10);
    personalreview.setState("shadiao");
    System.out.println(personalreviewDao.updateState(personalreview));

} 

/** 
* 
* Method: updateResult(Personalreview po) 
* 
*/ 
@Test
public void testUpdateResult() throws Exception { 
//TODO: Test goes here...
    Personalreview personalreview=new Personalreview();
    personalreview.setId(10);
    personalreview.setResult("Approve");
    System.out.println(personalreviewDao.updateResult(personalreview));

} 

/** 
* 
* Method: getUserPersonalreview(Personalreview po) 
* 
*/ 
@Test
public void testGetUserPersonalreview() throws Exception { 
//TODO: Test goes here...
    Personalreview personalreview=new Personalreview();
    personalreview.setUserId("chao");
    personalreview.setProjectId(14);
    List list=personalreviewDao.getUserPersonalreview(personalreview);
    ArrayList<Personalreview> arrayList=(ArrayList<Personalreview>)list;
    for (Personalreview po:arrayList
            ) {
        System.out.println(po.getId());
    }
} 

/** 
* 
* Method: getUserFoundDefect(Personalreview po) 
* 
*/ 
@Test
public void testGetUserFoundDefect() throws Exception { 
//TODO: Test goes here...
    Personalreview personalreview=new Personalreview();
    personalreview.setUserId("marioquer");
    personalreview.setProjectId(14);
    System.out.println(personalreviewDao.getUserFoundDefect(personalreview));
} 

/** 
* 
* Method: getDefectOfResult(Personalreview po) 
* 
*/ 
@Test
public void testGetDefectOfResult() throws Exception { 
//TODO: Test goes here...
    Personalreview personalreview=new Personalreview();
    personalreview.setUserId("chao");
    personalreview.setProjectId(14);
    personalreview.setResult("Unapprove");
    System.out.println(personalreviewDao.getDefectOfResult(personalreview));
} 


} 
