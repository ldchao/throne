package Dao;

import POJO.User;

/**
 * Created by mm on 2016/7/10.
 */
public interface UserDao {

    //tip: the "id" ,"password" ,"userLogin" ,"userState" could not be null
    public boolean addUser(User user);

    public User findUser(String id);

    //tip: the "id" ,"password" ,"userLogin" ,"userState" could not be null
    public boolean update(User user);

    public boolean delete(String id);
}
