package Dao;

import POJO.Contribution;

import java.util.List;

/**
 * Created by mm on 2016/7/28.
 */
public interface ContributionDao {
    //need all the attributes
    public boolean addContribution(Contribution po);

    //need the "userId",it will return the sum of "row"
    public double getSumOfRow(Contribution po);

    //need the "userId",it will return the sum of "time"
    public double getSumOfTime(Contribution po);

    //need the "userId",it will return the sum of "amount"
    public double getSumOfAmount(Contribution po);

    //need the "userId",it will return the average of "accuracy"
    public double getAverageOfAccuracy(Contribution po);

    //need the "userId",it will return the average of "coverage"
    public double getAverageOfCoverage(Contribution po);

    //need the "userId",it will return a list of "Contribution"
    public List getContributionsByUserId(Contribution po);
}
