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
        invitement.setProjectId("23");
        invitement.setUserId("shachao");
        invitement.setId(1);
        invitement.setState("NOTACCEPT");
        invitementDao.addInvitement(invitement);
    }
}
