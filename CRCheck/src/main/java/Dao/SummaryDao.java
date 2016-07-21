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

    //need the "newPersonalReviewId" of "Summary",it will return a list of "Summary"
    public List getMergedSummary(Summary po);

    //need the "newPersonalReviewId" of "Summary",it will delete all the "Summary" which contains this "newPersonalReviewId"
    public boolean deleteNewPersonalReviewId(Summary po);

    //need the "newPersonalReviewId" and "oldPersonalReviewId"
    public boolean deleteSummary(Summary po);
}
