package controller;

import POJO.Message;
import model.InvitationMessage;
import model.UserModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.MessageService;
import serviceImpl.MessageServiceImpl;

import javax.servlet.http.HttpServletRequest;
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
        //TODO
        return messageService.checkAllMessage(userId);

    }
    //查看全部历史消息
    @RequestMapping(value = "/AllOldMessage", method = RequestMethod.GET)
    @ResponseBody
    public List<InvitationMessage> getAllOldMessage(String userId) {

        MessageService messageService = new MessageServiceImpl();
        //TODO
        return messageService.checkAllMessage(userId);

    }
}
