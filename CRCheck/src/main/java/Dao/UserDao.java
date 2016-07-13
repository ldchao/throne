package Dao;

/**
 * Created by mm on 2016/7/10.
 */
public interface UserDao {

    //tip: the "id" ,"password" ,"userLogin" ,"userState" could not be null, if add succeed ,it will return true
    public boolean addUser(User user);

    public User findUser(String id);

    //tip: the "id" ,"password" ,"userLogin" ,"userState" could not be null,  if update succeed ,it will return true
    public boolean update(User user);

    //tip:  if delete succeed ,it will return true
    public boolean delete(String id);
}
