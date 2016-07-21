package model;

import enums.ApproveState;
import enums.FileType;

/**
 * Created by lvdechao on 2016/7/10.
 */
public class PersonalReviewRecord {
    private Integer id;  //记录id
    private String userId;
    private Integer projectId;
    private String commitTime;
    private String path;//所在路径名
    private String lineNum;//所在行数
    private String type;//错误类型
    private String description;//错误细节
    private String state;//若未完成提交："正常提交"，若已完成提交："后续提交":;若为合并项：“合并项”
    private ApproveState result;//审批结果(审批通过、未通过、未审批)

    private FileType fileType;//分为Dir,File或Code三种，Dir不出现
    private String pagesNum;//若为文档增加一个页数

    public PersonalReviewRecord() {
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getLineNum() {
        return lineNum;
    }

    public void setLineNum(String lineNum) {
        this.lineNum = lineNum;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public ApproveState getResult() {
        return result;
    }

    public void setResult(ApproveState result) {
        this.result = result;
    }

    public FileType getFileType() {
        return fileType;
    }

    public void setFileType(FileType fileType) {
        this.fileType = fileType;
    }

    public String getPagesNum() {
        return pagesNum;
    }

    public void setPagesNum(String pagesNum) {
        this.pagesNum = pagesNum;
    }
}
