package Dao;

import POJO.Invitement;

import java.util.ArrayList;

/**
 * Created by dlydd on 2016/7/11.
 */
public interface InvitementDao {
    //id是自增的，不过目前没增加，要手动输入
    public boolean addInvitement(Invitement invitement);

    public boolean deleteInvitementbyProject(int pid);

    public boolean deleteInvitementbyUser(String uid);

    public boolean deleteInvitement(int pid, String uid);

    //need the "projectId" and "userId" to change the "state",update a list of "Invitement" one time
    public boolean updateInvitement(Invitement invitemnent);

    public Invitement findInvitement(int pid, String uid);

    public ArrayList<Invitement> findAllInvitement(int pid);
}
