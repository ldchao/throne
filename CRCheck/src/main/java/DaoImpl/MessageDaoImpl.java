package DaoImpl;

import Connection.connection;
import Dao.MessageDao;
import POJO.Message;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Created by dlydd on 2016/7/11.
 */
public class MessageDaoImpl implements MessageDao{
    public boolean addMessage(Message message) {
        Session session= connection.getSession();
        try {
            if (findMessage(message.getUserId())==null){
                session.save(message);
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

    public boolean deleteMessage(String id) {
        Session session= connection.getSession();
        try {
            Message message = new Message();
            message.setUserId(id);
            session.delete(message);
            Transaction transaction=session.beginTransaction();
            transaction.commit();
            connection.closeSession(session);
            return true;
        }catch (Exception e){
            connection.closeSession(session);
            return false;
        }
    }

    public boolean updateMessage(Message message) {
        Session session= connection.getSession();
        try {
            session.update(message);
            Transaction transaction=session.beginTransaction();
            transaction.commit();
            connection.closeSession(session);
            return true;
        }catch (Exception e){
            connection.closeSession(session);
            return false;
        }
    }

    public Message findMessage(String uid) {
        Session session= connection.getSession();
        try {
           Message message=(Message) session.get(Message.class,uid);
            session.close();
            if(message!=null){
                return message;
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
