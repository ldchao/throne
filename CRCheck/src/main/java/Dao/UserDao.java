package Dao;

import POJO.User;

/**
 * Created by mm on 2016/7/10.
 */
public interface UserDao {
    public boolean addUser(User user);

    public User findUser(String id);

    public boolean update(User user);
}