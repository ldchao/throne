package DaoImpl;

import Connection.connection;
import Dao.FriendsDao;
import POJO.Friends;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Created by dlydd on 2016/7/11.
 */
public class FriendsDaoImpl implements FriendsDao {


    public boolean addFriend(Friends friend) {
        Session session= connection.getSession();
        try {
            if (findFriend(friend.getFriendId())==null){
                session.save(friend);
                Transaction transaction=session.beginTransaction();
                transaction.commit();
                connection.closeSession(session);
                return true;
            }else {
                connection.closeSession(session);
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            connection.closeSession(session);
            return false;
        }
    }

    public boolean deleteFriend(String fid) {
        Session session= connection.getSession();
        try {
            Friends friends = new Friends();
            friends.setFriendId(fid);
            session.delete(friends);
            Transaction transaction=session.beginTransaction();
            transaction.commit();
            connection.closeSession(session);
            return true;
        }catch (Exception e){
            connection.closeSession(session);
            return false;
        }

    }

    public boolean updateFriend(Friends friend) {
        Session session= connection.getSession();
        try {
            session.update(friend);
            Transaction transaction=session.beginTransaction();
            transaction.commit();
            connection.closeSession(session);
            return true;
        }catch (Exception e){
            connection.closeSession(session);
            return false;
        }
    }

    public Friends findFriend(String fid) {
        Session session= connection.getSession();
        try {
            Friends friends=(Friends) session.get(Friends.class,fid);
            session.close();
            if(friends!=null){
                return friends;
            }else {
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
            connection.closeSession(session);
            return  null;
        }
    }
}
