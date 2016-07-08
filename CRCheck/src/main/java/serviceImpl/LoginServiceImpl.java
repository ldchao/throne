package serviceImpl;

import enums.StateMessage;
import service.LoginService;
import enums.Power;

/**
 * Created by zs on 2016/7/8.
 */
public class LoginServiceImpl implements LoginService {
//    @Override
    //注册
    public StateMessage addUser(String userId, String password, Power power) throws Exception{
//        //验证information
//
//        //创建PO并保存
//        UserPO po=changeVoToPo(vo);
//        try {
//            message=data.insert(po);
//        } catch (RemoteException e) {
//            e.printStackTrace();
//            return StateMessage.FAIL;
//        }
        return StateMessage.SUCCESS;
    }
//    @Override
    //登录
    public StateMessage loginUser(String userId, String password) throws Exception{
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
//            return StateMessage.FAIL;
//        }
//        if(!userPo.getPassword().equals(password)){
//            System.out.println("密码不对哟，看看大小写输对了没");
//            return StateMessage.FAIL;
//        }
          return StateMessage.SUCCESS;
    }
}
