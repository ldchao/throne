package controller;

import enums.Language;
import enums.Power;
import enums.UniversalState;
import model.InvitationMessage;
import model.ProjectModel;
import model.UserModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import service.LoginService;
import service.ProjectService;
import service.UserService;
import serviceImpl.LoginServiceImpl;
import serviceImpl.ProjectServiceImpl;
import serviceImpl.UserServiceImpl;
import tool.DateHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


/**
 * Created by zs on 2016/7/13.
 */
@Controller
public class ProjectController {
    //发布新项目
    @RequestMapping(value = "/Launch", method = RequestMethod.GET)
    public ModelAndView launch(String[] str1,String[] str2) {
        //表单内容转存成ProjectModel
        ProjectModel p=new ProjectModel();
        //str1第一项 用户id
        p.setUserID(str1[0]);
        //str1第二项，名称-String
        p.setName(str1[1]);
        //str1第三项-项目描述
        p.setDiscription(str1[2]);
        //str1第四项-编程语言
        p.setType(Language.valueOf(str1[3]));
        //str1第五项-开始时间
        p.setStartDate(str1[4]);
        //str1第六项-结束时间
        p.setEndDate(str1[5]);
        //str1第七项-项目权限
        p.setPower(Power.valueOf(str1[6]));
        //str2第一项-自己是否参与(YES,NO)
        p.setAttendReview(str2[0]);
        //str2其他项-邀请用户id
        ArrayList<InvitationMessage> list=new ArrayList<InvitationMessage>();
        for(int i=1;i<str2.length;i++){
            InvitationMessage m=new InvitationMessage();
            m.setUserID(str2[i]);
            list.add(m);
        }
        p.setInvitationList(list);
        //新增项目
        ProjectService ps=new ProjectServiceImpl();
        int id=ps.addProject(p);
        ProjectModel project=ps.checkProject(id);
        //计算项目剩余时间
        String day=DateHelper.daysAnalyse(str1[4],str1[5]);

        ModelAndView modelAndView=new ModelAndView("ProjectDetailPage");
        modelAndView.addObject("p",project);
        modelAndView.addObject("day",day);
        return modelAndView;
    }
}
