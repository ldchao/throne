package DaoImpl;

import Connection.connection;
import Dao.PersonalreviewDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by mm on 2016/7/11.
 */
public class PersonalreviewDaoImpl implements PersonalreviewDao {
    public boolean addPersionalreview(Personalreview personalreview) {
        Session session= connection.getSession();
        try {
            if(findPersonalreviewById(personalreview.getId())==null){
                session.save(personalreview);
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

    public boolean deletePersonalreview(int id) {
        Session session= connection.getSession();
        try {
            if (findPersonalreviewById(id)!=null) {
                Personalreview personalreview = new Personalreview();
                personalreview.setId(id);
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

    public boolean updatePersonalreview(Personalreview personalreview) {
        Session session= connection.getSession();
        try {
            if(findPersonalreviewById(personalreview.getId())!=null) {
                session.update(personalreview);
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

    public Personalreview findPersonalreviewById(int id){
        Session session=connection.getSession();
        try {
            Personalreview personalreview=session.get(Personalreview.class,id);
            connection.closeSession(session);
            return personalreview;
        }catch (Exception e){
            e.printStackTrace();
            connection.closeSession(session);
            return null;
        }
    }
}
