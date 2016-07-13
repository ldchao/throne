package controller;

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
    @ResponseBody
    public UserListForm getUserList() {
        UserService user=new UserServiceImpl();
        UserListForm userListForm=new UserListForm();
        userListForm.setUserList(user.getUserList());
        return userListForm;
    }
}
