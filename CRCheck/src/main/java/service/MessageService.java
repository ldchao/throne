package service;

import enums.MessageState;
import enums.UniversalState;
import model.InvitationMessage;
import model.ProjectModel;

import java.util.ArrayList;

/**
 * Created by lvdechao on 2016/7/11.
 */
public interface MessageService {

    //todo 项目创建时发布消息通知
    public UniversalState setIssueMessage(ProjectModel projectModel);

    // TODO: 更改消息处理状态
    public UniversalState changeMessageState(String messageID, MessageState messageState);

    // TODO: 2016/7/11 删除消息
    public UniversalState deleteMessage(String messageID);

    // TODO: 2016/7/12 一键清空消息
    public UniversalState deleteAllMessage(String userID);

    // TODO: 2016/7/12 查看所有消息
    public ArrayList<InvitationMessage> checkAllMessage(String userID);
}
