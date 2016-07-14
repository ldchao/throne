package service;

import model.UserModel;

import java.util.List;

/**
 * Created by lvdechao on 2016/7/10.
 */
public interface UserService {

    public UserModel getUser(String userid);
    public List<String> getUserList(String userid);
}
