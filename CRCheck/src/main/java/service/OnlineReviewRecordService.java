package service;

import model.PersonalReviewRecord;

import java.util.ArrayList;

/**
 * Created by lvdechao on 2016/7/28.
 */
public interface OnlineReviewRecordService {

    //根据projectID，userID，path取得当前用户当前项目在当前路径文件的个人缺陷列表
    public ArrayList<PersonalReviewRecord> getUserRecordList(int projectID,String userID,String path);

    //根据projectID，path取得当前项目在当前路径文件的汇总缺陷列表
    public ArrayList<PersonalReviewRecord> getProjectRecordList(int projectID,String path);
}
