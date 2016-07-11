package Connection;

import DaoImpl.UserDaoImpl;
import POJO.User;

/**
 * Created by L.H.S on 16/7/11.
 */
public class TEST123 {
    public static void main(String [] args){
        connection connection=new connection();
        UserDaoImpl userDao=new UserDaoImpl();
        User user=new User();
        user.setId("dddd");
        user.setPassword("2222");
        user.setUserLogin("sdff");
        user.setUserState("ddff");
        user.setChecklistPath("ccccc");
        System.out.print(userDao.addUser(user));
    }
}
