package DaoImpl;

import Connection.connection;
import POJO.*;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mm on 2016/7/12.
 */
public class CreateIdDaoImpl implements Dao.CreateIdDaoImpl{
    public int CreateIntId(String Class) {
        Session session= connection.getSession();
        try {
            String hql="select max(x.id) from "+Class+" x";
            Query query=session.createQuery(hql);
            Integer result=(Integer) query.uniqueResult();
            connection.closeSession(session);
            if (result==null){
                result=1;
            }else {
                result=result+1;
            }
            return result;
        }catch (Exception e){
            e.printStackTrace();
            connection.closeSession(session);
            return 0;
        }
    }

    public String CreateStringId(String Class) {
        Session session=connection.getSession();
        try{
            String hql="select max(x.id) from "+Class+" x";
            Query query=session.createQuery(hql);
            String result=(String)query.uniqueResult();
            connection.closeSession(session);
            if (result==null){
                return "1";
            }else {
                return ""+(Integer.parseInt(result)+1);
            }
        }catch (Exception e){
            e.printStackTrace();
            connection.closeSession(session);
            return null;
        }
    }
}
