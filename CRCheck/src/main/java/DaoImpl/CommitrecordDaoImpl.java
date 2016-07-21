package DaoImpl;

import Connection.connection;
import Dao.CommitrecordDao;
import POJO.Commitrecord;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mm on 2016/7/20.
 */
public class CommitrecordDaoImpl implements CommitrecordDao {
    public boolean addCommitrecord(Commitrecord po) {
        Session session= connection.getSession();
        try {
            if (findCRById(po.getId())==null){
                session.save(po);
                Transaction transaction=session.beginTransaction();
                transaction.commit();
                connection.closeSession(session);
                return true;
            }else {
                connection.closeSession(session);
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            connection.closeSession(session);
            return false;
        }
    }

    public Commitrecord findCRById(int id){
        Session session = connection.getSession();
        try {
            String hql = "from Commitrecord c where c.id=" + id;
            Query query = session.createQuery(hql);
            List list = query.list();
            session.close();
            if (list.size() == 0) {
                return null;
            } else {
                Commitrecord commitrecord = (Commitrecord) list.get(0);
                return commitrecord;
            }

        } catch (Exception e) {
            e.printStackTrace();
            connection.closeSession(session);
            return null;
        }
    }

    public List findreviewType(Commitrecord po) {
        Session session = connection.getSession();
        try {
            String hql = "from Commitrecord c where c.userId='" +po.getUserId()+"'";
            Query query = session.createQuery(hql);
            List list = query.list();
            session.close();
            if (list.size() == 0) {
                return null;
            } else {
               ArrayList<String> tList = new ArrayList<String>();
                for(int i=0;i<tList.size();i++){
                    Commitrecord commitrecord = (Commitrecord) list.get(i);
                    tList.add(commitrecord.getReviewType());
                }
                for ( int i = 0 ; i < tList.size() - 1 ; i ++ ) {
                    for ( int j = tList.size() - 1 ; j > i; j -- ) {
                        if (tList.get(j).equals(tList.get(i))) {
                            tList.remove(j);
                        }
                    }
                }
                return tList;
            }

        } catch (Exception e) {
            e.printStackTrace();
            connection.closeSession(session);
            return null;
        }
    }

    public List findCommitrecord(Commitrecord po, String startTime, String endTime) {
        Session session = connection.getSession();
        try {
            String hql = "from Commitrecord c where c.userId='" +po.getUserId()+"'";
            Query query = session.createQuery(hql);
            List list = query.list();
            session.close();
            if (list.size() == 0) {
                return null;
            } else {
                for(int i=0;i<list.size();i++) {
                    Commitrecord commitrecord = (Commitrecord) list.get(i);
                    if (commitrecord.getCommitTime().compareTo(startTime) < 0 ||
                            commitrecord.getCommitTime().compareTo(endTime) > 0) {
                        list.remove(i);
                    }
                }
                        return list;
                }

        } catch (Exception e) {
            e.printStackTrace();
            connection.closeSession(session);
            return null;
        }
    }

    public int getReviewTime(Commitrecord po) {
        Session session = connection.getSession();
        try {
            String hql = "from Commitrecord c where c.userId='" +po.getUserId()+"'";
            Query query = session.createQuery(hql);
            List list = query.list();
            session.close();
            if (list.size() == 0) {
                return 0;
            } else {
               int total=0;
                for(int i=0;i<list.size();i++){
                    Commitrecord commitrecord = (Commitrecord) list.get(i);
                    
                }
                return total;
            }

        } catch (Exception e) {
            e.printStackTrace();
            connection.closeSession(session);
            return 0;
        }

    }

    public int getReviewTypeCodeLine(Commitrecord po) {
        return 0;
    }

    public int getReviewCodeLine(Commitrecord po) {
        return 0;
    }

    public int getReviewTimeOfProject(Commitrecord po) {
        return 0;
    }
}
