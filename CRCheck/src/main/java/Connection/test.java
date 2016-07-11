package Connection;

import DaoImpl.UserDaoImpl;
import POJO.User;
import org.hibernate.Session;

/**
 * Created by mm on 2016/7/11.
 */
public class test {
    public static void  main(String[]args){
        UserDaoImpl userDao=new UserDaoImpl();
        User user=new User();
        user.setId("fatchao");
        user.setPassword("123456");
        user.setUserLogin("online");
        user.setUserState("public");
        user.setChecklistPath("src");
        userDao.addUser(user);
    }
}
