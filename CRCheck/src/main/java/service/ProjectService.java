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
    public UniversalState deleteProject(int projectID);

    //查看项目--没有返回null
    public ProjectModel checkProject(int projectID);

    //更新项目状态
    public UniversalState updateProjectState(int projectID, ProjectState projectState);

    //更新项目质量总结报告
    public UniversalState updateQualityFeedback(int projectID,String newQualityFeedback);

    //修改项目信息
    public UniversalState updateProjectMessage(int projectID,ProjectModel projectModel);

}
