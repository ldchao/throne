package controller;

import enums.UniversalState;
import model.UserModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.FriendService;
import serviceImpl.FriendServiceImpl;
import java.util.ArrayList;

/**
 * Created by zs on 2016/7/28.
 */
@Controller
public class FriendsController {
    //添加好友
    @RequestMapping(value = "/addFriend")
    @ResponseBody
    public String addFriend(String uid, String fid){
        FriendService service=new FriendServiceImpl();
        UniversalState state=service.addFriend(uid,fid);
        if(state==UniversalState.SUCCESS)
            return "SUCCESS";
        return "FAIL";
    }
    //删除好友
    @RequestMapping(value = "/deleteFriend")
    @ResponseBody
    public String deleteFriend(String uid,String fid){
        FriendService service=new FriendServiceImpl();
        UniversalState state=service.deleteFriend(uid,fid);
        if(state==UniversalState.SUCCESS)
            return "SUCCESS";
        return "FAIL";
    }
    //删除全部好友
    @RequestMapping(value = "/deleteAllFriend")
    @ResponseBody
    public String deleteAllFriend(String uid){
        FriendService service=new FriendServiceImpl();
        UniversalState state=service.deleteAllFriend(uid);
        if(state==UniversalState.SUCCESS)
            return "SUCCESS";
        return "FAIL";
    }
    //查找全部好友
    @RequestMapping(value = "/findAllFriend")
    @ResponseBody
    public ArrayList<UserModel> findAllFriend(String uid){
        FriendService service=new FriendServiceImpl();
        ArrayList<UserModel> list=service.findAllFriend(uid);
        return list;
    }
}
