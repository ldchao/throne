package controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import service.UserService;
import serviceImpl.UserServiceImpl;

import java.util.List;

/**
 * Created by zs on 2016/7/13.
 */
public class UserController {
    //获取随机的8个用户
    @RequestMapping(value = "/getUserList", method = RequestMethod.GET)
    @ResponseBody
    public List<String> getUserList() {
        UserService user=new UserServiceImpl();
        return user.getUserList();
    }
}
