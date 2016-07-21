package service;

import enums.UniversalState;
import model.ProjectQualityModel;

/**
 * Created by lvdechao on 2016/7/14.
 */
public interface CRCService {

    // TODO: 2016/7/14 获取矩阵模型
    public int[][] getMatrix(int projectID);

    //增加一条项目预测报告记录(need userID,projectID,description)
    public UniversalState addQualityReview(ProjectQualityModel projectQualityModel);

}
