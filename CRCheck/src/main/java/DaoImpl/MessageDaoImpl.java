package DaoImpl;

import Connection.connection;
import Dao.MessageDao;
import POJO.Message;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dlydd on 2016/7/11.
 */
public class MessageDaoImpl implements MessageDao{
    public boolean addMessage(Message message) {
        Session session= connection.getSession();
        try {
            if (findMessage(message.getId())==null){
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



    public boolean deleteMessage(int id) {
        Session session= connection.getSession();
        try {
        Message message=findMessage(id);
        message.setId(message.getId());
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

    public boolean deleteAllMessage(String uid) {
        Session session= connection.getSession();
        try {
            ArrayList<Message> message=findAllMessage(uid);
            for(int i=0;i<message.size();i++) {
                message.get(i).setId(message.get(i).getId());
                session.delete(message.get(i));
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

    public boolean updateMessageState(Message message) {
        Session session= connection.getSession();
        try {
            String hql="update Message m set m.state=? where m.id=?";
            Query query=session.createQuery(hql);
            query.setString(0, message.getState());
            query.setString(1,message.getUserId());
            query.executeUpdate();
            Transaction transaction=session.beginTransaction();
            transaction.commit();
            connection.closeSession(session);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            connection.closeSession(session);
            return false;
        }
    }



    public Message findMessage(int id) {
        Session session = connection.getSession();
        try {
            String hql = "from Message m where m.id=" + id;
            Query query = session.createQuery(hql);
            List list = query.list();
            session.close();
            if (list.size() == 0) {
                return null;
            } else {
                Message message = (Message) list.get(0);
                return message;
            }

        } catch (Exception e) {
            e.printStackTrace();
            connection.closeSession(session);
            return null;
        }
    }



    public int getMessageNum(String uid) {
        Session session= connection.getSession();
        try {
            String hql="from Message m where m.userId='"+uid+"'";
            Query query = session.createQuery(hql);
            ArrayList<Message> list=(ArrayList<Message>) query.list();
            session.close();
            return list.size();
        }catch (Exception e) {
            e.printStackTrace();
            connection.closeSession(session);
            return 0;
        }

    }

    public ArrayList<Message> findAllMessage(String uid) {
        Session session= connection.getSession();
        try {
           String hql="from Message m where m.userId='"+uid+"'";
            Query query = session.createQuery(hql);
            ArrayList<Message> list=(ArrayList<Message>) query.list();
            session.close();
            if(list.size()!=0){
                return list;
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
