package DaoImpl;

import Connection.connection;
import Dao.PersonalreviewDao;
import POJO.Personalreview;
import POJO.Project;
import POJO.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by mm on 2016/7/11.
 */
public class PersonalreviewDaoImpl implements PersonalreviewDao {
    public boolean addPersionalreview(Personalreview po) {
        Session session= connection.getSession();
        try {
            if(findPersonalreviewById(po)==null){
                session.save(po);
                Transaction transaction=session.beginTransaction();
                transaction.commit();
                connection.closeSession(session);
                return true;
            }else{
                connection.closeSession(session);
                return false;
            }

        }catch (Exception e){
            e.printStackTrace();
            connection.closeSession(session);
            return false;
        }
    }

    public boolean deletePersonalreview(Personalreview po) {
        Session session= connection.getSession();
        try {
            if (findPersonalreviewById(po)!=null) {
                Personalreview personalreview = new Personalreview();
                personalreview.setId(po.getId());
                session.delete(personalreview);
                Transaction transaction = session.beginTransaction();
                transaction.commit();
                connection.closeSession(session);
                return true;
            }else{
                connection.closeSession(session);
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            connection.closeSession(session);
            return false;
        }
    }

    public List findValidPersonalReview(Personalreview po) {
        Session session=connection.getSession();
        try {
            String hql="from Personalreview p where p.projectId=? " +
                    "and p.state<>'Done' " +
                    "and p.result<>'Error'"+
                    "and p.id not in (select s.oldPersonalReviewId from Summary s) " +
                    "and not exists (select '*' from Attendance a where p.userId=a.userId and p.projectId=a.projectId and a.state='NotDone')" +
                    "order by p.location";
            Query query=session.createQuery(hql);
            query.setInteger(0,po.getProjectId());
            List list=query.list();
            connection.closeSession(session);
            return list;

        }catch (Exception e){
            e.printStackTrace();
            connection.closeSession(session);
            return null;
        }
    }

    public Personalreview findPersonalreviewById(Personalreview po){
        Session session=connection.getSession();
        try {
            Personalreview personalreview=session.get(Personalreview.class,po.getId());
            connection.closeSession(session);
            return personalreview;
        }catch (Exception e){
            e.printStackTrace();
            connection.closeSession(session);
            return null;
        }
    }

    public boolean updateState(Personalreview po) {
        Session session=connection.getSession();
        try{
            if (findPersonalreviewById(po)!=null){
                String hql="update Personalreview p set p.state=? where p.id=?";
                Query query=session.createQuery(hql);
                query.setString(0,po.getState());
                query.setInteger(1,po.getId());
                query.executeUpdate();
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

    public boolean updateResult(Personalreview po) {
        Session session=connection.getSession();
        try{
            if (findPersonalreviewById(po)!=null){
                String hql="update Personalreview p set p.result=? where p.id=?";
                Query query=session.createQuery(hql);
                query.setString(0,po.getResult());
                query.setInteger(1,po.getId());
                query.executeUpdate();
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

    public List getUserPersonalreview(Personalreview po) {
        Session session=connection.getSession();
        try {
            String hql="from Personalreview p where p.projectId=? and p.userId=? and p.state<>'Combination'order by p.location";
            Query query=session.createQuery(hql);
            query.setInteger(0,po.getProjectId());
            query.setString(1,po.getUserId());
            List list=query.list();
            connection.closeSession(session);
            return list;
        }catch (Exception e){
            e.printStackTrace();
            connection.closeSession(session);
            return null;
        }
    }

    public int getUserFoundDefect(Personalreview po) {
        Session session=connection.getSession();
        try {
            String hql="select count (*) from Personalreview p where p.userId=? and p.projectId=? and p.state='NotDone'";
            Query query=session.createQuery(hql);
            query.setString(0, po.getUserId());
            query.setInteger(1,po.getProjectId());
            Integer result=((Number) query.uniqueResult()).intValue();
            connection.closeSession(session);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            connection.closeSession(session);
            return -1;
        }
    }

    public int getDefectOfResult(Personalreview po) {
        Session session=connection.getSession();
        try {
            String hql="select count (*) from Personalreview p where p.userId=? and p.projectId=? and p.state='NotDone' and p.result=?";
            Query query=session.createQuery(hql);
            query.setString(0, po.getUserId());
            query.setInteger(1,po.getProjectId());
            query.setString(2,po.getResult());
            Integer result=((Number) query.uniqueResult()).intValue();
            connection.closeSession(session);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            connection.closeSession(session);
            return -1;
        }
    }

    public List findValidPersonalReview2(Personalreview po) {
        Session session=connection.getSession();
        try {
            String hql="from Personalreview p where p.projectId=? " +
                    "and p.state<>'Done' " +
                    "and p.id not in (select s.oldPersonalReviewId from Summary s) " +
                    "and not exists (select '*' from Attendance a where p.userId=a.userId and p.projectId=a.projectId and a.state='NotDone')" +
                    "order by p.location";
            Query query=session.createQuery(hql);
            query.setInteger(0,po.getProjectId());
            List list=query.list();
            connection.closeSession(session);
            return list;

        }catch (Exception e){
            e.printStackTrace();
            connection.closeSession(session);
            return null;
        }
    }

    public List findValidPersonalReviewOnline(Personalreview po,String path) {
        Session session=connection.getSession();
        try {
            String hql="from Personalreview p where p.projectId=? " +
                    "and p.state<>'Done' " +
                    "and p.result<>'Error'"+
                    "and p.id not in (select s.oldPersonalReviewId from Summary s) " +
                    "and not exists (select '*' from Attendance a where p.userId=a.userId and p.projectId=a.projectId and a.state='NotDone')" +
                    "and p.location like :name " +
                    "order by p.location";
            Query query=session.createQuery(hql);
            query.setInteger(0,po.getProjectId());
            query.setParameter("name",path+"%");
            List list=query.list();
            connection.closeSession(session);
            return list;

        }catch (Exception e){
            e.printStackTrace();
            connection.closeSession(session);
            return null;
        }
    }

    public List getUserPersonalreviewOnline(Personalreview po,String path) {
        Session session=connection.getSession();
        try {
            String hql="from Personalreview p where p.projectId=? and p.userId=? and p.state<>'Combination'" +
                    "and p.location like :name " +
                    "order by p.location";
            Query query=session.createQuery(hql);
            query.setInteger(0,po.getProjectId());
            query.setString(1,po.getUserId());
            query.setParameter("name",path+"%");
            List list=query.list();
            connection.closeSession(session);
            return list;
        }catch (Exception e){
            e.printStackTrace();
            connection.closeSession(session);
            return null;
        }
    }

    public List findValidPersonalReview2Online(Personalreview po,String path) {
        Session session=connection.getSession();
        try {
            String hql="from Personalreview p where p.projectId=? " +
                    "and p.state<>'Done' " +
                    "and p.id not in (select s.oldPersonalReviewId from Summary s) " +
                    "and not exists (select '*' from Attendance a where p.userId=a.userId and p.projectId=a.projectId and a.state='NotDone')" +
                    "and p.location like :name " +
                    "order by p.location";
            Query query=session.createQuery(hql);
            query.setInteger(0,po.getProjectId());
            query.setParameter("name",path+"%");
            List list=query.list();
            connection.closeSession(session);
            return list;

        }catch (Exception e){
            e.printStackTrace();
            connection.closeSession(session);
            return null;
        }
    }
}
