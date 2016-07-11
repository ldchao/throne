package serviceImpl;

import enums.MessageState;
import enums.UniversalState;
import model.ProjectModel;
import service.MessageService;

/**
 * Created by zs on 2016/7/11.
 */
public class MessageServiceImpl implements MessageService {
    public UniversalState setIssueMessage(ProjectModel projectModel) {
        return null;
    }

    public UniversalState changeMessageState(String messageID, MessageState messageState) {
        return null;
    }

    public UniversalState deleteMessageState(String messageID) {
        return null;
    }
}
