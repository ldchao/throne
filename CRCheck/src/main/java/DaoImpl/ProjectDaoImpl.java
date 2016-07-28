package DaoImpl;

import Connection.connection;
import Dao.ProjectDao;
import POJO.Project;
import POJO.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by mm on 2016/7/11.
 */
public class ProjectDaoImpl implements ProjectDao{
    public boolean addProject(Project po) {
        Session session= connection.getSession();
        try {
            if (findProject(po)==null){
                session.save(po);
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

    public boolean deleteProject(Project po) {
        Session session= connection.getSession();
        try {
            if (findProject(po)!=null){
                Project project=new Project();
                project.setId(po.getId());
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

    public boolean updateProject(Project po) {
        Session session= connection.getSession();
        try {
            if (findProject(po)!=null){
                session.update(po);
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

    public List findProjectByUserId(User po) {
        Session session=connection.getSession();
        try {
            String hql="from Project p where userId='"+po.getId()+"'";
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

    public Project findProject(Project po) {
        Session session= connection.getSession();
        try {
            Project project=session.get(Project.class,po.getId());
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

    public List getSimilarProject(String name) {
        Session session=connection.getSession();
        try {
            String hql="from Project p where p.name like :name";
            String temp="";
            Query query=session.createQuery(hql);

            //transfer to the format of %
            if (!name.trim().equals("")){
                temp+="%";
                for(int i=0;i<name.length();i++){
                    temp+=name.charAt(i)+"%";
                }
            }

            query.setParameter("name",temp);
            List list=query.list();
            connection.closeSession(session);
            return list;
        }catch (Exception e){
            e.printStackTrace();
            connection.closeSession(session);
            return null;
        }
    }

    public boolean updateCodePath(Project po) {
        return false;
    }
}
