package controller;

import POJO.User;
import enums.UniversalState;
import model.UserInf;
import model.UserListForm;
import model.UserModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import service.UserService;
import serviceImpl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zs on 2016/7/13.
 */
@Controller
public class UserController {
    //获取随机的8个用户
    @RequestMapping(value = "/getUserList", method = RequestMethod.GET)
    public
    @ResponseBody
    List<String> getUserList(String userid) {
        UserService user = new UserServiceImpl();
        return user.getUserList(userid);
    }
    //TODO 四个不同板块的用户推荐

    //个人中心-用户信息
    @RequestMapping(value = "/pages/users")
    public ModelAndView getUser(HttpServletRequest request){
        UserModel userModel=(UserModel)request.getSession().getAttribute("User");
        String userId=userModel.getId();
        UserService userService = new UserServiceImpl();
        UserInf inf=userService.getUserInf(userId);
        if(inf==null)
            return null;
        ModelAndView model=new ModelAndView("PersonalPage");
        model.addObject("inf",inf);
        return model;
    }
    //修改用户信息
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    @ResponseBody
    public String updateUser(HttpServletRequest request) {
        //获得信息
        String id = request.getParameter("id");
        String email = request.getParameter("email");
        String sex = request.getParameter("sex");
        String phone = request.getParameter("phone");
        String blog = request.getParameter("blog");
        String address = request.getParameter("address");
        //转换信息
        UserInf inf = new UserInf();
        inf.setId(id);
        inf.setEmail(email);
        inf.setSex(sex);
        inf.setPhone(phone);
        inf.setBlog(blog);
        inf.setAddress(address);
        //调用更新
        UserService user = new UserServiceImpl();
        UniversalState state = user.update(inf);
        if (state == UniversalState.SUCCESS)
            return "SUCCESS";
        return "FAIL";
    }
    @RequestMapping(value = "/getHeadPortraits", method = RequestMethod.POST)
    @ResponseBody
    public String getHeadPortraits(String userID) {
        UserService userService = new UserServiceImpl();
       return userService.getHeadPortraits(userID);
    }

    //搜索好友
    @RequestMapping(value = "/searchUserList", method = RequestMethod.POST)
    @ResponseBody
    public ArrayList<UserModel> searchUserList(String key){
        UserService service=new UserServiceImpl();
        ArrayList<UserModel> models=service.searchUserList(key);
        return models;
    }

}
