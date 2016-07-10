package service;

import enums.ProjectState;
import enums.UniversalState;
import model.ProjectModel;

/**
 * Created by lvdechao on 2016/7/10.
 */
public interface ProjectService {

    //创建项目
    public UniversalState addProject(ProjectModel projectModel);

    //删除项目
    public UniversalState deleteProject(String projectID);

    //查看项目
    public ProjectModel checkProject(String projectID);

    //更新项目状态
    public UniversalState updateProjectState(String projectID, ProjectState projectState);

    //更新项目质量总结报告
    public UniversalState updateQualityFeedback(String projectID,String newQualityFeedback);

    //修改项目信息
    public UniversalState updateProjectMessage(String projectID,ProjectModel projectModel);

}
