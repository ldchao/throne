package service;

import enums.Power;
import enums.StateMessage;

/**
 * Created by zs on 2016/7/8.
 */
public interface LoginService {
    //注册
    public StateMessage addUser(String userId, String password, Power power) throws Exception;
    //登录
    public StateMessage loginUser(String userId, String password) throws Exception;
}
