package serviceImpl;

import Dao.UserDao;
import DaoImpl.UserDaoImpl;
import POJO.User;
import enums.Power;
import enums.UniversalState;
import model.UserModel;
import service.UserService;

/**
 * Created by zs on 2016/7/10.
 */
public class UserServiceImpl implements UserService {
    public UserModel getUser(String userid){
        UserModel model=new UserModel();
        UserDao dao=new UserDaoImpl();
        User user=new User();
        user=dao.findUser(userid);
        model.setId(user.getId());
        model.setPower(Power.valueOf(user.getUserState()));
        return null;
    }

    //测试
    public static void main(String[] args) {
        UserService u=new UserServiceImpl();
        UserModel state=u.getUser("saige");
        System.out.print(String.valueOf(state));
    }

}
