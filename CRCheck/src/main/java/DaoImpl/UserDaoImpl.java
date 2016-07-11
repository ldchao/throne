package DaoImpl;

import Connection.connection;
import Dao.UserDao;
import POJO.User;
import com.sun.org.apache.regexp.internal.REUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Created by mm on 2016/7/11.
 */
public class UserDaoImpl implements UserDao {
    public boolean addUser(User user) {
        Session session= connection.getSession();
        try {
            if (findUser(user.getId())==null){
                session.save(user);
                Transaction transaction=session.beginTransaction();
                transaction.commit();
                session.close();
                return true;
            }else {
                session.close();
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            session.close();
            return false;
        }
    }

    public User findUser(String id) {
        Session session= connection.getSession();
        try {
            User user=(User)session.get(User.class,id);
            session.close();
            if(user!=null){
                return user;
            }else {
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
            session.close();
            return  null;
        }
    }

    public boolean update(User user) {
        Session session= connection.getSession();
        try {
            session.update(user);
            Transaction transaction=session.beginTransaction();
            transaction.commit();
            session.close();
            return true;
        }catch (Exception e){
            session.close();
            return false;
        }
    }

    public boolean delete(String id) {
        Session session= connection.getSession();
        try {
            User user=new User();
            user.setId(id);
            session.delete(user);
            Transaction transaction=session.beginTransaction();
            transaction.commit();
            session.close();
            return true;
        }catch (Exception e){
            session.close();
            return false;
        }
    }
}
