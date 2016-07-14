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

    //need the "id" of "Project",get all the "Summary" of a project
    public List findSummary(Project po);

    //need all the attributes,if update succeed ,it will return true .And all the attributes must be set
    public boolean update(Summary po);

    //need the "id" of "Summary",if delete succeed ,it will return true
    public boolean delete(Summary po);

    //need the "id" and "flag",just need the "id" and "flag" of "Summary"
    public boolean updateFlag(Summary po);

    //need the "projectId",return "Summary"
    public List getValidSummary(Summary po);

    //delete the inValid Summary,just need the "projectId" of "Summary"
    public boolean deleteInvalidSummary(Summary po);

    //need the "id" of "Summary",return "Summary"
    public Summary findSummaryById(Summary po);
}
