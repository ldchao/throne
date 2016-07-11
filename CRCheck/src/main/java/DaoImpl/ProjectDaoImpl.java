package DaoImpl;

import Connection.connection;
import Dao.ProjectDao;
import POJO.Project;
import POJO.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
                session.close();
                return true;
            }else {
                session.close();
                return  false;
            }
        }catch(Exception e){
            e.printStackTrace();
            session.close();
            return false;
        }
    }

    public boolean deleteProject(String id) {
        Session session= connection.getSession();
        try {
            if (findProject(id)!=null){
                Project project=new Project();
                project.setId(id);
                session.delete(project);
                Transaction transaction=session.beginTransaction();
                transaction.commit();
                return true;
            }else{
                session.close();
                return false;
            }
        }catch (Exception e){
            session.close();
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
                session.close();
                return true;
            }else {
                session.close();
                return  false;
            }
        }catch(Exception e){
            e.printStackTrace();
            session.close();
            return false;
        }
    }

    public Project findProject(String id) {
        Session session= connection.getSession();
        try {
            Project project=session.get(Project.class,id);
            session.close();
            if (project!=null){
                return project;
            }else {
                return null;
            }
        }catch (Exception e){
            session.close();
            return null;
        }
    }
}
