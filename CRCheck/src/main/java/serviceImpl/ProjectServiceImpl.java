package serviceImpl;

import enums.ProjectState;
import enums.UniversalState;
import model.ProjectModel;
import service.ProjectService;

/**
 * Created by zs on 2016/7/11.
 */
public class ProjectServiceImpl implements ProjectService{
    public UniversalState addProject(ProjectModel projectModel) {
        return null;
    }

    public UniversalState deleteProject(String projectID) {
        return null;
    }

    public ProjectModel checkProject(String projectID) {
        return null;
    }

    public UniversalState updateProjectState(String projectID, ProjectState projectState) {
        return null;
    }

    public UniversalState updateQualityFeedback(String projectID, String newQualityFeedback) {
        return null;
    }

    public UniversalState updateProjectMessage(String projectID, ProjectModel projectModel) {
        return null;
    }
}
