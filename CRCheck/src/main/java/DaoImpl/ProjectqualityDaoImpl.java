package DaoImpl;

import Connection.connection;
import Dao.ProjectqualityDao;
import POJO.Projectquality;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by mm on 2016/7/20.
 */
public class ProjectqualityDaoImpl implements ProjectqualityDao {
    public boolean addProjectquality(Projectquality po) {
        Session session= connection.getSession();
        try {
            if (findPQById(po.getId())==null){
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

    public Projectquality findPQById(int id){
        Session session = connection.getSession();
        try {
            String hql = "from Projectquality p where p.id=" + id;
            Query query = session.createQuery(hql);
            List list = query.list();
            session.close();
            if(list.size()==0){
                return null;
            }
               Projectquality projectquality = (Projectquality)list.get(0);
                return projectquality;


        } catch (Exception e) {
            e.printStackTrace();
            connection.closeSession(session);
            return null;
        }
    }

    public List getProjectqualitys(Projectquality po) {
        Session session = connection.getSession();
        try{
            String hql = "from Projectquality p where p.projectId="+po.getProjectId();
            Query query = session.createQuery(hql);
            List list = query.list();
            session.close();
            Collections.sort(list, new Comparator<Projectquality>(){

                        public int compare(Projectquality o1, Projectquality o2) {

                                  //按照学生的年龄进行升序排列
                                if(o1.getEndTime().compareTo( o2.getEndTime())>0){
                                          return 1;
                                      }
                                   if(o1.getEndTime()== o2.getEndTime()){
                                           return 0;
                                       }
                                   return -1;
                              }
                      });

            return list;
        }catch(Exception e){
            e.printStackTrace();
            connection.closeSession(session);
            return null;
        }

    }

    public double getPredictedDefect(Projectquality po, String method) {
        Session session = connection.getSession();
        try{
            String hql = "from Projectquality p where p.projectId="+po.getProjectId();
            Query query = session.createQuery(hql);
            List list = query.list();
            session.close();
            List<String> tList = new ArrayList<String>();
            for(int i=0;i<list.size();i++){
                 Projectquality projectquality = (Projectquality) list.get(i);
                 tList.add(projectquality.getEndTime());
            }
            int mymax=0;
            for(int i=0;i<list.size();i++){
                if(tList.get(i).compareTo(tList.get(mymax))>0){
                    mymax=i;
                }
            }
            Projectquality projectquality=(Projectquality) list.get(mymax);
            if(method=="Method1"){
                return projectquality.getMethod1();
            }else{
                return projectquality.getMethod2();
            }

        }catch(Exception e){
            return 0.0;
        }
    }
}
