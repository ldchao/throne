package serviceImpl;

import Dao.InvitementDao;
import DaoImpl.InvitementDaoImpl;
import POJO.Invitement;
import enums.MessageState;
import enums.UniversalState;
import model.InvitationMessage;
import service.InvitationListService;
import java.util.ArrayList;

/**
 * Created by zs on 2016/7/10.
 */
public class InvitationListServiceImpl implements InvitationListService{

    public ArrayList<InvitationMessage> getInvitationList(String projectID) {
        ArrayList<InvitationMessage> invitationList=new ArrayList<InvitationMessage>();
        InvitementDao invitmentDao=new InvitementDaoImpl();
        ArrayList<Invitement> list=invitmentDao.findAllInvitement(projectID);
        for (Invitement invitement:list) {
            InvitationMessage invitationMessage=new InvitationMessage();
            invitationMessage.setProjectID(invitement.getProjectId());
            invitationMessage.setUserID(invitement.getUserId());
            invitationMessage.setAccepting_state(MessageState.valueOf(invitement.getState()));
            invitationList.add(invitationMessage);
        }
        return invitationList;
    }

    public UniversalState saveInvitationList(ArrayList<InvitationMessage> list) {

        InvitementDao invitmentDao=new InvitementDaoImpl();
        boolean result=true;
        for (InvitationMessage invitationMesage:list ) {
            Invitement invitement=new Invitement();
            invitement.setProjectId(invitationMesage.getProjectID());
            invitement.setUserId(invitationMesage.getUserID());
            invitement.setState(invitationMesage.getAccepting_state().toString());
            result=result&invitmentDao.addInvitement(invitement);
        }
        return result?UniversalState.SUCCESS:UniversalState.FAIL;
    }

    public UniversalState changeInvitationState(String userID, String projectID, MessageState state) {
        InvitementDao invitmentDao=new InvitementDaoImpl();
        Invitement invitement=new Invitement();
        invitement.setProjectId(projectID);
        invitement.setUserId(userID);
        invitement.setState(state.toString());
        return invitmentDao.updateInvitement(invitement)?UniversalState.SUCCESS:UniversalState.FAIL;
    }
}
