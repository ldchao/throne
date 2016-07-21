package Dao;

import POJO.Commitrecord;

import java.util.List;

/**
 * Created by mm on 2016/7/20.
 */
public interface CommitrecordDao {
    //iterator two
    //need all the attributes
    public boolean addCommitrecord(Commitrecord po);

    //iterator two
    //need the "userId",it will return a list of "reviewType" which doesn't contain any repetitive element
    public List findreviewType(Commitrecord po);

    //iterator two
    //need the "userId" ,the "startTime" and the "endTime",it will return a list of "Commitrecord"
    public List findCommitrecord(Commitrecord po,String startTime,String endTime);

    //iterator two
    //need the "userId" ,it will return the time the "User" spent
    public int getReviewTime(Commitrecord po);

    //iterator two
    //need the "userId" and "reviewType" ,it will return the amount of the code lines the "User" reviewed
    public int getReviewTypeCodeLine(Commitrecord po);

    //iterator two
    //need the "projectId" and "userId",it will return the amount of the code lines of the "Project" the "User" reviewed
    public int getReviewCodeLine(Commitrecord po);

    //iterator two
    //need the "projectId" and "userId",it will return the amount of the time of "Project" the "User" spent
    public int getReviewTimeOfProject(Commitrecord po);
}
