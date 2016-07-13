package DaoImpl;

import Connection.connection;
import Dao.ProjectDao;
import POJO.Project;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by mm on 2016/7/11.
 */
public class ProjectDaoImpl implements ProjectDao{
    public boolean addProject(Project project) {
        Session session= connection.getSession();
        try {
            if (findProject(project.getId())==null){
                session.save(project);
                Transaction transaction=session.beginTransaction();
                transaction.commit();
                connection.closeSession(session);
                return true;
            }else {
                connection.closeSession(session);
                return  false;
            }
        }catch(Exception e){
            e.printStackTrace();
            connection.closeSession(session);
            return false;
        }
    }

    public boolean deleteProject(int id) {
        Session session= connection.getSession();
        try {
            if (findProject(id)!=null){
                Project project=new Project();
                project.setId(id);
                session.delete(project);
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

    public boolean updateProject(Project project) {
        Session session= connection.getSession();
        try {
            if (findProject(project.getId())!=null){
                session.update(project);
                Transaction transaction=session.beginTransaction();
                transaction.commit();
                connection.closeSession(session);
                return true;
            }else {
                connection.closeSession(session);
                return  false;
            }
        }catch(Exception e){
            e.printStackTrace();
            connection.closeSession(session);
            return false;
        }
    }

    public List findProjectByUserId(String userId) {
        Session session=connection.getSession();
        try {
            String hql="from Project p where userId='"+userId+"'";
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

    public Project findProject(int id) {
        Session session= connection.getSession();
        try {
            Project project=session.get(Project.class,id);
            connection.closeSession(session);
            if (project!=null){
                return project;
            }else {
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
            connection.closeSession(session);
            return null;
        }
    }
}
