package Dao;

import POJO.User;

import java.util.List;

/**
 * Created by mm on 2016/7/10.
 */
public interface UserDao {

    //the "id" ,"password" ,"userLogin" ,"userState" could not be null, if add succeed ,it will return true
    public boolean addUser(User po);

    //just need the "id" of "User",return "User"
    public User findUser(User po);

    //the "id" ,"password" ,"userLogin" ,"userState" could not be null,  if update succeed ,it will return true,update a "User" one time
    public boolean update(User po);

    //just need the "id" of "User",if delete succeed ,it will return true
    public boolean delete(User po);

    //return "User"
    public List getAllUserId();
}
