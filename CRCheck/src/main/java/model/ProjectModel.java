package model;

import enums.Language;
import enums.Power;
import enums.ProjectState;

import java.util.ArrayList;

/**
 * Created by lvdechao on 2016/7/10.
 */
public class ProjectModel {

    String UserID;
    String ProjectID;
    String name;
    Language kind;
    String discription;
    ProjectState state;
    Power power;
    String startDate;
    String endDate;
    String projectPath;
    String qualityFeedback;
    ArrayList<InvitationMessage> invitationList;


}
