package Dao;

import POJO.Friends;

import java.util.ArrayList;

/**
 * Created by dlydd on 2016/7/11.
 */
public interface FriendsDao {
    //id是自增的，不过目前没增加，要手动输入
    public boolean addFriend(Friends friend);

    public boolean deleteFriend(String uid,String fid);

    public boolean deleteAllFriend(String uid);

    public boolean updateFriend(Friends friend);

    public ArrayList<Friends> findAllFriend(String uid);

    public Friends findFriend(String uid,String fid);
}
