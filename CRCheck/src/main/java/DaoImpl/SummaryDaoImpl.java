package DaoImpl;

import Connection.connection;
import Dao.SummaryDao;
import POJO.Summary;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by mm on 2016/7/11.
 */
public class SummaryDaoImpl implements SummaryDao {
    public boolean addSummary(Summary summary) {
        Session session= connection.getSession();
//        try {
//
//        }
        return false;
    }

    public List findSummary(String projectId) {
        return null;
    }

    public boolean update(Summary summary) {
        return false;
    }

    public boolean delete(String id) {
        return false;
    }
}
