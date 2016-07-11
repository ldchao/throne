package serviceImpl;

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





        return null;
    }

    public UniversalState saveInvitationList(ArrayList<InvitationMessage> list) {




        return null;
    }

    public UniversalState changeInvitationState(String userID, String projectID, MessageState state) {
        return null;
    }
}
