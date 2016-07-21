package DaoTest;

import Dao.CommitrecordDao;
import DaoImpl.CommitrecordDaoImpl;
import POJO.Commitrecord;

/**
 * Created by dlydd on 2016/7/21.
 */
public class TestCommitrecord {
    public static void main(String args[]){
        CommitrecordDao commitrecordDao = new CommitrecordDaoImpl();
        Commitrecord commitrecord = new Commitrecord();
        commitrecord.setId(1);
        commitrecord.setCodeLine(10);
        commitrecord.setCommitTime("10:30");
        commitrecord.setProjectId(1);
        commitrecord.setReviewType("缺陷");
        commitrecord.setTime(20);
        commitrecord.setUserId("asdf");
        commitrecordDao.addCommitrecord(commitrecord);
    }
}
