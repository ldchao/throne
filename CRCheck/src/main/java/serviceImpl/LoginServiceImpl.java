package serviceImpl;

import Dao.UserDao;
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
//        UserDao dao = new kk();
//        }
//        //创建PO并保存
//        User user=new User();
//        user.setId(userId);
//        user.setPassword(password);
//        user.setId(userId);
//        try {
//            message=dao.addUser();
//        } catch (RemoteException e) {
//            e.printStackTrace();
//            return UniversalState.ERROR;
//        }
        return UniversalState.SUCCESS;
    }
//    @Override
    //登录
    public UniversalState loginUser(String userId, String password){
//        UserPO userPo = null;
//        try {
//            userPo = data.find(id);
//        } catch (RemoteException e) {
//            RMIExceptionHandler.handleRMIException();
//            e.printStackTrace();
//        }
//
//        if(userPo==null){
//            System.out.println("该账号不存在请重新输入");
//            return UniversalState.FAIL;
//        }
//        if(!userPo.getPassword().equals(password)){
//            System.out.println("密码不对哟，看看大小写输对了没");
//            return UniversalState.FAIL;
//        }
          return UniversalState.SUCCESS;
    }
}
