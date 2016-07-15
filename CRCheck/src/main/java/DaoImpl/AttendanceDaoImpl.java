package DaoImpl;

import Connection.connection;
import Dao.AttendanceDao;
import POJO.Attendance;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Query;

import java.util.ArrayList;
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
    public boolean deleteAttendancebyProject(int pid) {
        Session session = connection.getSession();
        try{
            ArrayList<Attendance> attendance=findAttendancebyProject(pid);
            for(int i=0;i<attendance.size();i++) {
                attendance.get(i).setId(attendance.get(i).getId());
                session.delete(attendance.get(i));
            }
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
            ArrayList<Attendance> attendance=findAttendancebyUser(uid);
            for(int i=0;i<attendance.size();i++) {
                attendance.get(i).setId(attendance.get(i).getId());
                session.delete(attendance.get(i));
            }
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

    public boolean deleteAttendance(int pid, String uid) {
        Session session = connection.getSession();
        try{
            Attendance attendance=findAttendance(pid,uid);
            attendance.setId(attendance.getId());
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

    public Attendance findAttendance(int pid, String uid) {
        Session session= connection.getSession();
        try {
            String hql="from Attendance as a where a.projectId="+pid+" and a.userId='"+uid+"'";
            Query query = session.createQuery(hql);
            List aList = query.list();
            connection.closeSession(session);
            if(aList.size()==0){
                return null;
            }else {
                Attendance attendance = (Attendance) aList.get(0);
                return attendance;
            }

        }catch (Exception e){
            e.printStackTrace();
            connection.closeSession(session);
            return  null;
        }
    }

    public ArrayList<Attendance> findAttendancebyProject(int pid){
        Session session = connection.getSession();
        try {
            String hql="from Attendance a where a.projectId="+pid;
            Query query = session.createQuery(hql);
            ArrayList<Attendance> aList =(ArrayList<Attendance>) query.list();
            connection.closeSession(session);
                return aList;

        }catch (Exception e){
            e.printStackTrace();
            connection.closeSession(session);
            return  null;
        }
    }

    public ArrayList<Attendance> findAttendancebyUser(String uid){
        Session session = connection.getSession();
        try {
            String hql="from Attendance a where a.userId='"+uid+"'";
            Query query = session.createQuery(hql);
            ArrayList<Attendance> aList =(ArrayList<Attendance>) query.list();
            connection.closeSession(session);
                return aList;

        }catch (Exception e){
            e.printStackTrace();
            connection.closeSession(session);
            return  null;
        }
    }

    public ArrayList<String> finduserDone(int pid) {
        Session session = connection.getSession();
        try{
            String hql="from Attendance a where a.projectId="+pid;
            Query query = session.createQuery(hql);
            ArrayList<String> uids = new ArrayList<String>();
            ArrayList<Attendance> aList =(ArrayList<Attendance>) query.list();
            for(int i=0;i<aList.size();i++){
                //System.out.print(aList.get(i).getState());
                if(aList.get(i).getState().equals("Done")){
                    //System.out.print("122122121");
                    uids.add(aList.get(i).getUserId());
                }
            }
            connection.closeSession(session);
            return uids;
        }catch (Exception e){
            e.printStackTrace();
            connection.closeSession(session);
            return  null;
        }

    }

    public ArrayList<String> finduserNotDone(int pid) {
        Session session = connection.getSession();
        try{
            String hql="from Attendance a where a.projectId="+pid;
            Query query = session.createQuery(hql);
            ArrayList<String> uids = new ArrayList<String>();
            ArrayList<Attendance> aList =(ArrayList<Attendance>) query.list();
            for(int i=0;i<aList.size();i++){
                if(aList.get(i).getState().equals("NotDone")){
                    uids.add(aList.get(i).getUserId());
                }
            }
            connection.closeSession(session);
            return uids;
        }catch (Exception e){
            e.printStackTrace();
            connection.closeSession(session);
            return  null;
        }
    }
}
