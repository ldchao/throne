package service;

import enums.ApproveState;
import enums.UniversalState;
import model.CommitRecordModel;
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

    //查看参评所有完成评审用户名单
    public ArrayList<String> checkProjectUserList(int projectID);

    //按用户查看个人评审记录
    public ArrayList<PersonalReviewRecord> checkPersonalReviewRecord(String userID,int projectID);

    //查看评审记录
    public ArrayList<SummaryReviewRecord> checkSummaryReviewRecord(int projectID);

    //合并评审记录（前面为待合并项ID（个人表中），后面会合并后结果）
    public UniversalState mergeReviewRecord(ArrayList<String> recordIDList, SummaryReviewRecord result);

    //分解评审记录
    public ArrayList<SummaryReviewRecord> disassembleReviewRecord(SummaryReviewRecord result);

    //审批评审记录（审批个人的评审记录，reviewRecordID为个人评审记录表格中ID）
    public UniversalState approveReviewRecord(PersonalReviewRecord personalReviewRecord);

    //删除评审(只删除汇总表格，reviewRecordID为汇总评审记录表格中ID)
    public UniversalState deleteReviewRecord(String reviewRecordID);

    //确定评审结果，确定后将不能再回退（发起者特有权利）
    public UniversalState confirmReviewRecord(int projectID);
}
