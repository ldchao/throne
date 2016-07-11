package Connection;

import DaoImpl.SummaryDaoImpl;
import DaoImpl.UserDaoImpl;
import POJO.Summary;
import POJO.User;

/**
 * Created by mm on 2016/7/11.
 */
public class test {
    public static void  main(String[]args){
        UserDaoImpl userDao=new UserDaoImpl();
        User user=new User();
        user.setId("xichao");
        user.setPassword("123");
        user.setAddress("hello");
        user.setUserLogin("online");
        user.setUserState("private");
        user.setChecklistPath("src");
        userDao.addUser(user);


//        SummaryDaoImpl summaryDao=new SummaryDaoImpl();
//        Summary summary=new Summary();
//        summary.setId(123);
//        summary.setCombination("jdjaj");
//        summary.setDescription("222");
//        summary.setLocation("fatchao");
//        summary.setProjectId("123");
//        summary.setType("shuai");
//        summaryDao.delete(123);


    }
}
