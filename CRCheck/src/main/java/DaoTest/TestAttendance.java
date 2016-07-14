package DaoTest;

import DaoImpl.AttendanceDaoImpl;
import POJO.Attendance;

/**
 * Created by dlydd on 2016/7/14.
 */
public class TestAttendance {

    public static void main(String args[]){
        AttendanceDaoImpl attendanceDao = new AttendanceDaoImpl();
        Attendance attendance = new Attendance();
        attendance.setId(1);
        attendance.setUserId("shachao");
        attendance.setState("Done");
        attendance.setProjectId(12);
        attendance.setQualityReview("a");
        //attendanceDao.addAttendance(attendance);
        System.out.print(attendanceDao.finduserDone(12));
    }
}
