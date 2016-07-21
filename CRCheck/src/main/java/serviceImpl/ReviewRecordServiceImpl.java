package serviceImpl;

import Dao.*;
import DaoImpl.*;
import POJO.*;
import enums.ApproveState;
import enums.CommitState;
import enums.UniversalState;
import model.CommitRecordModel;
import model.CrcCalculation;
import model.PersonalReviewRecord;
import model.SummaryReviewRecord;
import service.CRCService;
import service.ProjectService;
import service.ReviewRecordService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zs on 2016/7/11.
 */
public class ReviewRecordServiceImpl implements ReviewRecordService {
    //提交评审记录
    public UniversalState submitReviewRecord(ArrayList<PersonalReviewRecord> recordList){
//        if(recordList.isEmpty()) {
//            CommitrecordDao commitrecordDao=new CommitrecordDaoImpl();
//            Commitrecord commitrecord=new Commitrecord();
//            commitrecord.setProjectId(commitRecordModel.getProjectId());
//            return UniversalState.SUCCESS;
//        }
//        CreateIdDao createIdDao=new CreateIdDaoImpl();
//        AttendanceDao attendanceDao=new AttendanceDaoImpl();
//        PersonalreviewDao personalreviewDao=new PersonalreviewDaoImpl();
//        SummaryDao summaryDao=new SummaryDaoImpl();
//
//        Date date=new Date();
//        DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String time=format.format(date);
//        PersonalReviewRecord ReviewRecord=recordList.get(0);
//        Attendance attendance=attendanceDao.findAttendance(ReviewRecord.getProjectId(),ReviewRecord.getUserId());
//        String state=attendance.getState();
//        boolean result=true;
//
//        for(PersonalReviewRecord personalReviewRecord:recordList){
//
//            //个人评审表格数据添加
//            Personalreview personalreview=new Personalreview();
//            int id=createIdDao.CreateIntId("Personalreview");
//            personalreview.setId(id);
//            personalreview.setUserId(personalReviewRecord.getUserId());
//            personalreview.setProjectId(personalReviewRecord.getProjectId());
//            personalreview.setCommitTime(time);
//            String path=personalReviewRecord.getPath()+" "+personalReviewRecord.getLineNum();
//            personalreview.setLocation(path);
//            personalreview.setType(personalReviewRecord.getType());
//            personalreview.setDescription(personalReviewRecord.getDescription());
//            personalreview.setState(state);
//            personalreview.setResult(ApproveState.Unapprove.toString());
//            result=result&personalreviewDao.addPersionalreview(personalreview);
//
//        }
//        return result?UniversalState.SUCCESS:UniversalState.FAIL;
        return null;
    }

    //完成评审
    public UniversalState finishReviewRecord(String userID,int projectID){
//        boolean result=true;
//
//        AttendanceDao attendanceDao=new AttendanceDaoImpl();
//        Attendance a=attendanceDao.findAttendance(projectID,userID);
//        if(a.getState().equals("Done")){
//            return UniversalState.SUCCESS;
//        }
//
//        Attendance attendance=new Attendance();
//        attendance.setProjectId(projectID);
//        attendance.setUserId(userID);
//        attendance.setState(CommitState.Done.toString());
//        attendanceDao.updateAttendanceState(attendance);
//
//        /**
//         *  将个人正常评审记录插入汇总表格
//         */
//        User user=new User();
//        user.setId(userID);
//        PersonalreviewDao personalreviewDao=new PersonalreviewDaoImpl();
//        List<Personalreview> list=personalreviewDao.findProject(user);
//        CreateIdDao createIdDao=new CreateIdDaoImpl();
//        SummaryDao summaryDao=new SummaryDaoImpl();
//
//        for (Personalreview personalreview:list) {
//            //汇总表格数据添加
//            if(personalreview.getState().equals("Done")){
//                continue;
//            }
//
//            Summary summary=new Summary();
//            int summaryID=createIdDao.CreateIntId("Summary");
//            summary.setId(summaryID);
//            summary.setUserId(userID);
//            summary.setProjectId(projectID);
//            summary.setLocation(personalreview.getLocation());
//            summary.setType(personalreview.getType());
//            summary.setDescription(personalreview.getDescription());
//            summary.setFlag(1);
//            summary.setCombination("");
//            result=result&summaryDao.addSummary(summary);
//        }
//        if(result=false){
//            return UniversalState.FAIL;
//        }
//
//        /**
//         * 待插入与矩阵交互部分，个人评审结果塞入   attendance.setQualityReview();
//         */
//
//
//        /**
//         * 待插入整个项目评审记录与矩阵交互部分，项目总的质量报告放入 project中的qualityReview
//         */
//        CRCService crcService=new CRCServiceImpl();
//        int[][] matrix=crcService.getMatrix(projectID);
//        String qualityReview="";
//        if(matrix[0].length==1){
//            qualityReview="数据不足，无法预测缺陷数目。";
//        }else {
//            CrcCalculation crcCalculation = new CrcCalculation(matrix);
//
//            qualityReview = "通过算法一预测项目缺陷数为" + crcCalculation.getMhCH()
//                    + ";通过算法二预测项目缺陷数为" + crcCalculation.getMtCH() + "。";
//        }
//        ProjectService projectService=new ProjectServiceImpl();
//        return projectService.updateQualityFeedback(projectID,qualityReview);
        return null;
    }

    //查看参评所有完成评审用户名单
    public ArrayList<String> checkProjectUserList(int projectID) {

        AttendanceDao attendanceDao=new AttendanceDaoImpl();
        ArrayList<String> userlist=attendanceDao.finduserDone(projectID);
        return userlist;
    }

    //按用户查看个人评审记录
    public ArrayList<PersonalReviewRecord> checkPersonalReviewRecord(String userID, int projectID) {
//        PersonalreviewDao personalreviewDao=new PersonalreviewDaoImpl();
//
//        User user=new User();
//        user.setId(userID);
//        Project project=new Project();
//        project.setId(projectID);
//        List<Personalreview> list=personalreviewDao.findProject(user,project);
//        ArrayList<PersonalReviewRecord> result=new ArrayList<PersonalReviewRecord>();
//
//        for (Personalreview p:list) {
//            PersonalReviewRecord r=new PersonalReviewRecord();
//            r.setId(p.getId());
//            r.setUserId(p.getUserId());
//            r.setUserId(p.getUserId());
//            r.setProjectId(p.getProjectId());
//            r.setCommitTime(p.getCommitTime());
//            String[] location=p.getLocation().split(" ");
//            r.setPath(location[0]);
//            r.setLineNum(location[1]);
//            r.setType(p.getType());
//            r.setDescription(p.getDescription());
//            CommitState finishState= CommitState.valueOf(p.getState());
//            String state=finishState== CommitState.Done?"后续提交":"正常提交";
//            r.setState(state);
//            r.setResult(ApproveState.valueOf(p.getResult()));
//            result.add(r);
//        }
//        return result;
        return null;
    }

    //查看评审记录
    public ArrayList<SummaryReviewRecord> checkSummaryReviewRecord(int projectID) {
//        SummaryDao summaryDao=new SummaryDaoImpl();
//        ArrayList<SummaryReviewRecord> result=new ArrayList<SummaryReviewRecord>();
//        Summary summary=new Summary();
//        summary.setProjectId(projectID);
//        List<Summary> list=summaryDao.getValidSummary(summary);
//        for (Summary s:list) {
//            SummaryReviewRecord r=new SummaryReviewRecord();
//            r.setId(s.getId());
//            r.setUserId(s.getUserId());
//            r.setProjectId(s.getProjectId());
//            String[] location=s.getLocation().split(" ");
//            r.setPath(location[0]);
//            r.setLineNum(location[1]);
//            r.setType(s.getType());
//            r.setDescription(s.getDescription());
//            String combination=s.getCombination();
//            r.setCombination(combination);
//            r.setCombinated(combination.equals("")?false:true);
//            result.add(r);
//        }
//        return result;
        return null;
    }

    //合并评审记录（前面为待合并项ID（个人表中），后面会合并后结果）
    public UniversalState mergeReviewRecord(ArrayList<String> recordIDList, SummaryReviewRecord result){

//        SummaryDao summaryDao=new SummaryDaoImpl();
//        CreateIdDao createIdDao=new CreateIdDaoImpl();
//        Summary summary=new Summary();
//        summary.setFlag(0);
//        boolean state=true;
//        String combination="";
//        for (String id:recordIDList) {
//            combination+=id+";";
//            summary.setId(Integer.parseInt(id));
//            state=state&summaryDao.updateFlag(summary);
//        }
//        Summary r=new Summary();
//        r.setId(createIdDao.CreateIntId("Summary"));
//        r.setUserId(result.getUserId());
//        r.setProjectId(result.getProjectId());
//        String location=result.getPath()+" "+result.getLineNum();
//        r.setLocation(location);
//        r.setType(result.getType());
//        r.setDescription(result.getDescription());
//        r.setCombination(combination);
//        state=state&summaryDao.addSummary(r);
//        return state?UniversalState.SUCCESS:UniversalState.FAIL;
        return null;
    }

    //分解评审记录
    public ArrayList<SummaryReviewRecord> disassembleReviewRecord(SummaryReviewRecord result){

//        ArrayList<SummaryReviewRecord> subRecordList=new ArrayList<SummaryReviewRecord>();
//
//        SummaryDao summaryDao=new SummaryDaoImpl();
//        Summary summary=new Summary();
//        summary.setFlag(1);
//        Summary s;
//
//        String combinations=result.getCombination();
//        String idList[]=combinations.split(";");
//        for (String id:idList) {
//            summary.setId(Integer.parseInt(id));
//            summaryDao.updateFlag(summary);
//
//            s=summaryDao.findSummaryById(summary);
//            SummaryReviewRecord r=new SummaryReviewRecord();
//            r.setId(s.getId());
//            r.setUserId(s.getUserId());
//            r.setProjectId(s.getProjectId());
//            String[] location=s.getLocation().split(" ");
//            r.setPath(location[0]);
//            r.setLineNum(location[1]);
//            r.setType(s.getType());
//            r.setDescription(s.getDescription());
//            String combination=s.getCombination();
//            r.setCombination(combination);
//            r.setCombinated(combination.equals("")?false:true);
//            subRecordList.add(r);
//        }
//        summary.setId(result.getId());
//        summaryDao.delete(summary);
//
//        return subRecordList;
        return null;
    }

    //审批评审记录（审批个人的评审记录，reviewRecordID为个人评审记录表格中ID）
    public UniversalState approveReviewRecord(PersonalReviewRecord personalReviewRecord){

        boolean result=true;
        PersonalreviewDao personalreviewDao=new PersonalreviewDaoImpl();

        Personalreview personalreview=new Personalreview();
        personalreview.setId(personalReviewRecord.getId());
        personalreview.setUserId(personalReviewRecord.getUserId());
        personalreview.setProjectId(personalReviewRecord.getProjectId());
        personalreview.setCommitTime(personalReviewRecord.getCommitTime());
        String path=personalReviewRecord.getPath()+" "+personalReviewRecord.getLineNum();
        personalreview.setLocation(path);
        personalreview.setType(personalReviewRecord.getType());
        personalreview.setDescription(personalReviewRecord.getDescription());
        personalreview.setState(personalReviewRecord.getState());
        personalreview.setResult(personalReviewRecord.getResult().toString());
        result=result&personalreviewDao.addPersionalreview(personalreview);

        return result?UniversalState.SUCCESS:UniversalState.FAIL;
    }

    //删除评审(只删除汇总表格，reviewRecordID为汇总评审记录表格中ID)
    public UniversalState deleteReviewRecord(String reviewRecordID){
//        SummaryDao summaryDao=new SummaryDaoImpl();
//        Summary summary=new Summary();
//        summary.setId(Integer.parseInt(reviewRecordID));
//        return summaryDao.delete(summary)?UniversalState.SUCCESS:UniversalState.FAIL;
        return null;
    }

    //确定评审，确定以后将不能修改该项目任何评审
    public UniversalState confirmReviewRecord(int projectID) {
//        SummaryDao summaryDao=new SummaryDaoImpl();
//        Summary summary=new Summary();
//        summary.setProjectId(projectID);
//        return summaryDao.deleteInvalidSummary(summary)?UniversalState.SUCCESS:UniversalState.FAIL;
        return null;
    }
}
