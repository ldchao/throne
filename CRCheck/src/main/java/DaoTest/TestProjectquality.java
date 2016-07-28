package DaoTest;

import Dao.ProjectqualityDao;
import DaoImpl.ProjectqualityDaoImpl;
import POJO.Projectquality;

/**
 * Created by dlydd on 2016/7/27.
 */
public class TestProjectquality {
    public static void main(String args[]){
        ProjectqualityDao projectqualityDao = new ProjectqualityDaoImpl();
        Projectquality projectquality = new Projectquality();
        projectquality.setDescription("good");
        projectquality.setEndTime("10:30");
        projectquality.setId(4);
        projectquality.setMethod1(1.0);
        projectquality.setMethod2(2.0);
        projectquality.setProjectId(2);
        projectquality.setUserId("asdf");
        projectqualityDao.addProjectquality(projectquality);
    }
}
