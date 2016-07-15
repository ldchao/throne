package DaoTest;

import Dao.FriendsDao;
import DaoImpl.FriendsDaoImpl;
import POJO.Friends;

/**
 * Created by dlydd on 2016/7/15.
 */
public class TestFriends {
    public static void main(String args[]){
        FriendsDaoImpl friendsDao = new FriendsDaoImpl();
        Friends friends =new Friends();
        friends.setUserId("shachao");
        friends.setFriendId("23");
        friends.setId(1);
        //friendsDao.addFriend(friends);
        friendsDao.deleteAllFriend("shachao");
       // System.out.print(friendsDao.findFriend("123","23").getId());
    }
}
