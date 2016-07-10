package model;

import enums.MessageState;

/**
 * Created by lvdechao on 2016/7/10.
 */
public class InvitationMessage {
    private String userID;
    private String projectID;
    private MessageState accepting_state;

    public InvitationMessage() {
    }

    public MessageState getAccepting_state() {
        return accepting_state;
    }

    public void setAccepting_state(MessageState accepting_state) {
        this.accepting_state = accepting_state;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getProjectID() {
        return projectID;
    }

    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }
}
