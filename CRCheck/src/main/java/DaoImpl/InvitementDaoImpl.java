package DaoImpl;

import Connection.connection;
import Dao.InvitementDao;
import POJO.Invitement;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Query;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * Created by dlydd on 2016/7/11.
 */
public class InvitementDaoImpl implements InvitementDao{

    public boolean addInvitement(Invitement invitement) {
        Session session= connection.getSession();
        try {
            if (findInvitement(invitement.getProjectId(),invitement.getUserId())==null){
                session.save(invitement);
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

    public boolean deleteInvitementbyProject(String pid) {
        Session session= connection.getSession();
        try {
            Invitement invitement = new Invitement();
            invitement.setProjectId(pid);
            session.delete(invitement);
            Transaction transaction=session.beginTransaction();
            transaction.commit();
            session.close();
            return true;
        }catch (Exception e){
            connection.closeSession(session);
            return false;
        }

    }

    public boolean deleteInvitementbyUser(String uid) {
        Session session= connection.getSession();
        try {
            Invitement invitement = new Invitement();
            invitement.setProjectId(uid);
            session.delete(invitement);
            Transaction transaction=session.beginTransaction();
            transaction.commit();
            session.close();
            return true;
        }catch (Exception e){
            connection.closeSession(session);
            return false;
        }
    }

    public boolean deleteInvitement(String pid, String uid) {
        Session session= connection.getSession();
        try {
            Invitement invitement = new Invitement();
            invitement.setProjectId(pid);
            invitement.setUserId(uid);
            session.delete(invitement);
            Transaction transaction=session.beginTransaction();
            transaction.commit();
            session.close();
            return true;
        }catch (Exception e){
            connection.closeSession(session);
            return false;
        }
    }

    public boolean updateInvitement(Invitement invitemnent) {
        Session session= connection.getSession();
        try {
            session.update(invitemnent);
            Transaction transaction=session.beginTransaction();
            transaction.commit();
            session.close();
            return true;
        }catch (Exception e){
            connection.closeSession(session);
            return false;
        }
    }

    public Invitement findInvitement(String pid, String uid) {
        Session session= connection.getSession();
        try {
            String hql="from Invitement as i where i.projectId="+pid+" and i.userId="+uid;
            Query query = session.createQuery(hql);
            List aList = query.list();
            Invitement invitement = (Invitement) aList.get(0);
            session.close();
            if(invitement!=null){
                return invitement;
            }else {
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
            session.close();
            return  null;
        }
    }

    public ArrayList<Invitement> findAllInvitement(String pid) {
        Session session= connection.getSession();
        try {
            String hql="from Invitement as i where i.projectId="+pid;
            Query query = session.createQuery(hql);
            ArrayList<Invitement> aList = (ArrayList<Invitement>) query.list();
            connection.closeSession(session);
            if(aList.size()!=0){
                return aList;
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
