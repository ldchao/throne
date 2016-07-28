package service;

import POJO.Friends;
import enums.UniversalState;
import model.UserModel;

import java.util.ArrayList;

/**
 * Created by lvdechao on 2016/7/28.
 */
public interface FriendService {

    public UniversalState addFriend(String uid, String fid);

    public UniversalState deleteFriend(String uid,String fid);

    public UniversalState deleteAllFriend(String uid);

    public ArrayList<UserModel> findAllFriend(String uid);

}
