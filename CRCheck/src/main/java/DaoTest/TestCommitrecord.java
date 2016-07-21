package DaoTest;

import Dao.CommitrecordDao;
import DaoImpl.CommitrecordDaoImpl;
import POJO.Commitrecord;

import java.util.List;

/**
 * Created by dlydd on 2016/7/21.
 */
public class TestCommitrecord {
    public static void main(String args[]){
        CommitrecordDao commitrecordDao = new CommitrecordDaoImpl();
        Commitrecord commitrecord = new Commitrecord();
        commitrecord.setId(3);
        commitrecord.setCodeLine(11);
        commitrecord.setCommitTime("11:30");
        commitrecord.setProjectId(1);
        commitrecord.setReviewType("缺陷类型2");
        commitrecord.setTime(25);
        commitrecord.setUserId("asdf");
        //commitrecordDao.addCommitrecord(commitrecord);
//        List list = commitrecordDao.findreviewType(commitrecord);
//        for(int i=0;i<list.size();i++) {
//            System.out.println(list.get(i));
//            System.out.print(list.size());
//        }

    }
}
