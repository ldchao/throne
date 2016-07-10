package POJO;

import enums.MessageState;

/**
 * Created by mm on 2016/7/10.
 */
public class Message {
    private String userId;
    private String id;
    private String content;
    private String messageState;

    public Message() {
    }

    public Message(String userId, String id, String content, String messageState) {
        this.userId = userId;
        this.id = id;
        this.content = content;
        this.messageState = messageState;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMessageState() {
        return messageState;
    }

    public void setMessageState(String messageState) {
        this.messageState = messageState;
    }
}

