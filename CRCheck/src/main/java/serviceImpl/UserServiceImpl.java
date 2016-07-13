package serviceImpl;

import Dao.UserDao;
import DaoImpl.UserDaoImpl;
import POJO.User;
import enums.Power;
import model.UserModel;
import service.MessageService;
import service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    public List<String> getUserList(){
        UserDao dao=new UserDaoImpl();
        List list=dao.getAllUserId();
        if(list.size()<=8)
            return list;
        Random rand = new Random();
        int i=list.size()/8;
        if(list.size()%8!=0)
            i++;
        int randNum = rand.nextInt(i);
        List<String> result=new ArrayList<String>();
        for(int j=0;j<8;j++){
            int k=(8*randNum+j)%list.size();
            result.add(list.get(k)+"");
        }
        return result;
    }

    //测试
    public static void main(String[] args) {
        UserService u=new UserServiceImpl();
        //UserModel state=u.getUser("saige");
        List<String> list=u.getUserList();
        for(String s:list)
            System.out.print(s+" ");
//        System.out.print(String.valueOf(state.getId()));
//        System.out.print(String.valueOf(state.getPower()));
    }

}
