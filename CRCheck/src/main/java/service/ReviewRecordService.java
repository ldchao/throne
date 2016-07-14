package service;

import enums.ApproveState;
import enums.UniversalState;
import model.PersonalReviewRecord;
import model.SummaryReviewRecord;

import java.util.ArrayList;

/**
 * Created by lvdechao on 2016/7/10.
 */
public interface ReviewRecordService {

     //提交评审记录
    public UniversalState submitReviewRecord(ArrayList<PersonalReviewRecord> recordList);

    //完成评审
    public UniversalState finishReviewRecord(String userID,int projectID);

    //查看参评所有用户名单
    public ArrayList<String> checkProjectUserList(String projectID);

    //按用户查看个人评审记录
    public ArrayList<PersonalReviewRecord> checkPersonalReviewRecord(String userID,int projectID);

    //查看评审记录
    public ArrayList<SummaryReviewRecord> checkSummaryReviewRecord(String projectID);

    //合并评审记录（前面为待合并项ID（个人表中），后面会合并后结果）
    public UniversalState mergeReviewRecord(ArrayList<String> recordIDList, PersonalReviewRecord result);

    //分解评审记录
    public UniversalState disassembleReviewRecord(PersonalReviewRecord result);

    //审批评审记录（审批个人的评审记录，reviewRecordID为个人评审记录表格中ID）
    public UniversalState approveReviewRecord(String reviewRecordID, ApproveState approveState);

    //删除评审(只删除汇总表格，reviewRecordID为汇总评审记录表格中ID)
    public UniversalState deleteReviewRecord(String reviewRecordID);
}
