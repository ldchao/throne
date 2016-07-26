package controller;

import enums.Power;
import enums.UniversalState;
import model.UserModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.LoginService;
import service.UserService;
import serviceImpl.LoginServiceImpl;
import serviceImpl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by L.H.S on 16/7/11.
 */

@Controller
public class LoginController {

    // 登录
    @RequestMapping(value = "/Login", method = RequestMethod.POST)
    @ResponseBody
    public String Login(HttpServletRequest request, String userId, String password) {

        LoginService loginService = new LoginServiceImpl();
        UniversalState state = loginService.loginUser(userId, password);

        if (state == UniversalState.SUCCESS) {
            UserService userService = new UserServiceImpl();
            UserModel userModel = userService.getUser(userId);
            request.getSession().setAttribute("User", userModel);
        }

        return String.valueOf(state);
    }

    // 注册
    @RequestMapping(value = "/Register", method = RequestMethod.POST)
    @ResponseBody
    public String Register(HttpServletRequest request, String userId, String password, String power) {

        LoginService loginService = new LoginServiceImpl();
        UniversalState state = loginService.addUser(userId, password, Power.valueOf(power));

        if (state == UniversalState.SUCCESS) {
            UserModel userModel = new UserModel();
            userModel.setId(userId);
            userModel.setMessageNum(0);
            userModel.setPower(Power.valueOf(power));
            request.getSession().setAttribute("User", userModel);
        }

        return String.valueOf(state);
    }

    //退出登录
    @RequestMapping(value = "/Logout", method = RequestMethod.POST)
    @ResponseBody
    public String Register(HttpServletRequest request) {
        request.getSession().setAttribute("User", null);
        return UniversalState.SUCCESS.toString();
    }
}
