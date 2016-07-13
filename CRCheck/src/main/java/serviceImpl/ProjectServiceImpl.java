package serviceImpl;

import Dao.CreateIdDao;
import Dao.ProjectDao;
import DaoImpl.CreateIdDaoImpl;
import DaoImpl.ProjectDaoImpl;
import POJO.Project;
import enums.Language;
import enums.Power;
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
    //创建项目
    public UniversalState addProject(ProjectModel projectModel) {
        //初始化对象
        ProjectDao dao = new ProjectDaoImpl();
        Project p=new Project();
        InvitationListService invite=new InvitationListServiceImpl();
        MessageService message=new MessageServiceImpl();
        //生成项目id
        CreateIdDao iddao=new CreateIdDaoImpl();
        int id=iddao.CreateIntId("Project");
        projectModel.setProjectID(id);
        //projectModel转project
        p.setId(id);
        p.setUserId(projectModel.getUserID());
        p.setName(projectModel.getName());
        p.setType(String.valueOf(projectModel.getType()));
        p.setDescription(projectModel.getDiscription());
        p.setProjectState("NotStart");
        p.setPower(String.valueOf(projectModel.getPower()));
        p.setStartTime(projectModel.getStartDate());
        p.setEndTime(projectModel.getEndDate());
        p.setCodePath("");
        p.setAttendReview(projectModel.getAttendReview());
        p.setQualityReview("");
        //加入数据库
        boolean a=dao.addProject(p);
        UniversalState b = invite.saveInvitationList(projectModel.getInvitationList());
        //发送消息
        UniversalState c = message.setIssueMessage(projectModel);
        if(a&&b.equals(UniversalState.SUCCESS)&&c.equals(UniversalState.SUCCESS))
            return UniversalState.SUCCESS;
        return UniversalState.FAIL;
    }
    //删除项目
    public UniversalState deleteProject(int projectID) {
        ProjectDao dao = new ProjectDaoImpl();
        Project po=new Project();
        po.setId(projectID);
        boolean a=dao.deleteProject(po);
        if(a)
            return UniversalState.SUCCESS;
        return UniversalState.FAIL;
    }
    //查看项目
    public ProjectModel checkProject(int projectID) {
        ProjectDao dao = new ProjectDaoImpl();
        Project po=new Project();
        po.setId(projectID);
        Project pro=dao.findProject(po);
        //project-->projectModel
        if(pro==null)
            return null;
        ProjectModel p=new ProjectModel();
        p.setUserID(pro.getUserId());
        p.setName(pro.getName());
        p.setProjectID(pro.getId());
        p.setType(Language.valueOf(pro.getType()));
        p.setDiscription(pro.getDescription());
        p.setState(ProjectState.valueOf(pro.getProjectState()));
        p.setPower(Power.valueOf(pro.getPower()));
        p.setStartDate(pro.getStartTime());
        p.setEndDate(pro.getEndTime());
        p.setProjectPath(pro.getCodePath());
        p.setAttendReview(pro.getAttendReview());
        p.setQualityFeedback(pro.getQualityReview());
        return p;
    }
    //更新项目状态
    public UniversalState updateProjectState(int projectID, ProjectState projectState) {
        //初始化找到对应项目
        ProjectDao dao = new ProjectDaoImpl();
        Project po=new Project();
        po.setId(projectID);
        Project pro=dao.findProject(po);
        //项目未找到
        if(pro==null)
            return UniversalState.FAIL;
        pro.setProjectState(String.valueOf(projectState));
        dao.updateProject(pro);
        return UniversalState.SUCCESS;
    }
    //更新项目质量总结报告
    public UniversalState updateQualityFeedback(int projectID, String newQualityFeedback) {
        //初始化找到对应项目
        ProjectDao dao = new ProjectDaoImpl();
        Project po=new Project();
        po.setId(projectID);
        Project pro=dao.findProject(po);
        //项目未找到
        if(pro==null)
            return UniversalState.FAIL;
        pro.setQualityReview(newQualityFeedback);
        dao.updateProject(pro);
        return UniversalState.SUCCESS;
    }
    //修改项目信息
    public UniversalState updateProjectMessage(int projectID, ProjectModel projectModel) {
        //初始化对象
        ProjectDao dao = new ProjectDaoImpl();
        Project p=new Project();
//        InvitationListService invite=new InvitationListServiceImpl();
//        MessageService message=new MessageServiceImpl();

        //projectModel转project
        p.setId(projectID);
        p.setUserId(projectModel.getUserID());
        p.setName(projectModel.getName());
        p.setType(String.valueOf(projectModel.getType()));
        p.setDescription(projectModel.getDiscription());
        p.setProjectState(String.valueOf(projectModel.getState()));
        p.setPower(String.valueOf(projectModel.getPower()));
        p.setStartTime(projectModel.getStartDate());
        p.setEndTime(projectModel.getEndDate());
        p.setCodePath(projectModel.getProjectPath());
        p.setAttendReview(projectModel.getAttendReview());
        p.setQualityReview(projectModel.getQualityFeedback());
        //更新数据库
        boolean a=dao.updateProject(p);
        //UniversalState b = invite.saveInvitationList(projectModel.getInvitationList());
        //发送消息
//        UniversalState c = message.setIssueMessage(projectModel);
//        if(a&&b.equals(UniversalState.SUCCESS)&&c.equals(UniversalState.SUCCESS))
//            return UniversalState.SUCCESS;
//        return UniversalState.FAIL;
    }
    //TODO 测试
    public static void main(String[] args){
        Project p=new Project();
//        p.setUserId(projectModel.getUserID());
//        p.setName(projectModel.getName());
//        p.setType(String.valueOf(projectModel.getType()));
//        p.setDescription(projectModel.getDiscription());
//        p.setProjectState(String.valueOf(projectModel.getState()));
//        p.setPower(String.valueOf(projectModel.getPower()));
//        p.setStartTime(projectModel.getStartDate());
//        p.setEndTime(projectModel.getEndDate());
//        p.setCodePath(projectModel.getProjectPath());
//        p.setAttendReview(projectModel.getAttendReview());
//        p.setQualityReview(projectModel.getQualityFeedback());
//        ProjectService ps=new ProjectServiceImpl();
        ps.addProject(p);
    }
}
