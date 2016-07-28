package DaoImpl;

import Connection.connection;
import Dao.ContributionDao;
import POJO.Contribution;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by mm on 2016/7/28.
 */
public class ContributionDaoImpl implements ContributionDao {

    public boolean addContribution(Contribution po) {
        Session session= connection.getSession();
        try {
            if (findContributionById(po)==null){
                session.save(po);
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

    public double getSumOfRow(Contribution po) {
        Session session=connection.getSession();
        try {
            String hql="select sum(c.row) from Contribution c where c.userId=:userId";
            Query query=session.createQuery(hql);
            query.setParameter("userId",po.getUserId());
            double result=(Double) query.uniqueResult();
            connection.closeSession(session);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            connection.closeSession(session);
            return 0;
        }
    }

    public double getSumOfTime(Contribution po) {
        Session session=connection.getSession();
        try {
            String hql="select sum(c.time) from Contribution c where c.userId=:userId";
            Query query=session.createQuery(hql);
            query.setParameter("userId",po.getUserId());
            double result=(Double) query.uniqueResult();
            connection.closeSession(session);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            connection.closeSession(session);
            return 0;
        }
    }

    public double getSumOfAmount(Contribution po) {
        Session session=connection.getSession();
        try {
            String hql="select sum(c.amount) from Contribution c where c.userId=:userId";
            Query query=session.createQuery(hql);
            query.setParameter("userId",po.getUserId());
            double result=(Double) query.uniqueResult();
            connection.closeSession(session);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            connection.closeSession(session);
            return 0;
        }
    }

    public double getAverageOfAccuracy(Contribution po) {
        Session session=connection.getSession();
        try {
            String hql="select avg(c.accuracy) from Contribution c where c.userId=:userId";
            Query query=session.createQuery(hql);
            query.setParameter("userId",po.getUserId());
            double result=(Double) query.uniqueResult();
            connection.closeSession(session);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            connection.closeSession(session);
            return 0;
        }
    }

    public double getAverageOfCoverage(Contribution po) {
        Session session=connection.getSession();
        try {
            String hql="select avg(c.coverage) from Contribution c where c.userId=:userId";
            Query query=session.createQuery(hql);
            query.setParameter("userId",po.getUserId());
            double result=(Double) query.uniqueResult();
            connection.closeSession(session);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            connection.closeSession(session);
            return 0;
        }
    }

    public List getContributionsByUserId(Contribution po) {
        Session session=connection.getSession();
        try {
            String hql="from Contribution c where c.userId=:userId";
            Query query=session.createQuery(hql);
            query.setParameter("userId",po.getUserId());
            List list=query.list();
            connection.closeSession(session);
            return list;
        }catch (Exception e){
            e.printStackTrace();
            connection.closeSession(session);
            return null;
        }
    }

    public Contribution findContributionById(Contribution po){
        Session session=connection.getSession();
        try {
            Contribution contribution=session.get(Contribution.class,po.getId());
            connection.closeSession(session);
            return contribution;
        }catch (Exception e){
            e.printStackTrace();
            connection.closeSession(session);
            return null;
        }
    }
}
