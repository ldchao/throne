package DaoImpl;

import Connection.connection;
import Dao.AttendanceDao;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Query;

import java.util.List;

/**
 * Created by dlydd on 2016/7/11.
 */
public class AttendanceDaoImpl  implements AttendanceDao {
    public boolean addAttendance(Attendance attendance){
        Session session= connection.getSession();
        try {
            if (findAttendance(attendance.getProjectId(),attendance.getUserId())==null){
                session.save(attendance);
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

    /**
     * @param pid
     * @return
     */
    public boolean deleteAttendancebyProject(String pid) {
        Session session = connection.getSession();
        try{
            Attendance attendance=new Attendance();
            attendance.setProjectId(pid);
            session.delete(attendance);
            Transaction transaction=session.beginTransaction();
            transaction.commit();
            connection.closeSession(session);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            connection.closeSession(session);
            return false;
        }

    }

    public boolean deleteAttendacebyUser(String uid) {
        Session session = connection.getSession();
        try{
            Attendance attendance=new Attendance();
            attendance.setUserId(uid);
            session.delete(attendance);
            Transaction transaction=session.beginTransaction();
            transaction.commit();
            connection.closeSession(session);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            connection.closeSession(session);
            return false;
        }
    }

    public boolean deleteAttendance(String pid, String uid) {
        Session session = connection.getSession();
        try{
            Attendance attendance=new Attendance();
            attendance.setProjectId(pid);
            attendance.setUserId(uid);
            session.delete(attendance);
            Transaction transaction=session.beginTransaction();
            transaction.commit();
            connection.closeSession(session);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            connection.closeSession(session);
            return false;
        }
    }

    public boolean updateAttendance(Attendance attendance) {
        Session session= connection.getSession();
        try {
            session.update(attendance);
            Transaction transaction=session.beginTransaction();
            transaction.commit();
            connection.closeSession(session);
            return true;
        }catch (Exception e){
            connection.closeSession(session);
            return false;
        }
    }

    public Attendance findAttendance(String pid, String uid) {
        Session session= connection.getSession();
        try {
            String hql="from Attendance as a where a.projectId="+pid+" and a.userId="+uid;
            Query query = session.createQuery(hql);
            List aList = query.list();
            Attendance attendance = (Attendance) aList.get(0);
            connection.closeSession(session);
            if(attendance!=null){
                return attendance;
            }else {
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
            connection.closeSession(session);
            return  null;
        }
    }
}
