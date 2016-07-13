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

    public boolean deleteInvitementbyProject(int pid) {
        Session session= connection.getSession();
        try {
            Invitement invitement = new Invitement();
            ArrayList<Invitement> invitements=findAllInvitement(pid);
            for(int i=0;i<invitements.size();i++) {
                invitement=invitements.get(i);
                invitement.setId(invitement.getId());
                session.delete(invitement);
            }
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
            ArrayList<Invitement> invitements=findInvitementbyUser(uid);
            for(int i=0;i<invitements.size();i++) {
                invitement=invitements.get(i);
                invitement.setId(invitement.getId());
                session.delete(invitement);
            }
            Transaction transaction=session.beginTransaction();
            transaction.commit();
            session.close();
            return true;
        }catch (Exception e){
            connection.closeSession(session);
            return false;
        }
    }

    public boolean deleteInvitement(int pid, String uid) {
        Session session= connection.getSession();
        try {
            Invitement invitement = new Invitement();
            invitement=findInvitement(pid,uid);
            invitement.setId(invitement.getId());
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
            invitemnent.setId(invitemnent.getId());
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

    public Invitement findInvitement(int pid, String uid) {
        Session session= connection.getSession();
        try {
            String hql="from Invitement i where i.projectId="+pid+" and i.userId='"+uid+"'";
            Query query = session.createQuery(hql);
            List aList = query.list();
            session.close();
           // System.out.print("sdfdsaf"+aList.size());
            if(aList.size()==0){
                return null;
            }else {
                Invitement invitement = (Invitement) aList.get(0);
                return invitement;
            }


        }catch (Exception e){
            e.printStackTrace();
            session.close();
            return  null;
        }
    }

    public ArrayList<Invitement> findAllInvitement(int pid) {
        Session session= connection.getSession();
        try {
            String hql="from Invitement i where i.projectId="+pid+"";
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

    public ArrayList<Invitement> findInvitementbyUser(String uid){
        Session session= connection.getSession();
        try {
            String hql="from Invitement i where i.userId='"+uid+"'";
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
