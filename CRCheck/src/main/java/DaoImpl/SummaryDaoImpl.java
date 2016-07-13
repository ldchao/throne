package DaoImpl;

import Connection.connection;
import Dao.SummaryDao;
import POJO.Project;
import POJO.Summary;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by mm on 2016/7/11.
 */
public class SummaryDaoImpl implements SummaryDao {
    public boolean addSummary(Summary po) {
        Session session= connection.getSession();
        try {
            if (findSummaryById(po)==null){
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

    public List findSummary(Project po) {
        Session session= connection.getSession();
        try {
            String hql="from Summary s where s.projectId="+po.getId()+"";
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

    public boolean update(Summary po) {
        Session session= connection.getSession();
        try{
            if (findSummaryById(po)!=null){
                session.update(po);
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

    public boolean delete(Summary po) {
        Session session= connection.getSession();
        try {
            if(findSummaryById(po)!=null){
                Summary summary=new Summary();
                summary.setId(po.getId());
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


    public Summary findSummaryById(Summary po) {
        Session session=connection.getSession();
        try {
            Summary summary=(Summary) session.get(Summary.class,po.getId());
            connection.closeSession(session);
            return summary;
        }catch (Exception e){
            e.printStackTrace();
            connection.closeSession(session);
            return null;
        }
    }

}
