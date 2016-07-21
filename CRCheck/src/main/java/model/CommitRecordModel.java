package model;

/**
 * Created by lvdechao on 2016/7/20.
 */
public class CommitRecordModel {

    private Integer id;
    private String userId;
    private Integer projectId;
    private String commitTime;
    private Integer codeLine;
    private String reviewType;
    private Integer time;

    public CommitRecordModel() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getCommitTime() {
        return commitTime;
    }

    public void setCommitTime(String commitTime) {
        this.commitTime = commitTime;
    }

    public Integer getCodeLine() {
        return codeLine;
    }

    public void setCodeLine(Integer codeLine) {
        this.codeLine = codeLine;
    }

    public String getReviewType() {
        return reviewType;
    }

    public void setReviewType(String reviewType) {
        this.reviewType = reviewType;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }
}
