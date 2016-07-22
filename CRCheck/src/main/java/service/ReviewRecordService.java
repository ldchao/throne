package service;

import enums.ApproveState;
import enums.UniversalState;
import model.PersonalReviewRecord;

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
    public ArrayList<PersonalReviewRecord> checkSummaryReviewRecord(int projectID);

    //合并评审记录--重新填写项（前面为待合并项ID，后面会合并后结果）
    public UniversalState mergeReviewRecord(ArrayList<String> recordIDList, PersonalReviewRecord result);

    //合并评审记录--选取某条作为合并后项(前面为待合并项ID,id为选作展示的项，userid为执行此操作者)
    public UniversalState mergeReviewRecord(ArrayList<String> recordIDList, int id,String userID);

    //分解评审记录(result只需要personalReviewID)
    public ArrayList<PersonalReviewRecord> disassembleReviewRecord(int id);

    //分解评审记录（id为合并项，idlist为将原来合并子项中要剔除出去的部分），返回剔除掉的部分
    public ArrayList<PersonalReviewRecord> disassembleReviewRecord(int id,ArrayList<String> idList);

    //审批评审记录（审批个人的评审记录，reviewRecordID为个人评审记录表格中ID）
    public UniversalState approveReviewRecord(int id, ApproveState approveState);

    //删除评审(只删除汇总表格，reviewRecordID为汇总评审记录表格中ID)
    public UniversalState deleteReviewRecord(String reviewRecordID);

    //确定评审结果，确定后将不能再回退（发起者特有权利）
    public UniversalState confirmReviewRecord(int projectID);

    //根据合并项的id，查看合并前的所有项
    public ArrayList<PersonalReviewRecord> getChildReviewRecord(int id);
}
