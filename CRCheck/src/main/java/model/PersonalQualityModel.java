package model;

/**
 * Created by lvdechao on 2016/7/22.
 */
public class PersonalQualityModel {

    private String userID;
    private int unApproveDefectNum;
    private int correctDefectNum;
    private int errorDefectNum;
    private double efficiency;

    public PersonalQualityModel() {
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public int getUnApproveDefectNum() {
        return unApproveDefectNum;
    }

    public void setUnApproveDefectNum(int unApproveDefectNum) {
        this.unApproveDefectNum = unApproveDefectNum;
    }

    public int getCorrectDefectNum() {
        return correctDefectNum;
    }

    public void setCorrectDefectNum(int correctDefectNum) {
        this.correctDefectNum = correctDefectNum;
    }

    public int getErrorDefectNum() {
        return errorDefectNum;
    }

    public void setErrorDefectNum(int errorDefectNum) {
        this.errorDefectNum = errorDefectNum;
    }

    public double getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(double efficiency) {
        this.efficiency = efficiency;
    }
}
