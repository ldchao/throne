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

    //need all the attributes,just update a "Personalreview" one time
    public boolean updatePersonalreview(Personalreview po);

    //just need the "id" of "User",and the "id" of project,return "Personalreview"
    public List findProject(User user, Project project);

    //just need the "id" of "Project",return "Personalreview"
    public List findProject(Project po);

    //just need the "id" of "User",return "Personalreview"
    public List findProject(User po);

}
