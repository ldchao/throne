package Dao;

import POJO.Project;

/**
 * Created by mm on 2016/7/10.
 */
public interface ProjectDao {
    public boolean addProject(Project project);

    public boolean deleteProject(String id);

    public boolean updateProject(Project project);

    public Project findProject(String id);
}
