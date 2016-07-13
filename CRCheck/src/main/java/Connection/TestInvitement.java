package Connection;

import DaoImpl.InvitementDaoImpl;
import POJO.Invitement;

/**
 * Created by dlydd on 2016/7/12.
 */
public class TestInvitement {
    public static void main(String args[]){
        InvitementDaoImpl invitementDao = new InvitementDaoImpl();
        Invitement invitement = new Invitement();
       //invitement.setProjectId("23");
       //invitement.setUserId("shachao");
        //invitement.setId(1);
        invitement.setState("NOTACCEPT");
        //invitement.setState("ACCEPT");
        //invitementDao.addInvitement(invitement);
      //  invitementDao.deleteInvitementbyUser("shachao");
//        invitement=invitementDao.findAllInvitement("23").get(0);
//        System.out.print(invitement.getId());
//        System.out.print(invitementDao.deleteInvitementbyProject("23"));
         // invitement=invitementDao.findInvitement("23","shachao");
        //System.out.print(invitement.getId());
        //invitementDao.deleteInvitement("23","shachao");
        invitementDao.updateInvitement(invitement);
    }
}
