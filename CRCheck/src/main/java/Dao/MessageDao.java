package Dao;

/**
 * Created by dlydd on 2016/7/11.
 */
public interface MessageDao {
    //id是自增的，不过目前没增加，要手动输入
    public boolean addMessage(Message message);

    public boolean deleteMessage(String id);

    public boolean updateMessage(Message message);

    public Message findMessage(String uid);
}
