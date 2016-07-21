package controller;

import enums.FileType;
import enums.Power;
import model.InvitationMessage;
import model.ProjectModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import service.AttendanceService;
import service.ProjectService;
import serviceImpl.AttendanceServiceImpl;
import serviceImpl.ProjectServiceImpl;
import tool.DateHelper;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by zs on 2016/7/13.
 */
@Controller
public class ProjectController {
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
    public ModelAndView getProject(int projectId) {
        //新增项目
        ProjectService ps = new ProjectServiceImpl();
        ProjectModel project = ps.checkProject(projectId);
        //项目不存在
        if (project == null) {
            return null;
        }
        //计算项目剩余时间
        String day = DateHelper.daysAnalyse(project.getStartDate(),project.getEndDate());

        ModelAndView modelAndView=new ModelAndView("ProjectDetailPage");
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
        for(ProjectModel model:list){
            String day=DateHelper.daysAnalyse(model.getStartDate(),model.getEndDate());
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
        for(ProjectModel model:list){
            String day=DateHelper.daysAnalyse(model.getStartDate(),model.getEndDate());
            model.setDay(day);
        }
        return list;
    }
}
