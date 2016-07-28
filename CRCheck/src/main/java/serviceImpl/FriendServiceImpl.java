package serviceImpl;

import Dao.CreateIdDao;
import Dao.FriendsDao;
import Dao.UserDao;
import DaoImpl.CreateIdDaoImpl;
import DaoImpl.FriendsDaoImpl;
import DaoImpl.UserDaoImpl;
import POJO.Friends;
import POJO.User;
import enums.UniversalState;
import model.UserModel;
import service.FriendService;

import java.util.ArrayList;

/**
 * Created by lvdechao on 2016/7/28.
 */
public class FriendServiceImpl implements FriendService{

    public UniversalState addFriend(String uid, String fid) {

        FriendsDao friendsDao=new FriendsDaoImpl();
        CreateIdDao createIdDao=new CreateIdDaoImpl();
        int id=createIdDao.CreateIntId("Friends");
        Friends friends=new Friends();
        friends.setId(id);
        friends.setUserId(uid);
        return friendsDao.addFriend(friends)?UniversalState.SUCCESS:UniversalState.FAIL;
    }

    public UniversalState deleteFriend(String uid, String fid) {
        FriendsDao friendsDao=new FriendsDaoImpl();
        return friendsDao.deleteFriend(uid,fid)?UniversalState.SUCCESS:UniversalState.FAIL;
    }

    public UniversalState deleteAllFriend(String uid) {
        FriendsDao friendsDao=new FriendsDaoImpl();
        return friendsDao.deleteAllFriend(uid)?UniversalState.SUCCESS:UniversalState.FAIL;
    }

    public ArrayList<UserModel> findAllFriend(String uid) {
        ArrayList<UserModel> result=new ArrayList<UserModel>();
        FriendsDao friendsDao=new FriendsDaoImpl();
        ArrayList<Friends> friendses=friendsDao.findAllFriend(uid);
        UserDao userDao=new UserDaoImpl();
        User po=new User();
        for (Friends friends:friendses) {
            UserModel um=new UserModel();
            um.setId(uid);
            po.setId(friends.getFriendId());
            User u=userDao.findUser(po);
            um.setHeadPortrait(u.getHeadPortrait());
            result.add(um);
        }
        return result;
    }
}
