package service;

import enums.UniversalState;
import model.CommitRecordModel;

/**
 * Created by lvdechao on 2016/7/21.
 */
public interface CommitService {

    //提交每次评审的行数，用时等
    public UniversalState commit(CommitRecordModel commitRecordModel);

}
