package controller;

import enums.UniversalState;
import model.UserInf;
import model.UserListForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.UserService;
import serviceImpl.UserServiceImpl;

import java.util.List;

/**
 * Created by zs on 2016/7/13.
 */
@Controller
public class UserController {
    //获取随机的8个用户
    @RequestMapping(value = "/getUserList", method = RequestMethod.GET)
    public @ResponseBody List<String> getUserList(String userid) {
        UserService user=new UserServiceImpl();
        return user.getUserList(userid);
    }
    //TODO 四个不同板块的用户推荐

    //修改用户信息
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    @ResponseBody
    public String getUserList(UserInf inf) {
        UserService user=new UserServiceImpl();
        UniversalState state=user.update(inf);
        if(state==UniversalState.SUCCESS)
            return "SUCCESS";
        return "FAIL";
    }
}
