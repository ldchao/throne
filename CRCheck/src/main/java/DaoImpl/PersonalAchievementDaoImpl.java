package DaoImpl;

import Connection.connection;
import Dao.AchievementDao;
import Dao.PersonalAchievementDao;
import POJO.Achievement;
import POJO.PersonalAchievement;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Created by mm on 2016/7/28.
 */
public class PersonalAchievementDaoImpl implements PersonalAchievementDao  {

    public ArrayList<Integer> getAchievements(PersonalAchievement po) {
        Session session=connection.getSession();
        try {
            String hql="select p.achievementId from PersonalAchievement p where p.userId=:userId order by p.achievementId";
            Query query=session.createQuery(hql);
            query.setParameter("userId",po.getUserId());
            ArrayList<Integer> arrayList=(ArrayList<Integer>) query.list();
            connection.closeSession(session);
            return arrayList;
        }catch (Exception e){
            e.printStackTrace();
            connection.closeSession(session);
            return null;
        }
    }

    public boolean addPersonalAchievement(PersonalAchievement po) {
        Session session=connection.getSession();
        try {
            if (findPersonalAchievementById(po)==null){
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

    public PersonalAchievement findPersonalAchievementById(PersonalAchievement po){
        Session session=connection.getSession();
        try {
            PersonalAchievement personalAchievement=session.get(PersonalAchievement.class,po.getId());
            connection.closeSession(session);
            return personalAchievement;
        }catch (Exception e){
            e.printStackTrace();
            connection.closeSession(session);
            return null;
        }
    }
}
