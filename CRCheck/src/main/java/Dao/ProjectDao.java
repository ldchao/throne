package Dao;

import POJO.Project;

import java.util.List;

/**
 * Created by mm on 2016/7/10.
 */
public interface
ProjectDao {
    //all the attributes must be set,id is automatically set(but not realized,so you need to set it manually)
    public boolean addProject(Project project);

    public boolean deleteProject(int id);

    public boolean updateProject(Project project);

    public List findProjectByUserId(String userId);

    public Project findProject(int id);
}
