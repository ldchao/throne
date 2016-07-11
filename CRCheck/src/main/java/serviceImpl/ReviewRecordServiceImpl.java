package serviceImpl;

import enums.ApproveState;
import enums.UniversalState;
import model.ReviewRecord;
import service.ReviewRecordService;

import java.util.ArrayList;

/**
 * Created by zs on 2016/7/11.
 */
public class ReviewRecordServiceImpl implements ReviewRecordService {
    //提交评审记录
    public UniversalState submitReviewRecord(ArrayList<ReviewRecord> recordList){
        return null;
    }

    //完成评审
    public UniversalState finishReviewRecord(ArrayList<ReviewRecord> recordList){
        return null;
    }

    //查看评审记录
    public ArrayList<ReviewRecord> checkReviewRecord(String projectID){
        return null;
    }

    //合并评审记录（前面为待合并项ID（个人表中），后面会合并后结果）
    public UniversalState mergeReviewRecord(ArrayList<String> recordIDList,ReviewRecord result){
        return null;
    }

    //分解评审记录
    public UniversalState disassembleReviewRecord(ReviewRecord result){
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
