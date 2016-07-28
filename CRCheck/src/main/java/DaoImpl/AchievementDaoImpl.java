package DaoImpl;

import Connection.connection;
import Dao.AchievementDao;
import POJO.Achievement;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.stat.SessionStatistics;

import java.util.List;

/**
 * Created by mm on 2016/7/28.
 */
public class AchievementDaoImpl implements AchievementDao{
    public List getSatisfactoryAchievement(Achievement po, String userId) {
        Session session= connection.getSession();
        try {
            String hql="from Achievement a " +
                    "where a.type= :type " +
                    "and a.value<= :value " +
                    "and a.id not in (select p.achievementId from PersonalAchievement p where p.userId= :userId)";
            Query query=session.createQuery(hql);
            query.setParameter("type",po.getType());
            query.setParameter("value",po.getValue());
            query.setParameter("userId",userId);
            List list=query.list();
            connection.closeSession(session);
            return list;
        }catch (Exception e){
            e.printStackTrace();
            connection.closeSession(session);
            return null;
        }
    }

    public Achievement findAchievementById(Achievement po) {
        Session session=connection.getSession();
        try {
            Achievement achievement=session.get(Achievement.class,po.getId());
            connection.closeSession(session);
            return achievement;
        }catch (Exception e){
            e.printStackTrace();
            connection.closeSession(session);
            return null;
        }
    }
}
