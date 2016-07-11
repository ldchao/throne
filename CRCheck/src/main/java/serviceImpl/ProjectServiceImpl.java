package serviceImpl;

import Dao.ProjectDao;
import DaoImpl.ProjectDaoImpl;
import POJO.Project;
import enums.MessageState;
import enums.ProjectState;
import enums.UniversalState;
import model.ProjectModel;
import service.InvitationListService;
import service.MessageService;
import service.ProjectService;

/**
 * Created by zs on 2016/7/11.
 */
public class ProjectServiceImpl implements ProjectService{
    public UniversalState addProject(ProjectModel projectModel) {
        //初始化对象
        ProjectDao dao = new ProjectDaoImpl();
        Project p=new Project();
        InvitationListService invite=new InvitationListServiceImpl();
        MessageService message=new MessageServiceImpl();
        //projectModel转project
        p.setId(projectModel.getProjectID());
        p.setUserId(projectModel.getUserID());
        //p.setType();
        //加入数据库
        boolean a=dao.addProject(p);
        UniversalState b = invite.saveInvitationList(projectModel.getInvitationList());
        //发送消息
        UniversalState c = message.setIssueMessage(projectModel);
        if(a&&b.equals(UniversalState.SUCCESS)&&c.equals(UniversalState.SUCCESS))
            return UniversalState.SUCCESS;
        return UniversalState.FAIL;
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
