package Dao;

import POJO.Message;

import java.util.ArrayList;

/**
 * Created by dlydd on 2016/7/11.
 */
public interface MessageDao {
    //id是自增的，不过目前没增加，要手动输入
    public boolean addMessage(Message message);

    public boolean deleteAllMessage(String uid);

    public boolean deleteMessage(int id);

    //need the "messageId" to change "state" of "Message",update a "Message" one time
    public boolean updateMessage(Message message);

    public ArrayList<Message> findAllMessage(String uid);

    public Message findMessage(int id);

    public int getMessageNum(String uid);

}
