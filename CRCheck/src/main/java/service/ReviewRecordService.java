package service;

import enums.UniversalState;
import model.ReviewRecord;

import java.util.ArrayList;

/**
 * Created by lvdechao on 2016/7/10.
 */
public interface ReviewRecordService {

     //提交评审记录
    public UniversalState submitReviewRecord(ArrayList<ReviewRecord> recordList);

    //完成评审
    public UniversalState finishReviewRecord(ArrayList<ReviewRecord> recordList);

    //查看评审记录
    public ArrayList<ReviewRecord> checkReviewRecord(String projectID);

}
