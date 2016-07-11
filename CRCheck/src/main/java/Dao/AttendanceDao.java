package Dao;

import POJO.Attendance;

/**
 * Created by dd on 2016/7/11.
 */
public interface AttendanceDao {
      //id是自增的，不过目前没增加，要手动输入
      public boolean addAttendance(Attendance attendance);

      public boolean deleteAttendancebyProject(String pid);

      public boolean deleteAttendacebyUser(String uid);

      public boolean deleteAttendance(String pid,String uid);

      public boolean updateAttendance(Attendance attendance);


      public Attendance findAttendance(String pid,String uid);
}
