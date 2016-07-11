package Dao;

import POJO.Friends;

/**
 * Created by dlydd on 2016/7/11.
 */
public interface FriendsDao {
    public boolean addFriend(Friends friend);

    public boolean deleteFriend(String fid);

    public boolean updateFriend(Friends friend);

    public Friends findFriend(String fid);
}
