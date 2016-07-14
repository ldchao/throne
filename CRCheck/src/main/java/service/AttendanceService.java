package service;

import model.ProjectModel;

import java.util.ArrayList;

/**
 * Created by lvdechao on 2016/7/14.
 */
public interface AttendanceService {

    // TODO: 2016/7/14 查看我发起的项目列表
    public ArrayList<ProjectModel> getOwnProjectID(String userID);


    // TODO: 2016/7/14 查看我参与的项目列表
    public ArrayList<ProjectModel> getAttendProjectID(String userID);

}
