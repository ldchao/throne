package Dao;

import POJO.Project;
import POJO.User;

import java.util.List;

/**
 * Created by mm on 2016/7/10.
 */
public interface
ProjectDao {
    //all the attributes must be set,id is automatically set(but not realized,so you need to set it manually)
    public boolean addProject(Project po);

    public boolean deleteProject(Project po);

    public boolean updateProject(Project po);

    public List findProjectByUserId(User po);

    public Project findProject(Project po);
}
