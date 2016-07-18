package controller;

import enums.MessageState;
import enums.UniversalState;
import model.InvitationMessage;
import model.UserModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.MessageService;
import serviceImpl.MessageServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lvdechao on 2016/7/13.
 */

@Controller
public class MessageController {


    // TODO: 2016/7/13 每5秒钟调用，若收到新消息，返回Changed，否则返回Unchanged
    @RequestMapping(value = "/MessageRemind", method = RequestMethod.GET)
    @ResponseBody
    public String messageRemind(HttpServletRequest request) {

        if (request.getSession().getAttribute("User") == null)
            return "Unchanged";

        UserModel userModel = (UserModel) request.getSession().getAttribute("User");
        int oldNum = userModel.getMessageNum();
        MessageService messageService = new MessageServiceImpl();
        int newNum = messageService.checkMessageCount(userModel.getId());
        if (newNum > oldNum) {
            userModel.setMessageNum(newNum);
            return "Changed";
        }
        return "Unchanged";
    }

    //查看全部新消息
    @RequestMapping(value = "/AllNewMessage", method = RequestMethod.GET)
    @ResponseBody
    public List<InvitationMessage> getAllNewMessage(String userId) {
        MessageService messageService = new MessageServiceImpl();
        return messageService.checkUnhandledMessage(userId);
    }

    //查看全部历史消息
    @RequestMapping(value = "/AllOldMessage", method = RequestMethod.GET)
    @ResponseBody
    public List<InvitationMessage> getAllOldMessage(String userId) {
        MessageService messageService = new MessageServiceImpl();
        return messageService.checkhandledMessage(userId);
    }

    //接受邀请
    @RequestMapping(value = "/ApproveMessage", method = RequestMethod.GET)
    @ResponseBody
    public String approveMessage(HttpServletRequest request, int messageId) {
        MessageService messageService = new MessageServiceImpl();
        UniversalState state = messageService.changeMessageState(messageId, MessageState.Agree);
        if (state == UniversalState.SUCCESS) {
            UserModel user = (UserModel) request.getSession().getAttribute("User");
            user.deleteMessageNum();
            return "SUCCESS";
        }
        return "FAIL";
    }

    //拒绝邀请
    @RequestMapping(value = "/RefuseMessage", method = RequestMethod.GET)
    @ResponseBody
    public String refuseMessage(HttpServletRequest request, int messageId) {
        MessageService messageService = new MessageServiceImpl();
        UniversalState state = messageService.changeMessageState(messageId, MessageState.Refuse);
        if (state == UniversalState.SUCCESS) {
            UserModel user = (UserModel) request.getSession().getAttribute("User");
            user.deleteMessageNum();
            return "SUCCESS";
        }
        return "FAIL";
    }

    //忽略消息
    @RequestMapping(value = "/IgnoreMessage", method = RequestMethod.GET)
    @ResponseBody
    public String ignoreMessage(HttpServletRequest request, int messageId) {
        MessageService messageService = new MessageServiceImpl();
        UniversalState state = messageService.changeMessageState(messageId, MessageState.Ignore);
        if (state == UniversalState.SUCCESS) {
            UserModel user = (UserModel) request.getSession().getAttribute("User");
            user.deleteMessageNum();
            return "SUCCESS";
        }
        return "FAIL";
    }

    //删除消息
    @RequestMapping(value = "/DeleteMessage", method = RequestMethod.GET)
    @ResponseBody
    public String deleteMessage(HttpServletRequest request, int messageId) {
        MessageService messageService = new MessageServiceImpl();
        UniversalState state = messageService.changeMessageState(messageId, MessageState.Delete);
        if (state == UniversalState.SUCCESS) {
            UserModel user = (UserModel) request.getSession().getAttribute("User");
            int newNum = messageService.checkMessageCount(user.getId());
            user.setMessageNum(newNum);
            return "SUCCESS";
        }
        return "FAIL";
    }

    //一键清空
    @RequestMapping(value = "/DeleteAllMessage", method = RequestMethod.GET)
    @ResponseBody
    public String deleteAllMessage(String userId) {
        MessageService messageService = new MessageServiceImpl();
        ArrayList<InvitationMessage> list = messageService.checkhandledMessage(userId);
        for(InvitationMessage message:list){
            UniversalState state = messageService.changeMessageState(message.getMessageID(), MessageState.Delete);
            if (state == UniversalState.FAIL) {
                return "FAIL";
            }
        }
        return "SUCCESS";
    }
}
