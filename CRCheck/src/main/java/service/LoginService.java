package service;

import enums.Power;
import enums.UniversalState;

/**
 * Created by zs on 2016/7/8.
 */
public interface LoginService {
    //注册
    public UniversalState addUser(String userId, String password, Power power);
    //登录
    public UniversalState loginUser(String userId, String password);
}
