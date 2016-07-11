package serviceImpl;

import Dao.UserDao;
import DaoImpl.UserDaoImpl;
import POJO.User;
import enums.Power;
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
        model.setPassword(user.getPassword());
        model.setPower(Power.valueOf(user.getUserState()));
        return null;
    }
    //TODO
    //测试
}
