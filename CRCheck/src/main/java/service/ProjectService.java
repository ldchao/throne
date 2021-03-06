package service;

import enums.ProjectState;
import enums.UniversalState;
import model.ProjectModel;

import java.util.List;

/**
 * Created by lvdechao on 2016/7/10.
 */
public interface ProjectService {

    //创建项目
    public int addProject(ProjectModel projectModel);

    //删除项目
    public UniversalState deleteProject(int projectID);

    //查看项目--没有返回null
    public ProjectModel checkProject(int projectID);

    //更新项目状态
    public UniversalState updateProjectState(int projectID, ProjectState projectState);

    //修改项目信息
    public UniversalState updateProjectMessage(int projectID,ProjectModel projectModel);

}
