package Dao;

import POJO.Invitement;

/**
 * Created by dlydd on 2016/7/11.
 */
public interface InvitementDao {
    public boolean addInvitement(Invitement invitement);

    public boolean deleteInvitementbyProject(String pid);

    public boolean deleteInvitementbyUser(String uid);

    public boolean deleteInvitement(String pid, String uid);

    public boolean updateInvitement(Invitement invitemnent);

    public Invitement findInvitement(String pid, String uid);
}