package serviceImpl;

import Dao.UserDao;
import DaoImpl.UserDaoImpl;
import POJO.User;
import enums.Power;
import model.UserModel;
import service.MessageService;
import service.UserService;

/**
 * Created by zs on 2016/7/10.
 */
public class UserServiceImpl implements UserService {
    public UserModel getUser(String userid){
        UserModel model=new UserModel();
        UserDao dao=new UserDaoImpl();
        User user=new User();
        User po = new User();
        po.setId(userid);
        user=dao.findUser(po);
        model.setId(user.getId());
        model.setPower(Power.valueOf(user.getUserState()));
        MessageService messageService=new MessageServiceImpl();
        int messageNum=messageService.checkMessageCount(userid);
        model.setMessageNum(messageNum);
        return model;
    }

    //测试
    public static void main(String[] args) {
        UserService u=new UserServiceImpl();
        UserModel state=u.getUser("saige");
        System.out.print(String.valueOf(state.getId()));
        System.out.print(String.valueOf(state.getPower()));
    }

}
