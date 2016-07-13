package Dao;

import POJO.Project;
import POJO.Summary;

import java.util.List;

/**
 * Created by mm on 2016/7/11.
 */
public interface SummaryDao {
    //need all the attributes ,you can get "id" by using another Interface
    public boolean addSummary(Summary po);

    //need the "id" of "Project",get all the Summary of a project
    public List findSummary(Project po);

    //need all the attributes,if update succeed ,it will return true .And all the attributes must be set
    public boolean update(Summary po);

    //need the "id" of "Summary",if delete succeed ,it will return true
    public boolean delete(Summary po);
}
