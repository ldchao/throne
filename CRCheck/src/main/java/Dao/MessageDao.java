package Dao;

import POJO.Message;

/**
 * Created by dlydd on 2016/7/11.
 */
public interface MessageDao {
    public boolean addMessage(Message message);

    public boolean deleteMessage(String id);

    public boolean updateMessage(Message message);

    public boolean findMessage(String id);
}