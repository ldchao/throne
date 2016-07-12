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
import java.util.Queue;

/**
 * Created by mm on 2016/7/11.
 */
public class PersonalreviewDaoImpl implements PersonalreviewDao {
    public boolean addPersionalreview(Personalreview personalreview) {
        Session session= connection.getSession();
        try {
            session.save(personalreview);
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

    public boolean deletePersonalreview(int id) {
        Session session= connection.getSession();
        try {
            Personalreview personalreview=new Personalreview();
            personalreview.setId(id);
            session.delete(personalreview);
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

    public boolean updatePersonalreview(Personalreview personalreview) {
        Session session= connection.getSession();
        try {
            session.update(personalreview);
            Transaction transaction=session.beginTransaction();
            transaction.commit();
            connection.closeSession(session);
            return true;
        }catch (Exception e){
            connection.closeSession(session);
            return false;
        }
    }

    public List findProject(String userId, String projectId) {
        Session session= connection.getSession();
        try {
            String hql="from Personalreview p where p.userId='"+userId+"' and projectId='"+projectId+"'";
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

    public List findProject(Project project) {
        Session session= connection.getSession();
        try{
            String hql="from Personalreview p where p.projectId='"+project.getId()+"'";
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

    public List findProject(User user) {
        Session session= connection.getSession();
        try{
            String hql="from Personalreview p where p.userId='"+user.getId()+"'";
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
}
