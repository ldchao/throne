package serviceImpl;

import Dao.*;
import DaoImpl.*;
import POJO.*;
import enums.*;
import model.*;
import service.CRCService;
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
        if(recordList.isEmpty()) {
            return UniversalState.SUCCESS;
        }
        CreateIdDao createIdDao=new CreateIdDaoImpl();
        AttendanceDao attendanceDao=new AttendanceDaoImpl();
        PersonalreviewDao personalreviewDao=new PersonalreviewDaoImpl();

        Date date=new Date();
        DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time=format.format(date);

        PersonalReviewRecord ReviewRecord=recordList.get(0);
        Attendance attendance=attendanceDao.findAttendance(ReviewRecord.getProjectId(),ReviewRecord.getUserId());
        String state=attendance.getState();
        boolean result=true;

        for(PersonalReviewRecord personalReviewRecord:recordList){

            //个人评审表格数据添加
            Personalreview personalreview=new Personalreview();
            int id=createIdDao.CreateIntId("Personalreview");
            personalreview.setId(id);
            personalreview.setUserId(personalReviewRecord.getUserId());
            personalreview.setProjectId(personalReviewRecord.getProjectId());
            personalreview.setCommitTime(time);
            personalreview.setFileType(personalReviewRecord.getFileType().toString());
            String path="";
            if(personalReviewRecord.getFileType()== FileType.Code){
                path=personalReviewRecord.getPath()+" "+personalReviewRecord.getLineNum();
            }else{
                path=personalReviewRecord.getPath()+" "+personalReviewRecord.getPagesNum()+" "+personalReviewRecord.getLineNum();
            }
            personalreview.setLocation(path);
            personalreview.setType(personalReviewRecord.getType());
            personalreview.setDescription(personalReviewRecord.getDescription());
            personalreview.setState(state);
            personalreview.setResult(ApproveState.Unapprove.toString());
            result=result&personalreviewDao.addPersionalreview(personalreview);

        }
       return result?UniversalState.SUCCESS:UniversalState.FAIL;
    }

    //完成评审
    public UniversalState finishReviewRecord(String userID,int projectID){
        boolean result=true;

        AttendanceDao attendanceDao=new AttendanceDaoImpl();
        Attendance attendance=attendanceDao.findAttendance(projectID,userID);
        if(attendance.getState().equals("Done")){
            return UniversalState.SUCCESS;
        }

        attendance.setProjectId(projectID);
        attendance.setUserId(userID);
        attendance.setState(CommitState.Done.toString());
        attendanceDao.updateAttendanceState(attendance);

        CRCService crcService=new CRCServiceImpl();
        ProjectQualityModel projectQualityModel=new ProjectQualityModel();
        projectQualityModel.setUserId(userID);
        projectQualityModel.setProjectId(projectID);

        PersonalreviewDao personalreviewDao=new PersonalreviewDaoImpl();
        Personalreview po=new Personalreview();
        po.setProjectId(projectID);
        po.setUserId(userID);
        int defectNum=personalreviewDao.getUserFoundDefect(po);

        String description=userID+"完成评审，共提交"+defectNum+"个缺陷";
        projectQualityModel.setDescription(description);
        return crcService.addQualityReview(projectQualityModel);

    }

    //查看参评所有完成评审用户名单
    public ArrayList<String> checkProjectUserList(int projectID) {

        AttendanceDao attendanceDao=new AttendanceDaoImpl();
        ArrayList<String> userlist=attendanceDao.finduserDone(projectID);
        return userlist;
    }

    //按用户查看个人评审记录
    public ArrayList<PersonalReviewRecord> checkPersonalReviewRecord(String userID, int projectID) {
        PersonalreviewDao personalreviewDao=new PersonalreviewDaoImpl();

        Personalreview personalreview=new Personalreview();
        personalreview.setUserId(userID);
        personalreview.setProjectId(projectID);
        List<Personalreview> list=personalreviewDao.getUserPersonalreview(personalreview);

        ArrayList<PersonalReviewRecord> result=new ArrayList<PersonalReviewRecord>();

        for (Personalreview p:list) {
            PersonalReviewRecord r=ReviewRecordHelper.exchange(p);
            result.add(r);
        }
        return result;
    }

    //查看评审记录
    public ArrayList<PersonalReviewRecord> checkSummaryReviewRecord(int projectID) {


        PersonalreviewDao personalreviewDao=new PersonalreviewDaoImpl();
        Personalreview po=new Personalreview();
        po.setProjectId(projectID);
        List<Personalreview> reviewList=personalreviewDao.findValidPersonalReview2(po);

        ArrayList<PersonalReviewRecord> result=new ArrayList<PersonalReviewRecord>();

        for (Personalreview p:reviewList) {
            PersonalReviewRecord r=ReviewRecordHelper.exchange(p);
            result.add(r);
        }
        return result;
    }

    //合并评审记录--重新填写项（前面为待合并项ID，后面会合并后结果）
    public int mergeReviewRecord(ArrayList<String> recordIDList, PersonalReviewRecord result){

        boolean state=true;

        Date date=new Date();
        DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time=format.format(date);
        PersonalreviewDao personalreviewDao=new PersonalreviewDaoImpl();

        Personalreview personalreview=new Personalreview();
        CreateIdDao createIdDao=new CreateIdDaoImpl();
        int id=createIdDao.CreateIntId("Personalreview");
        personalreview.setId(id);
        personalreview.setUserId(result.getUserId());
        personalreview.setProjectId(result.getProjectId());
        personalreview.setCommitTime(time);
        personalreview.setFileType(result.getFileType().toString());
        String path="";
        if(result.getFileType()== FileType.Code){
            path=result.getPath()+" "+result.getLineNum();
        }else{
            path=result.getPath()+" "+result.getPagesNum()+" "+result.getLineNum();
        }
        personalreview.setLocation(path);
        personalreview.setType(result.getType());
        personalreview.setDescription(result.getDescription());
        personalreview.setState(CommitState.Combination.toString());
        personalreview.setResult(ApproveState.Unapprove.toString());
        state=state&personalreviewDao.addPersionalreview(personalreview);

        SummaryDao summaryDao=new SummaryDaoImpl();
        Summary summary=new Summary();
        summary.setNewPersonalReviewId(id);
        summary.setProjectId(result.getProjectId());
        for (String recordID:recordIDList) {
            int reviewID=Integer.parseInt(recordID);
            Summary po=new Summary();
            po.setNewPersonalReviewId(reviewID);
            List<Summary> summaryList=summaryDao.getMergedSummary(po);
            if(summaryList.size()==0||summaryList==null){
                summary.setOldPersonalReviewId(reviewID);
                int summaryId=createIdDao.CreateIntId("Summary");
                summary.setId(summaryId);
                state=state&summaryDao.addSummary(summary);
            }else{
                Personalreview personalreview1=new Personalreview();
                personalreview1.setId(reviewID);
                System.out.println("删除"+reviewID);
                personalreviewDao.deletePersonalreview(personalreview1);
                for(Summary s:summaryList){
                    summary.setOldPersonalReviewId(s.getOldPersonalReviewId());
                    int summaryId=createIdDao.CreateIntId("Summary");
                    summary.setId(summaryId);
                    state=state&summaryDao.addSummary(summary);
                }
            }


        }

        CRCService crcService=new CRCServiceImpl();
        ProjectQualityModel projectQualityModel=new ProjectQualityModel();
        projectQualityModel.setUserId(result.getUserId());
        projectQualityModel.setProjectId(result.getProjectId());

        String description=result.getUserId()+"将"+recordIDList.size()+"条缺陷合并为一条。";
        projectQualityModel.setDescription(description);
        if(crcService.addQualityReview(projectQualityModel)==UniversalState.FAIL){
            return -1;
        }

       return state?id:-1;
    }

    //合并评审记录--选取某条作为合并后项(前面为待合并项ID,id为选作展示的项，userid为执行此操作者)
    public int mergeReviewRecord(ArrayList<String> recordIDList, int id, String userID) {
        boolean state=true;

        Date date=new Date();
        DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time=format.format(date);

        PersonalreviewDao personalreviewDao=new PersonalreviewDaoImpl();
        Personalreview po=new Personalreview();
        po.setId(id);
        Personalreview personalreview=personalreviewDao.findPersonalreviewById(po);

        CreateIdDao createIdDao=new CreateIdDaoImpl();
        int newid=createIdDao.CreateIntId("Personalreview");
        personalreview.setId(newid);
        personalreview.setCommitTime(time);
        personalreview.setUserId(userID);
        personalreview.setState(CommitState.Combination.toString());
        personalreview.setResult(ApproveState.Unapprove.toString());

        state=state&personalreviewDao.addPersionalreview(personalreview);

        SummaryDao summaryDao=new SummaryDaoImpl();
        Summary summary=new Summary();
        summary.setNewPersonalReviewId(newid);
        summary.setProjectId(personalreview.getProjectId());
        for (String recordID:recordIDList) {
            int reviewID=Integer.parseInt(recordID);
            Summary po2=new Summary();
            po2.setNewPersonalReviewId(reviewID);
            List<Summary> summaryList=summaryDao.getMergedSummary(po2);

            if(summaryList.size()==0||summaryList==null){
                summary.setOldPersonalReviewId(reviewID);
                int summaryId=createIdDao.CreateIntId("Summary");
                summary.setId(summaryId);
                state=state&summaryDao.addSummary(summary);
            }else{
                Personalreview personalreview1=new Personalreview();
                personalreview1.setId(reviewID);
                personalreviewDao.deletePersonalreview(personalreview1);
                for(Summary s:summaryList){
                    summary.setOldPersonalReviewId(s.getOldPersonalReviewId());
                    int summaryId=createIdDao.CreateIntId("Summary");
                    summary.setId(summaryId);
                    state=state&summaryDao.addSummary(summary);
                }
            }
        }

//        PersonalreviewDao personalreviewDao=new PersonalreviewDaoImpl();
//        Personalreview po=new Personalreview();
//        po.setId(id);
//        Personalreview personalreview=personalreviewDao.findPersonalreviewById(po);
//
//        po.setState(CommitState.Combination.toString());
//
//        personalreviewDao.updateState(po);
//        CreateIdDao createIdDao=new CreateIdDaoImpl();
//
////        state=state&personalreviewDao.addPersionalreview(personalreview);
//
//        SummaryDao summaryDao=new SummaryDaoImpl();
//        Summary summary=new Summary();
//        summary.setNewPersonalReviewId(id);
//        summary.setProjectId(personalreview.getProjectId());
//        for (String recordID:recordIDList) {
//            summary.setOldPersonalReviewId(Integer.parseInt(recordID));
//            int summaryId=createIdDao.CreateIntId("Summary");
//            summary.setId(summaryId);
//            state=state&summaryDao.addSummary(summary);
//        }

        CRCService crcService=new CRCServiceImpl();
        ProjectQualityModel projectQualityModel=new ProjectQualityModel();
        projectQualityModel.setUserId(userID);
        projectQualityModel.setProjectId(personalreview.getProjectId());

        String description=userID+"将"+recordIDList.size()+"条缺陷合并为一条。";
        projectQualityModel.setDescription(description);
        if(crcService.addQualityReview(projectQualityModel)==UniversalState.FAIL){
            return -1;
        }

        return state?newid:-1;
    }

    //分解评审记录
    public ArrayList<PersonalReviewRecord> disassembleReviewRecord(int id,String userID){
        ArrayList<PersonalReviewRecord> result=getChildReviewRecord(id);
        int size=result.size();
        int projectID=0;
        if(size>0){
            PersonalReviewRecord personalReviewRecord=result.get(0);
            projectID=personalReviewRecord.getProjectId();
        }
        PersonalreviewDao personalreviewDao=new PersonalreviewDaoImpl();
        Personalreview po=new Personalreview();
        po.setId(id);
        personalreviewDao.deletePersonalreview(po);

        CRCService crcService=new CRCServiceImpl();
        ProjectQualityModel projectQualityModel=new ProjectQualityModel();
        projectQualityModel.setUserId(userID);
        projectQualityModel.setProjectId(projectID);

        String description=userID+"将一条缺陷分解为"+size+"条。";
        projectQualityModel.setDescription(description);
        crcService.addQualityReview(projectQualityModel);

        return result;
    }

    //分解评审记录（将某几项挑出）
    public ArrayList<PersonalReviewRecord> disassembleReviewRecord(int id,String userID, ArrayList<String> idList) {
        ArrayList<PersonalReviewRecord> result=new ArrayList<PersonalReviewRecord>();
        SummaryDao summaryDao=new SummaryDaoImpl();
        Summary summary=new Summary();
        summary.setNewPersonalReviewId(id);
        for (String childID:idList ) {
            int child_id=Integer.parseInt(childID);
            PersonalReviewRecord personalReviewRecord=checkOnePersonalReviewRecord(child_id);
            result.add(personalReviewRecord);
            summary.setOldPersonalReviewId(child_id);
            summaryDao.deleteSummary(summary);
        }

        List<Summary> summaryList=summaryDao.getMergedSummary(summary);
        if(summaryList==null||summaryList.size()==0){
            PersonalreviewDao personalreviewDao=new PersonalreviewDaoImpl();
            Personalreview po=new Personalreview();
            po.setId(id);
            personalreviewDao.deletePersonalreview(po);
        }

        int size=result.size();
        int projectID=0;
        if(size>0){
            PersonalReviewRecord personalReviewRecord=result.get(0);
            projectID=personalReviewRecord.getProjectId();
        }
        CRCService crcService=new CRCServiceImpl();
        ProjectQualityModel projectQualityModel=new ProjectQualityModel();
        projectQualityModel.setUserId(userID);
        projectQualityModel.setProjectId(projectID);

        String description=userID+"将一条缺陷分解为"+(size+1)+"条。";
        projectQualityModel.setDescription(description);
        crcService.addQualityReview(projectQualityModel);

        return result;
    }

    //审批评审记录（审批个人的评审记录，reviewRecordID为个人评审记录表格中ID）
    public UniversalState approveReviewRecord(int id, ApproveState approveState){

        boolean result=true;
        PersonalreviewDao personalreviewDao=new PersonalreviewDaoImpl();

        Personalreview personalreview=new Personalreview();
        personalreview.setId(id);
        personalreview.setResult(approveState.toString());
        result=result&personalreviewDao.updateResult(personalreview);

        return result?UniversalState.SUCCESS:UniversalState.FAIL;
    }

    //删除评审(只删除汇总表格，reviewRecordID为汇总评审记录表格中ID)
    public UniversalState deleteReviewRecord(String reviewRecordID){
        PersonalreviewDao personalreviewDao=new PersonalreviewDaoImpl();
        Personalreview personalreview=new Personalreview();
        personalreview.setId(Integer.parseInt(reviewRecordID));
        return personalreviewDao.deletePersonalreview(personalreview)?UniversalState.SUCCESS:UniversalState.FAIL;
    }

    //确定评审，确定以后将不能修改该项目任何评审
    public UniversalState confirmReviewRecord(int projectID) {

        ProjectDao dao = new ProjectDaoImpl();
        Project po = new Project();
        po.setId(projectID);
        Project pro = dao.findProject(po);
        if (pro == null)
            return UniversalState.FAIL;
        pro.setProjectState(ProjectState.Over.toString());
        dao.updateProject(pro);

        CRCService crcService=new CRCServiceImpl();
        ProjectQualityModel projectQualityModel=new ProjectQualityModel();
        projectQualityModel.setUserId(pro.getUserId());
        projectQualityModel.setProjectId(projectID);

        String description="发起者"+pro.getUserId()+"结束整个项目的评审。";
        projectQualityModel.setDescription(description);
        return crcService.addQualityReview(projectQualityModel);

        // TODO: 2016/7/27 修改成就系统
    }

    //根据合并项，得到其子项的评审记录
    public ArrayList<PersonalReviewRecord> getChildReviewRecord(int id) {
        SummaryDao summaryDao=new SummaryDaoImpl();
        Summary summary=new Summary();
        summary.setNewPersonalReviewId(id);
        List<Summary> summaryList=summaryDao.getMergedSummary(summary);
        ArrayList<PersonalReviewRecord> result=new ArrayList<PersonalReviewRecord>();
        for (Summary s:summaryList) {
            PersonalReviewRecord p=checkOnePersonalReviewRecord(s.getOldPersonalReviewId());
            result.add(p);
        }
        return result;
    }

    //查看一条评审记录
    private PersonalReviewRecord checkOnePersonalReviewRecord(int id){
        PersonalreviewDao personalreviewDao=new PersonalreviewDaoImpl();
        Personalreview po=new Personalreview();
        po.setId(id);
        Personalreview personalreview=personalreviewDao.findPersonalreviewById(po);
        PersonalReviewRecord result=ReviewRecordHelper.exchange(personalreview);
        return result;
    }


}
