package serviceImpl;

import Dao.AttendanceDao;
import Dao.CreateIdDao;
import Dao.MessageDao;
import DaoImpl.AttendanceDaoImpl;
import DaoImpl.CreateIdDaoImpl;
import DaoImpl.MessageDaoImpl;
import POJO.Attendance;
import POJO.Message;
import enums.FinishState;
import enums.MessageState;
import enums.UniversalState;
import model.InvitationMessage;
import model.ProjectModel;
import service.InvitationListService;
import service.MessageService;

import java.util.ArrayList;

/**
 * Created by zs on 2016/7/11.
 */
public class MessageServiceImpl implements MessageService {
    public UniversalState setIssueMessage(ProjectModel projectModel) {

        MessageDao messageDao=new MessageDaoImpl();
        CreateIdDao createIdDao=new CreateIdDaoImpl();
        String constantMessage=projectModel.getName()+"&"+projectModel.getType()
                +"&"+projectModel.getDiscription()+"&"+projectModel.getUserID()
                +"&"+projectModel.getStartDate()+"&"+projectModel.getEndDate();
//        String constantMessage="";
//        constantMessage+=projectModel.getUserID()+"邀请您于"+projectModel.getStartDate()+"至"
//                +projectModel.getEndDate()+"参加他发起的"+"“"+projectModel.getName()
//                +"”项目的评审，请查收消息。";
        boolean result=true;
        for (InvitationMessage invitationMessage:projectModel.getInvitationList()) {
//              String invitement="尊敬的用户您好，"+constantMessage;
            Integer id=createIdDao.CreateIntId("Message");
            Message message=new Message();
            message.setId(id);
            message.setContent(constantMessage);
            message.setProjectId(projectModel.getProjectID());
            message.setUserId(invitationMessage.getUserID());
            message.setState(MessageState.NotHandle.toString());
            message.setSendOrReceive("receive");
            result=result&messageDao.addMessage(message);
        }
        return result?UniversalState.SUCCESS:UniversalState.FAIL;
    }

    public UniversalState changeMessageState(int messageID, MessageState messageState) {

        boolean result=true;

        MessageDao messageDao=new MessageDaoImpl();
        Message message=new Message();
        message.setId(messageID);
        message.setState(messageState.toString());
        result=result&messageDao.updateMessageState(message);
        message=messageDao.findMessage(messageID);

        if(messageState==MessageState.Agree||messageState==MessageState.Refuse) {
            InvitationListService invitationListService = new InvitationListServiceImpl();
            invitationListService.changeInvitationState(message.getUserId(), message.getProjectId(), messageState);
        }
        if(messageState==MessageState.Agree ){
            CreateIdDao createIdDao = new CreateIdDaoImpl();
            AttendanceDao attendanceDao = new AttendanceDaoImpl();
            Attendance attendance = new Attendance();
            attendance.setId(createIdDao.CreateIntId("Attendance"));
            attendance.setProjectId(message.getProjectId());
            attendance.setUserId(message.getUserId());
            attendance.setState(FinishState.NotDone.toString());
            attendance.setQualityReview("");
            result = result & attendanceDao.addAttendance(attendance);
        }
        return result?UniversalState.SUCCESS:UniversalState.FAIL;
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
            invitation.setContent(message.getContent());
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
            if(MessageState.valueOf(message.getState())==MessageState.Delete)
                continue;
            InvitationMessage invitation=new InvitationMessage();
            invitation.setMessageID(message.getId());
            invitation.setUserID(message.getUserId());
            invitation.setContent(message.getContent());
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
            invitation.setContent(message.getContent());
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
