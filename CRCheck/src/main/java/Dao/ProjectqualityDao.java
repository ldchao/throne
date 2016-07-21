package Dao;

import POJO.Projectquality;

import java.util.List;

/**
 * Created by mm on 2016/7/20.
 */
public interface ProjectqualityDao {
    //iterator two
    //need all the attributes
    public boolean addProjectquality(Projectquality po);

    //iterator two
    //need the "projectId",it will return a list of "Projectquality" ordered by time
    public List getProjectqualitys(Projectquality po);

    //iterator two
    //need the "projectId" and the name of method like "method1",it will return the predicted amount of the defects by using "method"
    public double getPredictedDefect(Projectquality po,String method);

}
