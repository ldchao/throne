package serviceImpl;

import Dao.CommitrecordDao;
import Dao.CreateIdDao;
import DaoImpl.CommitrecordDaoImpl;
import DaoImpl.CreateIdDaoImpl;
import POJO.Commitrecord;
import enums.UniversalState;
import model.CommitRecordModel;
import service.CommitService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lvdechao on 2016/7/21.
 */
public class CommitServiceImpl implements CommitService{

    public UniversalState commit(CommitRecordModel commitRecordModel) {
        CommitrecordDao commitrecordDao=new CommitrecordDaoImpl();
        CreateIdDao createIdDao=new CreateIdDaoImpl();
        Commitrecord commitrecord=new Commitrecord();
        int id=createIdDao.CreateIntId("Commitrecord");
        commitrecord.setId(id);
        commitrecord.setProjectId(commitRecordModel.getProjectId());
        commitrecord.setUserId(commitRecordModel.getUserId());
        Date date=new Date();
        DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time=format.format(date);
        commitrecord.setCommitTime(time);
        commitrecord.setTime(commitRecordModel.getTime());
        commitrecord.setCodeLine(commitRecordModel.getCodeLine());
        commitrecord.setReviewType(commitRecordModel.getReviewType());
        return commitrecordDao.addCommitrecord(commitrecord)?UniversalState.SUCCESS:UniversalState.FAIL;
    }
}
