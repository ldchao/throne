package Dao;

import POJO.Attendance;

/**
 * Created by dd on 2016/7/11.
 */
public interface AttendanceDao {
      public boolean addAttendance(Attendance attendance);

      public boolean deleteAttendancebyProject(String pid);

      public boolean deleteAttendacebyUser(String uid);

      public boolean deleteAttendance(String pid,String uid);

      public boolean updateAttendance(Attendance attendance);

      public Attendance findAttendance(String pid,String uid);
}
