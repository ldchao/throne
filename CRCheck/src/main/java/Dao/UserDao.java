package Dao;

import POJO.User;

/**
 * Created by mm on 2016/7/10.
 */
public interface UserDao {

    //tip: the "id" ,"password" ,"userLogin" ,"userState" could not be null, if add succeed ,it will return true
    public boolean addUser(User po);

    public User findUser(User po);

    //tip: the "id" ,"password" ,"userLogin" ,"userState" could not be null,  if update succeed ,it will return true
    public boolean update(User po);

    //tip:  if delete succeed ,it will return true
    public boolean delete(User po);
}
