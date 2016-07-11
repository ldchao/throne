package Dao;

import POJO.Summary;

import java.util.List;

/**
 * Created by mm on 2016/7/11.
 */
public interface SummaryDao {
    //tip: the attribute "id" is automatically set ,the other must be set manually
    public boolean addSummary(Summary summary);

    //tip: get all the Summary of a project
    public List findSummary(String projectId);

    //tip: if update succeed ,it will return true
    public boolean update(Summary summary);

    //tip: if delete succeed ,it will return true
    public boolean delete(String id);
}
