package DaoImpl;

import Connection.connection;
import Dao.SummaryDao;
import POJO.Summary;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by mm on 2016/7/11.
 */
public class SummaryDaoImpl implements SummaryDao {
    public boolean addSummary(Summary summary) {
        Session session= connection.getSession();
        try {
            if (findSummaryById(summary.getId())==null){
                session.save(summary);
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

    public List findSummary(String projectId) {
        Session session= connection.getSession();
        try {
            String hql="from Summary s where s.projectId='"+projectId+"'";
            Query query=session.createQuery(hql);
            List list=query.list();
            connection.closeSession(session);
            return list;
        }catch (Exception e){
            e.printStackTrace();
            connection.closeSession(session);
            return null;
        }
    }

    public boolean update(Summary summary) {
        Session session= connection.getSession();
        try{
            if (findSummaryById(summary.getId())!=null){
                session.update(summary);
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

    public boolean delete(int id) {
        Session session= connection.getSession();
        try {
            if(findSummaryById(id)!=null){
                Summary summary=new Summary();
                summary.setId(id);
                session.delete(summary);
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


    public Summary findSummaryById(int id) {
        Session session=connection.getSession();
        try {
            Summary summary=(Summary) session.get(Summary.class,id);
            connection.closeSession(session);
            return summary;
        }catch (Exception e){
            connection.closeSession(session);
            return null;
        }
    }

}
