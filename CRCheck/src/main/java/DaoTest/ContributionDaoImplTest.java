package DaoTest;

import DaoImpl.ContributionDaoImpl;
import POJO.Contribution;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import java.util.ArrayList;
import java.util.List;

/** 
* ContributionDaoImpl Tester. 
* 
* @author <Authors name> 
* @since <pre>���� 28, 2016</pre> 
* @version 1.0 
*/ 
public class ContributionDaoImplTest { 
ContributionDaoImpl contributionDao;
@Before
public void before() throws Exception {
    contributionDao=new ContributionDaoImpl();
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: addContribution(Contribution po) 
* 
*/ 
@Test
public void testAddContribution() throws Exception { 
//TODO: Test goes here...
    Contribution contribution=new Contribution();
    contribution.setId(5);
    contribution.setUserId("sure");
    contribution.setProjectId(30);
    contribution.setRow(1.0);
    contribution.setTime(2.0);
    contribution.setAmount(3.0);
    contribution.setAccuracy(0.512);
    contribution.setCoverage(0.8);
    System.out.println(contributionDao.addContribution(contribution));
} 

/** 
* 
* Method: getSumOfRow(Contribution po) 
* 
*/ 
@Test
public void testGetSumOfRow() throws Exception { 
//TODO: Test goes here...
    Contribution contribution=new Contribution();
    contribution.setUserId("sure");
    System.out.println(contributionDao.getSumOfRow(contribution));
} 

/** 
* 
* Method: getSumOfTime(Contribution po) 
* 
*/ 
@Test
public void testGetSumOfTime() throws Exception {
    Contribution contribution=new Contribution();
    contribution.setUserId("sure");
    System.out.println(contributionDao.getSumOfTime(contribution));
//TODO: Test goes here... 
} 

/** 
* 
* Method: getSumOfAmount(Contribution po) 
* 
*/ 
@Test
public void testGetSumOfAmount() throws Exception { 
//TODO: Test goes here...
    Contribution contribution=new Contribution();
    contribution.setUserId("sure");
    System.out.println(contributionDao.getSumOfAmount(contribution));
} 

/** 
* 
* Method: getAverageOfAccuracy(Contribution po) 
* 
*/ 
@Test
public void testGetAverageOfAccuracy() throws Exception { 
//TODO: Test goes here...
    Contribution contribution=new Contribution();
    contribution.setUserId("sure");
    System.out.println(contributionDao.getAverageOfAccuracy(contribution));
} 

/** 
* 
* Method: getAverageOfCoverage(Contribution po) 
* 
*/ 
@Test
public void testGetAverageOfCoverage() throws Exception { 
//TODO: Test goes here...
    Contribution contribution=new Contribution();
    contribution.setUserId("sure");
    System.out.println(contributionDao.getAverageOfCoverage(contribution));
} 

/** 
* 
* Method: getContributionsByUserId(Contribution po) 
* 
*/ 
@Test
public void testGetContributionsByUserId() throws Exception { 
//TODO: Test goes here...
    Contribution contribution=new Contribution();
    contribution.setUserId("marioquer");
    ArrayList<Contribution> list=(ArrayList<Contribution>)contributionDao.getContributionsByUserId(contribution);
    for (Contribution po:list
         ) {
        System.out.println(po.getId());
    }

} 

/** 
* 
* Method: findContributionById(Contribution po) 
* 
*/ 
@Test
public void testFindContributionById() throws Exception { 
//TODO: Test goes here... 
} 


} 
