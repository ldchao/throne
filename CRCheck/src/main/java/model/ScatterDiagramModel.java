package model;

import POJO.User;

import java.util.ArrayList;

/**
 * Created by lvdechao on 2016/7/22.
 */
public class ScatterDiagramModel {

    private ArrayList<PersonalReviewRecord> defectList;
    private ArrayList<String> userList;
    private ArrayList<UserAndDefect> userAndDefectsList;

    public ScatterDiagramModel() {
    }

    public ArrayList<PersonalReviewRecord> getDefectList() {
        return defectList;
    }

    public void setDefectList(ArrayList<PersonalReviewRecord> defectList) {
        this.defectList = defectList;
    }

    public ArrayList<String> getUserList() {
        return userList;
    }

    public void setUserList(ArrayList<String> userList) {
        this.userList = userList;
    }

    public ArrayList<UserAndDefect> getUserAndDefectsList() {
        return userAndDefectsList;
    }

    public void setUserAndDefectsList(ArrayList<UserAndDefect> userAndDefectsList) {
        this.userAndDefectsList = userAndDefectsList;
    }
}
