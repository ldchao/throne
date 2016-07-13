package Dao;

import POJO.Personalreview;
import POJO.Project;
import POJO.User;

import java.util.List;

/**
 * Created by mm on 2016/7/11.
 */
public interface PersonalreviewDao {
    public boolean addPersionalreview(Personalreview po);

    public boolean deletePersonalreview(Personalreview po);

    public boolean updatePersonalreview(Personalreview po);

    public List findProject(User user, Project project);

    //just need to set the "projectId",the attribute of "project"
    public List findProject(Project po);

    //just need to set the "userId",the attribute of "userId"
    public List findProject(User po);

}
