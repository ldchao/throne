package Dao;

import POJO.Attendance;

import java.util.ArrayList;

/**
 * Created by dd on 2016/7/11.
 */
public interface AttendanceDao {
      //id是自增的，不过目前没增加，要手动输入
      public boolean addAttendance(Attendance attendance);

      public boolean deleteAttendancebyProject(int pid);

      public boolean deleteAttendacebyUser(String uid);

      public boolean deleteAttendance(int pid,String uid);

      //need the "userId" and "projectId" to change "state",update a list of "Attendance" one time
      public boolean updateAttendanceState(Attendance attendance);

      //need the "userId" and "projectId" to change "qualityreview",update a list of "Attendance" one time
      public boolean updateAttendanceQualityreview(Attendance attendance);

      public Attendance findAttendance(int pid,String uid);

      public ArrayList<Attendance> findAttendancebyProject(int pid);

      public ArrayList<Attendance> findAttendancebyUser(String uid);

      public ArrayList<String> finduserDone(int pid);

      public ArrayList<String> finduserNotDone(int pid);
}
