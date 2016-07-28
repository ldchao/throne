package DaoTest;

import DaoImpl.FileDaoImpl;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

/** 
* FileDaoImpl Tester. 
* 
* @author <Authors name> 
* @since <pre>���� 26, 2016</pre> 
* @version 1.0 
*/ 
public class FileDaoImplTest { 
FileDaoImpl fileDao;
@Before
public void before() throws Exception {
    fileDao=new FileDaoImpl();
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: getFileState(File po) 
* 
*/ 
@Test
public void testGetFileState() throws Exception { 
//TODO: Test goes here...
    File file=new File();
    file.setPath("C://ab/a.txt");
    System.out.println(fileDao.getFileState(file));
} 

/** 
* 
* Method: getFileLastTime(File po) 
* 
*/ 
@Test
public void testGetFileLastTime() throws Exception { 
//TODO: Test goes here...
    File file=new File();
    file.setPath("33");
    System.out.println(fileDao.getFileLastTime(file));
} 

/** 
* 
* Method: findFileById(File po) 
* 
*/ 
@Test
public void testFindFileById() throws Exception { 
//TODO: Test goes here... 
} 


    @Test
    public void testChangeFileState() throws Exception{
        File file=new File();
        file.setPath("33");
        file.setState("false");
        System.out.println(fileDao.changeFileState(file));
    }

} 
