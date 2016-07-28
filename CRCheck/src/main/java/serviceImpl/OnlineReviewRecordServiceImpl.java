package serviceImpl;

import Dao.PersonalreviewDao;
import DaoImpl.PersonalreviewDaoImpl;
import POJO.Personalreview;
import model.PersonalReviewRecord;
import service.OnlineReviewRecordService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lvdechao on 2016/7/28.
 */
public class OnlineReviewRecordServiceImpl implements OnlineReviewRecordService {

    public ArrayList<PersonalReviewRecord> getUserRecordList(int projectID, String userID, String path) {
        PersonalreviewDao personalreviewDao=new PersonalreviewDaoImpl();

        Personalreview personalreview=new Personalreview();
        personalreview.setUserId(userID);
        personalreview.setProjectId(projectID);
        List<Personalreview> list=personalreviewDao.getUserPersonalreviewOnline(personalreview,path);

        ArrayList<PersonalReviewRecord> result=new ArrayList<PersonalReviewRecord>();

        for (Personalreview p:list) {
            PersonalReviewRecord r=ReviewRecordHelper.exchange(p);
            result.add(r);
        }
        return result;
    }

    public ArrayList<PersonalReviewRecord> getProjectRecordList(int projectID, String path) {

        PersonalreviewDao personalreviewDao=new PersonalreviewDaoImpl();
        Personalreview po=new Personalreview();
        po.setProjectId(projectID);
        List<Personalreview> reviewList=personalreviewDao.findValidPersonalReview2Online(po,path);

        ArrayList<PersonalReviewRecord> result=new ArrayList<PersonalReviewRecord>();

        for (Personalreview p:reviewList) {
            PersonalReviewRecord r=ReviewRecordHelper.exchange(p);
            result.add(r);
        }
        return result;
    }
}
