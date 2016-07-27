package controller;


import enums.UniversalState;
import model.PersonalReviewRecord;
import model.ProjectModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import service.ProjectService;
import service.ReviewRecordService;
import serviceImpl.ProjectServiceImpl;
import serviceImpl.ReviewRecordServiceImpl;
import tool.RecordTransfer;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zs on 2016/7/23.
 */
@Controller
public class DealController {
    //进入发起者查看项目结果界面
    @RequestMapping(value = "/projects/result", method = RequestMethod.GET)
    public ModelAndView getProjectResult(String userId,int projectId) {
        //查找项目
        ProjectService ps = new ProjectServiceImpl();

        ProjectModel project = ps.checkProject(projectId);
        //项目不存在
        if (project == null) {
            return null;
        }
        //安全检查
        if(!project.getUserID().equals(userId))
            return null;

        ModelAndView modelAndView=new ModelAndView("LauncherPage");
        modelAndView.addObject("project", project);
        return modelAndView;
    }

    //查看参评所有完成评审用户名单
    @RequestMapping(value = "/getFinishUserList", method = RequestMethod.POST)
    @ResponseBody
    public List<String> getFinishUserList(int projectID){
        ReviewRecordService review=new ReviewRecordServiceImpl();
        List<String> list=review.checkProjectUserList(projectID);
        return list;
    }

    //查看评审记录
    @RequestMapping(value = "/getSummaryReview", method = RequestMethod.POST)
    @ResponseBody
    public List<PersonalReviewRecord> checkSummaryReview(int projectID){
        ReviewRecordService review=new ReviewRecordServiceImpl();
        List<PersonalReviewRecord> list=review.checkSummaryReviewRecord(projectID);
        return list;
    }

    //查看单条合并记录
    @RequestMapping(value = "/getChildReview", method = RequestMethod.POST)
    @ResponseBody
    public List<PersonalReviewRecord> getChildReview(int reviewId){
        ReviewRecordService review=new ReviewRecordServiceImpl();
        List<PersonalReviewRecord> list=review.getChildReviewRecord(reviewId);
        return list;
    }

    //结束此次项目-发布者
    @RequestMapping(value = "/confirmReview", method = RequestMethod.POST)
    @ResponseBody
    public String confirmReview(int projectID){
        ReviewRecordService review=new ReviewRecordServiceImpl();
        UniversalState state =review.confirmReviewRecord(projectID);
        if(state==UniversalState.SUCCESS)
            return "SUCCESS";
        return "FAIL";
    }

    //合并相同缺陷-重新填写项（参数前面为待合并项ID数组，后面会合并后结果）
    @RequestMapping(value = "/unionAdd", method = RequestMethod.POST)
    @ResponseBody
    public String unionAdd(HttpServletRequest request){
        //需要传的参数
        String userId=request.getParameter("userId");
        int projectId=Integer.parseInt(request.getParameter("projectId"));
        String[] records=request.getParameterValues("records[]");
        String[] union=request.getParameterValues("union[]");
        //参数处理
        ArrayList<String> list=new ArrayList<String>();
        for(String s:records){
            list.add(s);
        }
        //新建合并缺陷记录
        PersonalReviewRecord unionRecord= RecordTransfer.change(union);
        unionRecord.setProjectId(projectId);
        unionRecord.setUserId(userId);
        //调用逻辑层合并
        ReviewRecordService service=new ReviewRecordServiceImpl();
        UniversalState state=service.mergeReviewRecord(list,unionRecord);
        if(state.equals(UniversalState.SUCCESS))
            return "SUCCESS";
        return "FAIL";
    }

    //合并评审记录--选取某条作为合并后项(前面为待合并项ID数组,id为选作展示的项，userid为执行此操作者)
    @RequestMapping(value = "/unionChoose", method = RequestMethod.POST)
    @ResponseBody
    public int unionChoose(HttpServletRequest request){
        //需要传的参数
        String userId=request.getParameter("userId");
        int recordId=Integer.parseInt(request.getParameter("recordId"));
        String[] records=request.getParameterValues("records[]");
        //参数处理
        ArrayList<String> list=new ArrayList<String>();
        for(String s:records){
            list.add(s);
        }
        //调用逻辑层合并
        ReviewRecordService service=new ReviewRecordServiceImpl();
        int mergeId=service.mergeReviewRecord(list,recordId,userId);
        return mergeId;
    }

    //分解评审记录(result只需要personalReviewID--合并项id)
    @RequestMapping(value = "/disassembleAll", method = RequestMethod.POST)
    @ResponseBody
    public ArrayList<PersonalReviewRecord> disassembleAll(int mergedId,String userId){
        ReviewRecordService service=new ReviewRecordServiceImpl();
        ArrayList<PersonalReviewRecord> list=service.disassembleReviewRecord(mergedId,userId);
        return list;
    }

    //分解评审记录（id为合并项ID，idlist为将原来合并子项中要剔除出去的部分），返回剔除掉的部分
    @RequestMapping(value = "/disassembleSome", method = RequestMethod.POST)
    @ResponseBody
    public ArrayList<PersonalReviewRecord> disassembleSome(HttpServletRequest request){
        //获得参数
        int mergedId=Integer.parseInt(request.getParameter("recordId"));
        String userId=request.getParameter("userId");
        String[] idList=request.getParameterValues("idList[]");
        //参数处理
        ArrayList<String> li=new ArrayList<String>();
        for(String s:idList){
            li.add(s);
        }
        //调用逻辑层接口
        ReviewRecordService service=new ReviewRecordServiceImpl();
        ArrayList<PersonalReviewRecord> list=service.disassembleReviewRecord(mergedId,userId,li);
        return list;
    }
}
