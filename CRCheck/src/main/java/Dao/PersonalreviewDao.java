package Dao;

import POJO.Personalreview;
import POJO.Project;
import POJO.User;

import java.util.List;

/**
 * Created by mm on 2016/7/11.
 */
public interface PersonalreviewDao {
    //iterator two
    //need all the attributes,and you can get "id" by using another Interface
    public boolean addPersionalreview(Personalreview po);

    //iterator two
    //you just need to set "id"
    public boolean deletePersonalreview(Personalreview po);

//    //need all the attributes,just update a "Personalreview" one time
//    public boolean updatePersonalreview(Personalreview po);
//
//    //just need the "id" of "User",and the "id" of project,return "Personalreview"
//    public List findProject(User user, Project project);
//
//    //just need the "id" of "Project",return "Personalreview"
//    public List findProject(Project po);
//
//    //just need the "id" of "User",return "Personalreview"
//    public List findProject(User po);

    //iterator two
    //just need the "projectId",return a list of "Personalreview" whose "State" is not Done and "id" is not in "OldPersonalReviewId" of "Summary"
    public List findValidPersonalReview(Personalreview po);

    //iterator two
    //just need the "id",it will return a "Personalreview"
    public Personalreview findPersonalreviewById(Personalreview po);

    //iterator two
    //need the "id" and the "state",it will update the "state" of a "Personalreview"
    public boolean updateState(Personalreview po);

    //iterator two
    //need the "id" and the "result",it will update the "result" of a "Personalreview"
    public boolean updateResult(Personalreview po);

    //iterator two
    //need the "projectId" and "userId",it will return a list of related "Personalreview"
    public List getUserPersonalreview(Personalreview po);
}
