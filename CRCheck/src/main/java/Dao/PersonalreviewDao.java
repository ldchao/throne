package Dao;

import POJO.Personalreview;
import POJO.Project;
import POJO.User;

import java.util.List;

/**
 * Created by mm on 2016/7/11.
 */
public interface PersonalreviewDao {
    //need all the attributes,and you can get "id" by using another Interface
    public boolean addPersionalreview(Personalreview po);

    //you just need to set "id"
    public boolean deletePersonalreview(Personalreview po);

    //just need the "projectId",return a list of "Personalreview" whose "State" is "NotDone" and "id" is not in "OldPersonalReviewId" of "Summary"
   // and the "User" state of the "Project" is "Done" and the "result" is not "Error"
    public List findValidPersonalReview(Personalreview po);

    //just need the "id",it will return a "Personalreview"
    public Personalreview findPersonalreviewById(Personalreview po);

    //need the "id" and the "state",it will update the "state" of a "Personalreview"
    public boolean updateState(Personalreview po);

    //need the "id" and the "result",it will update the "result" of a "Personalreview"
    public boolean updateResult(Personalreview po);

    //need the "projectId" and "userId",it will return a list of "Personalreview" whose "state" is not "Combination"
    public List getUserPersonalreview(Personalreview po);

    //need the "userId" and "projectId",it will return the amount of the defect the "User" found
    public int getUserFoundDefect(Personalreview po);

    //need the "userId", "projectId" and "result",it will return the amount of the defect the "User" found in the state of "result"
    public int getDefectOfResult(Personalreview po);

    //just need the "projectId",return a list of "Personalreview" whose "State" is "NotDone" and "id" is not in "OldPersonalReviewId" of "Summary"
    // and the "User" state of the "Project" is "Done" and the "result" is "Error"
    public List findValidPersonalReview2(Personalreview po);
}
