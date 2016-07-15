package model;

import enums.MessageState;

/**
 * Created by lvdechao on 2016/7/10.
 */
public class InvitationMessage {
    private int MessageID;
    private String userID;
    private int projectID;
    private String content;//项目名、项目类型、项目描述、项目发起人、开始时间、结束时间

    private MessageState accepting_state;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public InvitationMessage() {
    }

    public int getMessageID() {
        return MessageID;
    }

    public void setMessageID(int messageID) {
        MessageID = messageID;
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

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }
}
