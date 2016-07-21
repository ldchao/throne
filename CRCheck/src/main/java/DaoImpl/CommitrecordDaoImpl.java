package DaoImpl;

import Dao.CommitrecordDao;
import POJO.Commitrecord;

import java.util.List;

/**
 * Created by mm on 2016/7/20.
 */
public class CommitrecordDaoImpl implements CommitrecordDao {
    public boolean addCommitrecord(Commitrecord po) {
        return false;
    }

    public List findreviewType(Commitrecord po) {
        return null;
    }

    public List findCommitrecord(Commitrecord po, String startTime, String endTime) {
        return null;
    }

    public int getReviewTime(Commitrecord po) {
        return 0;
    }

    public int getReviewTypeCodeLine(Commitrecord po) {
        return 0;
    }

    public int getReviewCodeLine(Commitrecord po) {
        return 0;
    }

    public int getReviewTimeOfProject(Commitrecord po) {
        return 0;
    }
}
