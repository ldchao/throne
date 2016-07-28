package DaoImpl;

import Connection.connection;
import Dao.FileDao;
import POJO.File;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by mm on 2016/7/26.
 */
public class FileDaoImpl implements FileDao {
    public String getFileState(File po) {
        Session session=connection.getSession();
        try {
            String hql="select f.state from File f where f.path=?";
            Query query=session.createQuery(hql);
            query.setString(0,po.getPath());
            String state=(String)query.uniqueResult();
            connection.closeSession(session);
            return state;
        }catch (Exception e){
            e.printStackTrace();
            connection.closeSession(session);
            return null;
        }
    }

    public String getFileLastTime(File po) {
        Session session=connection.getSession();
        try {
            String hql="select f.lastTime from File f where f.path=?";
            Query query=session.createQuery(hql);
            query.setString(0,po.getPath());
            String lastTime=(String)query.uniqueResult();
            connection.closeSession(session);
            return lastTime;
        }catch (Exception e){
            e.printStackTrace();
            connection.closeSession(session);
            return null;
        }
    }

    public boolean changeFileState(File po) {
        Session session=connection.getSession();
        try {
            String hql1="from File f where f.path=?";
            Query query1=session.createQuery(hql1);
            query1.setString(0,po.getPath());
            List list=query1.list();
            if (list.size()!=0){
                String hql2="update File f set f.state=? where f.path=?";
                Query query2=session.createQuery(hql2);
                query2.setString(0,po.getState());
                query2.setString(1,po.getPath());
                query2.executeUpdate();
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

    public boolean addFile(File po) {
        Session session = connection.getSession();
        try{
            if(findFileById(po)==null){
                session.save(po);
                Transaction transaction =session.beginTransaction();
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

    public File findFileById(File po){
        Session session=connection.getSession();
        try {
            File file=session.get(File.class,po.getId());
            connection.closeSession(session);
            return  file;
        }catch (Exception e){
            e.printStackTrace();
            connection.closeSession(session);
            return  null;
        }
    }
}
