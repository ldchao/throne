package DaoTest;

import DaoImpl.SummaryDaoImpl;
import POJO.Summary;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import java.util.ArrayList;

/** 
* SummaryDaoImpl Tester. 
* 
* @author <Authors name> 
* @since <pre>���� 21, 2016</pre> 
* @version 1.0 
*/ 
public class SummaryDaoImplTest { 
SummaryDaoImpl summaryDao;
@Before
public void before() throws Exception { 
    summaryDao=new SummaryDaoImpl();
}

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: addSummary(Summary po) 
* 
*/ 
@Test
public void testAddSummary() throws Exception { 
//TODO: Test goes here...
    Summary summary=new Summary();
    summary.setId(3);
    summary.setProjectId(14);
    summary.setNewPersonalReviewId(12);
    summary.setOldPersonalReviewId(6);
    System.out.println(summaryDao.addSummary(summary));
} 

/** 
* 
* Method: findSummaryById(Summary po) 
* 
*/ 
@Test
public void testFindSummaryById() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getMergedSummary(Summary po) 
* 
*/ 
@Test
public void testGetMergedSummary() throws Exception { 
//TODO: Test goes here...
    Summary summary=new Summary();
    summary.setNewPersonalReviewId(12);
    ArrayList<Summary> arrayList=(ArrayList<Summary>)summaryDao.getMergedSummary(summary);
    for (Summary po:arrayList
         ) {
        System.out.println(po.getId());
    }
} 

/** 
* 
* Method: deleteNewPersonalReviewId(Summary po) 
* 
*/ 
@Test
public void testDeleteNewPersonalReviewId() throws Exception { 
//TODO: Test goes here...
    Summary summary=new Summary();
    summary.setNewPersonalReviewId(12);
    System.out.println(summaryDao.deleteNewPersonalReviewId(summary));
} 

/** 
* 
* Method: deleteSummary(Summary po) 
* 
*/ 
@Test
public void testDeleteSummary() throws Exception { 
//TODO: Test goes here...
    Summary summary=new Summary();
    summary.setNewPersonalReviewId(10);
    summary.setOldPersonalReviewId(4);
    System.out.println(summaryDao.deleteSummary(summary));
} 


} 
