package controller;

import enums.Power;
import enums.UniversalState;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.LoginService;
import serviceImpl.LoginServiceImpl;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by L.H.S on 16/7/11.
 */

@Controller
public class LoginController {

    // 登录
    @RequestMapping(value="/Login", method = RequestMethod.POST)
    @ResponseBody
    public String Login(HttpServletRequest request, String userId, String password){

        LoginService loginService = new LoginServiceImpl();
        UniversalState state = loginService.loginUser(userId, password);


        return String.valueOf(state);
    }

    // 注册
    @RequestMapping(value="/Register", method = RequestMethod.POST)
    @ResponseBody
    public String Register(HttpServletRequest request, String userId, String password, String power){

        LoginService loginService = new LoginServiceImpl();

        UniversalState state = loginService.addUser(userId, password, Power.valueOf(power));

        return String.valueOf(state);
    }

}
