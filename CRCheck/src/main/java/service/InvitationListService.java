package service;

import enums.UniversalState;
import model.InvitationMessage;

import java.util.ArrayList;

/**
 * Created by lvdechao on 2016/7/10.
 */
public interface InvitationListService {

    //返回指定项目邀请名单（包含状态）
    public ArrayList<InvitationMessage> getInvitationList(String projectID);

    //存储项目的邀请名单
    public UniversalState saveInvitationList(ArrayList<InvitationMessage> list);

    //改变用户是否接收邀请的状态
    public UniversalState changeInvitationState(String userID,String projectID);

}
