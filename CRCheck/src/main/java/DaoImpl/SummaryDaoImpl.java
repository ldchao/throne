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

            session.save(summary);
            Transaction transaction=session.beginTransaction();
            transaction.commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            session.close();
            return false;
        }
    }

    public List findSummary(String projectId) {
        Session session= connection.getSession();
        try {
            String hql="from Summary s where s.projectId="+projectId;
            Query query=session.createQuery(hql);
            List list=query.list();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public boolean update(Summary summary) {
        Session session= connection.getSession();
        return false;
    }

    public boolean delete(String id) {
        Session session= connection.getSession();
        return false;
    }
}
