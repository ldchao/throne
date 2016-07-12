package model;

import enums.Language;
import enums.Power;
import enums.ProjectState;

import java.util.ArrayList;

/**
 * Created by lvdechao on 2016/7/10.
 */
public class ProjectModel {

    private String UserID;
    //之后系统递增
    private String ProjectID;
    private String name;
    private Language type;
    private String discription;
    //一开始默认未开始
    private ProjectState state;
    private Power power;
    private String startDate;
    private String endDate;
    //后台安排
    private String projectPath;
    private String qualityFeedback;
    //本人是否参与评审
    private String attendReview;
    private ArrayList<InvitationMessage> invitationList;

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getProjectID() {
        return ProjectID;
    }

    public void setProjectID(String projectID) {
        ProjectID = projectID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Language getType() {
        return type;
    }

    public void setType(Language type) {
        this.type = type;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public ProjectState getState() {
        return state;
    }

    public void setState(ProjectState state) {
        this.state = state;
    }

    public Power getPower() {
        return power;
    }

    public void setPower(Power power) {
        this.power = power;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getProjectPath() {
        return projectPath;
    }

    public void setProjectPath(String projectPath) {
        this.projectPath = projectPath;
    }

    public String getQualityFeedback() {
        return qualityFeedback;
    }

    public void setQualityFeedback(String qualityFeedback) {
        this.qualityFeedback = qualityFeedback;
    }

    public ArrayList<InvitationMessage> getInvitationList() {
        return invitationList;
    }

    public void setInvitationList(ArrayList<InvitationMessage> invitationList) {
        this.invitationList = invitationList;
    }

    public String getAttendReview() {
        return attendReview;
    }

    public void setAttendReview(String attendReview) {
        this.attendReview = attendReview;
    }
}
