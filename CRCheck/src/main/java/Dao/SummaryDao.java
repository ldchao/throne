package Dao;

import POJO.Summary;

import java.util.List;

/**
 * Created by mm on 2016/7/11.
 */
public interface SummaryDao {
    public boolean addSummary(Summary summary);

    public List findSummary(String projectId);

    public boolean update(Summary summary);

    public boolean delete(String id);
}
