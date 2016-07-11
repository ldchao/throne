package serviceImpl;

import Dao.UserDao;
import DaoImpl.UserDaoImpl;
import POJO.User;
import enums.UniversalState;
import service.LoginService;
import enums.Power;

/**
 * Created by zs on 2016/7/8.
 */
public class LoginServiceImpl implements LoginService {
    //注册
    public UniversalState addUser(String userId, String password, Power power){
        UserDao dao = new UserDaoImpl();

        //创建PO并保存
        User user=new User();
        user.setId(userId);
        user.setPassword(password);
        user.setUserState(String.valueOf(power));
        user.setUserLogin("ONLINE");
        user.setChecklistPath("src");
        boolean result=true;
        try {
            result=dao.addUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            return UniversalState.ERROR;
        }
        if(!result)
            return UniversalState.FAIL;
        return UniversalState.SUCCESS;
    }

    //登录
    public UniversalState loginUser(String userId, String password) {
        UserDao dao = new UserDaoImpl();
        User user = null;
        try {
            user = dao.findUser(userId);
        } catch (Exception e) {
            e.printStackTrace();
            return UniversalState.ERROR;
        }

        if (user == null) {
            System.out.println("该账号不存在请重新输入");
            return UniversalState.FAIL;
        }
        if (!user.getPassword().equals(password)) {
            System.out.println("密码不对哟，看看大小写输对了没");
            return UniversalState.FAIL;
        }
        return UniversalState.SUCCESS;
    }
    //TODO
    //测试
    public static void main(String[] args) {
        LoginService u=new LoginServiceImpl();
        UniversalState state=u.addUser("sha","123123",Power.PRIVATE);
        UniversalState state1=u.loginUser("saige","saige");
        System.out.print(String.valueOf(state));
        System.out.print(String.valueOf(state1));
    }
}
