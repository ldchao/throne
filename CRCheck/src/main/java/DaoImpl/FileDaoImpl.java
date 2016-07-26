package DaoImpl;

import Connection.connection;
import Dao.FileDao;
import POJO.File;
import org.hibernate.Query;
import org.hibernate.Session;

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
