package Dao;

import POJO.Personalreview;
import POJO.Project;
import POJO.User;

import java.util.List;

/**
 * Created by mm on 2016/7/11.
 */
public interface PersonalreviewDao {
    public boolean addPersionalreview(Personalreview personalreview);

    public boolean deletePersonalreview(String id);

    public boolean updatePersonalreview(Personalreview personalreview);

    public Personalreview findProject(String userId, String projectId);

    //the attribute of "project"
    public List findProject(Project project);

    public List findProject(User user);

}
