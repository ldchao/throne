package controller;

import Dao.CreateIdDao;
import DaoImpl.CreateIdDaoImpl;
import enums.FileType;
import enums.Power;
import javafx.beans.binding.IntegerExpression;
import model.InvitationMessage;
import model.ProjectModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import service.AttendanceService;
import service.ProjectService;
import service.ReviewRecordService;
import serviceImpl.AttendanceServiceImpl;
import serviceImpl.ProjectServiceImpl;
import serviceImpl.ReviewRecordServiceImpl;
import tool.DateHelper;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by zs on 2016/7/13.
 */
@Controller
public class ProjectController {
    //获得创建项目id-点击加号和发起评审时调用
    @RequestMapping(value = "/getProjectId", method = RequestMethod.POST)
    @ResponseBody
    public int getProjectId() {
        CreateIdDao iddao = new CreateIdDaoImpl();
        int id = iddao.CreateIntId("Project");
        return id;
    }

    //发布新项目
    @RequestMapping(value = "/Launch", method = RequestMethod.POST)
    @ResponseBody
    public int launch(String info1, String info2) {

        String[] str1 = info1.split("[&]");
        String[] str2 = info2.split("[&]");

        //表单内容转存成ProjectModel
        ProjectModel p = new ProjectModel();
        //str1第一项 用户id
        p.setUserID(str1[0]);
        //str1第二项，名称-String
        p.setName(str1[1]);
        //str1第三项-项目描述
        p.setDiscription(str1[2]);
        //str1第四项-编程语言
        p.setType(FileType.valueOf(str1[3]));
        //str1第五项-开始时间
        p.setStartDate(str1[4]);
        //str1第六项-结束时间
        p.setEndDate(str1[5]);
        //str1第七项-项目权限
        p.setPower(Power.valueOf(str1[6]));
        //str1第八项-项目id
        p.setProjectID(Integer.parseInt(str1[7]));
        //str1第九项-是否上传文件，是-UPLOAD，否-NOLOAD
        p.setProjectPath(str1[8]);

        //str2第一项-自己是否参与(YES,NO)
        p.setAttendReview(str2[0]);
        //str2其他项-邀请用户id
        ArrayList<InvitationMessage> list = new ArrayList<InvitationMessage>();
        for (int i = 1; i < str2.length; i++) {
            InvitationMessage m = new InvitationMessage();
            m.setUserID(str2[i]);
            list.add(m);
        }
        p.setInvitationList(list);
        //新增项目
        ProjectService ps = new ProjectServiceImpl();
        int id = ps.addProject(p);
        return id;
    }

    //进入项目
    @RequestMapping(value = "/pages/projects", method = RequestMethod.GET)
    public ModelAndView getProject(int projectId, String userId) {
        //新增项目
        ProjectService ps = new ProjectServiceImpl();
        ProjectModel project = ps.checkProject(projectId);
        //项目不存在
        if (project == null) {
            return null;
        }
        //计算项目剩余时间
        String day = DateHelper.daysAnalyse(project.getStartDate(), project.getEndDate());
        ModelAndView modelAndView = new ModelAndView();
        //发起者看看是否有人评审
        ReviewRecordService record = new ReviewRecordServiceImpl();
        ArrayList<String> list = record.checkProjectUserList(projectId);
        //有人且是发布者

        System.out.println("size:" + list.size() + "user:" + userId +"project:" + project.getUserID());
        if (list.size() != 0 && userId.equals(project.getUserID())) {
            modelAndView.setViewName("LauncherPage");
            //否则
        } else {
            modelAndView.setViewName("ProjectDetailPage");
        }
        modelAndView.addObject("project", project);
        modelAndView.addObject("day", day);
        return modelAndView;
    }

    //查看全部发起项目
    @RequestMapping(value = "/AllLaunchProjects", method = RequestMethod.GET)
    @ResponseBody
    public List<ProjectModel> getAllNewMessage(String userId) {
        AttendanceService attendanceService = new AttendanceServiceImpl();
        List<ProjectModel> list = attendanceService.getOwnProjectID(userId);
        for (ProjectModel model : list) {
            String day = DateHelper.daysAnalyse(model.getStartDate(), model.getEndDate());
            model.setDay(day);
        }
        return list;
    }

    //查看全部参与项目
    @RequestMapping(value = "/AllAttendProjects", method = RequestMethod.GET)
    @ResponseBody
    public List<ProjectModel> getAllOldMessage(String userId) {
        AttendanceService attendanceService = new AttendanceServiceImpl();
        List<ProjectModel> list = attendanceService.getAttendProjectID(userId);
        for (ProjectModel model : list) {
            String day = DateHelper.daysAnalyse(model.getStartDate(), model.getEndDate());
            model.setDay(day);
        }
        return list;
    }
}
