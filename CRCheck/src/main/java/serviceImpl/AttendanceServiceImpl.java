package serviceImpl;

import Dao.AttendanceDao;
import Dao.ProjectDao;
import DaoImpl.AttendanceDaoImpl;
import DaoImpl.ProjectDaoImpl;
import POJO.Attendance;
import POJO.Project;
import POJO.User;
import enums.CommitState;
import enums.ProjectState;
import model.ProjectModel;
import service.AttendanceService;
import service.ProjectService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lvdechao on 2016/7/14.
 */
public class AttendanceServiceImpl implements AttendanceService{

    public ArrayList<ProjectModel> getOwnProjectID(String userID) {
        ArrayList<ProjectModel> result=new ArrayList<ProjectModel>();
        ProjectDao projectDao=new ProjectDaoImpl();
        ProjectService projectService=new ProjectServiceImpl();
        User user=new User();
        user.setId(userID);
        List<Project> list=projectDao.findProjectByUserId(user);
        for (Project p:list) {
            ProjectModel projectModel=projectService.checkProject(p.getId());
            result.add(projectModel);
        }
        return result;
    }

    public ArrayList<ProjectModel> getAttendProjectID(String userID) {
        ArrayList<ProjectModel> result=new ArrayList<ProjectModel>();
        AttendanceDao attendanceDao=new AttendanceDaoImpl();
        ProjectService projectService=new ProjectServiceImpl();
        List<Attendance> list=attendanceDao.findAttendancebyUser(userID);
        for (Attendance a:list) {
            ProjectModel projectModel=projectService.checkProject(a.getProjectId());
            result.add(projectModel);
        }
        return result;


    }

    public String getState(String userID, int projectID) {

        AttendanceDao attendanceDao=new AttendanceDaoImpl();
        Attendance attendance=attendanceDao.findAttendance(projectID,userID);
        return attendance.getState();
    }
}
