package Dao;

import POJO.Project;
import POJO.User;

import java.util.List;

/**
 * Created by mm on 2016/7/10.
 */
public interface
ProjectDao {
    //need all the attributes ,you can get "id" by using another Interface
    public boolean addProject(Project po);

    //just need the "id" of "Project"
    public boolean deleteProject(Project po);

    //need all the attributes,update a "Project" one time
    public boolean updateProject(Project po);

    //just need the "id" of "User",return "Project"
    public List findProjectByUserId(User po);

    //need the "id" of "Project",return "Project"
    public Project findProject(Project po);

    //need the similar name of "userId",it will return a list of "userId" whose "state" is PUBILC
    public List getSimilarUser(String name);
}
