package service;

import enums.UniversalState;
import model.InvitationMessage;

import java.util.ArrayList;

/**
 * Created by lvdechao on 2016/7/10.
 */
public interface InvitationListService {

    public ArrayList<InvitationMessage> getInvitationList(String projectID);

    public UniversalState saveInvitationList(ArrayList<InvitationMessage> list);

    public UniversalState changeInvitationState(String userID,String projectID);

}
