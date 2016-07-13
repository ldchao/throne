package serviceImpl;

import Dao.AttendanceDao;
import Dao.CreateIdDao;
import Dao.PersonalreviewDao;
import Dao.SummaryDao;
import DaoImpl.AttendanceDaoImpl;
import DaoImpl.CreateIdDaoImpl;
import DaoImpl.PersonalreviewDaoImpl;
import DaoImpl.SummaryDaoImpl;
import POJO.Attendance;
import POJO.Personalreview;
import POJO.Summary;
import enums.ApproveState;
import enums.UniversalState;
import model.PersonalReviewRecord;
import service.ReviewRecordService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by zs on 2016/7/11.
 */
public class ReviewRecordServiceImpl implements ReviewRecordService {
    //提交评审记录
    public UniversalState submitReviewRecord(ArrayList<PersonalReviewRecord> recordList){
        if(recordList.isEmpty()) return UniversalState.SUCCESS;
        CreateIdDao createIdDao=new CreateIdDaoImpl();
        AttendanceDao attendanceDao=new AttendanceDaoImpl();
        PersonalreviewDao personalreviewDao=new PersonalreviewDaoImpl();
        SummaryDao summaryDao=new SummaryDaoImpl();

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
            String path=personalReviewRecord.getPath()+" "+personalReviewRecord.getLineNum();
            personalreview.setLocation(path);
            personalreview.setType(personalReviewRecord.getType());
            personalreview.setDescription(personalReviewRecord.getDescription());
            personalreview.setState(state);
            personalreview.setResult(ApproveState.Unapprove.toString());
            result=result&personalreviewDao.addPersionalreview(personalreview);

            //汇总表格数据添加
            Summary summary=new Summary();
            int summaryID=createIdDao.CreateIntId("Summary");
            summary.setId(summaryID);
            summary.setProjectId(personalReviewRecord.getProjectId());
            summary.setLocation(path);
            summary.setType(personalReviewRecord.getType());
            summary.setDescription(personalReviewRecord.getDescription());
            summary.setFlag(1);
            summary.setCombination("");
            result=result&summaryDao.addSummary(summary);
        }
        return result?UniversalState.SUCCESS:UniversalState.FAIL;
    }

    //完成评审
    public UniversalState finishReviewRecord(ArrayList<PersonalReviewRecord> recordList){


        return null;
    }

    //查看评审记录
    public ArrayList<PersonalReviewRecord> checkReviewRecord(String projectID){
        return null;
    }

    //合并评审记录（前面为待合并项ID（个人表中），后面会合并后结果）
    public UniversalState mergeReviewRecord(ArrayList<String> recordIDList, PersonalReviewRecord result){
        return null;
    }

    //分解评审记录
    public UniversalState disassembleReviewRecord(PersonalReviewRecord result){
        return null;
    }

    //审批评审记录（审批个人的评审记录，reviewRecordID为个人评审记录表格中ID）
    public UniversalState approveReviewRecord(String reviewRecordID, ApproveState approveState){
        return null;
    }

    //删除评审(只删除汇总表格，reviewRecordID为汇总评审记录表格中ID)
    public UniversalState deleteReviewRecord(String reviewRecordID){
        return null;
    }

}
