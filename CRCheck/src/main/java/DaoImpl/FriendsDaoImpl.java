package DaoImpl;

import Connection.connection;
import Dao.FriendsDao;
import POJO.Friends;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;

/**
 * Created by dlydd on 2016/7/11.
 */
public class FriendsDaoImpl implements FriendsDao {


    public boolean addFriend(Friends friend) {
        Session session= connection.getSession();
        try {
            if (findFriend(friend.getUserId(),friend.getFriendId())==null){
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

    public boolean deleteFriend(String uid,String fid) {
        Session session= connection.getSession();
        try {
            Friends friends = new Friends();
            friends=findFriend(uid,fid);
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

    public boolean deleteAllFriend(String uid){
        Session session = connection.getSession();
        try{
            ArrayList<Friends> friends = new ArrayList<Friends>();
            friends=findAllFriend(uid);
            for(int i=0;i<friends.size();i++){
                session.delete(friends.get(i));
            }
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

    public ArrayList<Friends> findAllFriend(String uid) {
        Session session= connection.getSession();
        try {
            String hql="from Friends f where f.userId='"+uid+"'";
            Query query = session.createQuery(hql);
            ArrayList<Friends> friends = (ArrayList<Friends>)query.list();
            session.close();
            return friends;
        }catch (Exception e){
            e.printStackTrace();
            connection.closeSession(session);
            return  null;
        }
    }

    public Friends findFriend(String uid,String fid){
        Session session = connection.getSession();
        try{
            String hql="from Friends f where f.userId='"+uid+"' and f.friendId='"+fid+"'";
            Query query = session.createQuery(hql);
            ArrayList<Friends> friends = (ArrayList<Friends>)query.list();
            session.close();
            if(friends.size()==0){
                return null;
            }else{
                return friends.get(0);
            }
        }catch (Exception e){
            e.printStackTrace();
            connection.closeSession(session);
            return  null;
        }

    }
}
