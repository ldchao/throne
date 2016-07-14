package serviceImpl;

import Dao.CreateIdDao;
import Dao.MessageDao;
import DaoImpl.CreateIdDaoImpl;
import DaoImpl.MessageDaoImpl;
import POJO.Message;
import enums.MessageState;
import enums.UniversalState;
import model.InvitationMessage;
import model.ProjectModel;
import service.MessageService;

import java.util.ArrayList;

/**
 * Created by zs on 2016/7/11.
 */
public class MessageServiceImpl implements MessageService {
    public UniversalState setIssueMessage(ProjectModel projectModel) {

        MessageDao messageDao=new MessageDaoImpl();
        CreateIdDao createIdDao=new CreateIdDaoImpl();
        String constantMessage="";
        constantMessage+=projectModel.getUserID()+"邀请您于"+projectModel.getStartDate()+"至"
                +projectModel.getEndDate()+"参加他发起的"+"“"+projectModel.getName()
                +"”项目的评审，请查收消息。";
        boolean result=true;
        for (InvitationMessage invitationMessage:projectModel.getInvitationList()) {
              String invitement="尊敬的用户您好，"+constantMessage;
            Integer id=createIdDao.CreateIntId("Message");
            Message message=new Message();
            message.setId(id);
            message.setContent(invitement);
            message.setProjectId(projectModel.getProjectID());
            message.setUserId(invitationMessage.getUserID());
            message.setState(MessageState.NotHandle.toString());
            message.setSendOrReceive("receive");
            result=result&messageDao.addMessage(message);
        }
        return result?UniversalState.SUCCESS:UniversalState.FAIL;
    }

    public UniversalState changeMessageState(int messageID, MessageState messageState) {

        MessageDao messageDao=new MessageDaoImpl();
        Message message=new Message();
        message.setId(messageID);
        message.setState(messageState.toString());
        return messageDao.updateMessage(message)?UniversalState.SUCCESS:UniversalState.FAIL;
    }

    public UniversalState deleteMessage(int messageID) {
        MessageDao messageDao=new MessageDaoImpl();
        return messageDao.deleteMessage(messageID)?UniversalState.SUCCESS:UniversalState.FAIL;
    }

    public UniversalState deleteAllMessage(String userID) {
        MessageDao messageDao=new MessageDaoImpl();
        return messageDao.deleteAllMessage(userID)?UniversalState.SUCCESS:UniversalState.FAIL;
    }

    public ArrayList<InvitationMessage> checkAllMessage(String userID) {
        ArrayList<InvitationMessage> messages=new ArrayList<InvitationMessage>();
        MessageDao messageDao=new MessageDaoImpl();
        ArrayList<Message> list=messageDao.findAllMessage(userID);
        for (Message message:list) {
            InvitationMessage invitation=new InvitationMessage();
            invitation.setMessageID(message.getId());
            invitation.setUserID(message.getUserId());
            invitation.setProjectID(message.getProjectId());
            invitation.setAccepting_state(MessageState.valueOf(message.getState()));
            messages.add(invitation);
        }
        return messages;
    }

    public ArrayList<InvitationMessage> checkhandledMessage(String userID) {
        ArrayList<InvitationMessage> messages=new ArrayList<InvitationMessage>();
        MessageDao messageDao=new MessageDaoImpl();
        ArrayList<Message> list=messageDao.findAllMessage(userID);
        for (Message message:list) {
            if(MessageState.valueOf(message.getState())==MessageState.NotHandle)
                continue;
            InvitationMessage invitation=new InvitationMessage();
            invitation.setMessageID(message.getId());
            invitation.setUserID(message.getUserId());
            invitation.setProjectID(message.getProjectId());
            invitation.setAccepting_state(MessageState.valueOf(message.getState()));
            messages.add(invitation);
        }
        return messages;
    }

    public ArrayList<InvitationMessage> checkUnhandledMessage(String userID) {
        ArrayList<InvitationMessage> messages=new ArrayList<InvitationMessage>();
        MessageDao messageDao=new MessageDaoImpl();
        ArrayList<Message> list=messageDao.findAllMessage(userID);
        for (Message message:list) {
            if(MessageState.valueOf(message.getState())!=MessageState.NotHandle)
                continue;
            InvitationMessage invitation=new InvitationMessage();
            invitation.setMessageID(message.getId());
            invitation.setUserID(message.getUserId());
            invitation.setProjectID(message.getProjectId());
            invitation.setAccepting_state(MessageState.valueOf(message.getState()));
            messages.add(invitation);
        }
        return messages;
    }

    public int checkMessageCount(String userID) {
        MessageDao messageDao=new MessageDaoImpl();
        return messageDao.getMessageNum(userID);
    }
}
