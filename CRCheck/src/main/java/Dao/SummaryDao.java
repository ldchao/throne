package Dao;

import POJO.Project;
import POJO.Summary;

import java.util.List;

/**
 * Created by mm on 2016/7/11.
 */
public interface SummaryDao {
    //tip: the attribute "id" is automatically set (but not realized,so right you need to set it manually),the other must be set manually
    public boolean addSummary(Summary po);

    //tip: get all the Summary of a project
    public List findSummary(Project po);

    //tip: if update succeed ,it will return true .And all the attributes must be set
    public boolean update(Summary po);

    //tip: if delete succeed ,it will return true
    public boolean delete(Summary po);
}
