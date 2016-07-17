package DaoTest;

import DaoImpl.MessageDaoImpl;
import POJO.Message;

/**
 * Created by dlydd on 2016/7/12.
 */
public class TestMessage {
    public static void main(String args[]){
        MessageDaoImpl messageDao = new MessageDaoImpl();
        Message message = new Message();
        message.setId(41);
        message.setState("Agree");
       message.setUserId("dd14");
        message.setContent("get check");
        message.setProjectId(13);
        message.setSendOrReceive("send");
      //  messageDao.addMessage(message);
        messageDao.deleteAllMessage("dd14");
//        message=messageDao.findMessage("shachao");
//       System.out.print(message.getId());
       // messageDao.deleteMessage("shachao");
        //message.setState("Handle");
       // messageDao.updateMessageState(message);
       //System.out.print( messageDao.getMessageNum("123"));
    }
}
