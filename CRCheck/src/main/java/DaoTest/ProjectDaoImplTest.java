package DaoTest;

import DaoImpl.ProjectDaoImpl;
import POJO.Project;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import java.util.ArrayList;

/** 
* ProjectDaoImpl Tester. 
* 
* @author <Authors name> 
* @since <pre>���� 28, 2016</pre> 
* @version 1.0 
*/ 
public class ProjectDaoImplTest {
    ProjectDaoImpl projectDao;

@Before
public void before() throws Exception {
    projectDao=new ProjectDaoImpl();
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: addProject(Project po) 
* 
*/ 
@Test
public void testAddProject() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: deleteProject(Project po) 
* 
*/ 
@Test
public void testDeleteProject() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: updateProject(Project po) 
* 
*/ 
@Test
public void testUpdateProject() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: findProjectByUserId(User po) 
* 
*/ 
@Test
public void testFindProjectByUserId() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: findProject(Project po) 
* 
*/ 
@Test
public void testFindProject() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getSimilarProject(String name) 
* 
*/ 
@Test
public void testGetSimilarProject() throws Exception { 
//TODO: Test goes here...
    ArrayList<Project> arrayList=(ArrayList<Project>) projectDao.getSimilarProject("2");
    for (Project po:arrayList
            ) {
        System.out.println(po.getName());
    }
} 


} 
