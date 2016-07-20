package POJO;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by mm on 2016/7/20.
 */
@Entity
public class Commitrecord {
    private Integer id;
    private String userId;
    private Integer projectId;
    private String commitTime;
    private Integer codeLine;
    private String reviewType;
    private Integer time;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "userId", nullable = false, length = 20)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "projectId", nullable = false)
    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    @Basic
    @Column(name = "commitTime", nullable = false, length = 20)
    public String getCommitTime() {
        return commitTime;
    }

    public void setCommitTime(String commitTime) {
        this.commitTime = commitTime;
    }

    @Basic
    @Column(name = "codeLine", nullable = false)
    public Integer getCodeLine() {
        return codeLine;
    }

    public void setCodeLine(Integer codeLine) {
        this.codeLine = codeLine;
    }

    @Basic
    @Column(name = "ReviewType", nullable = false, length = 20)
    public String getReviewType() {
        return reviewType;
    }

    public void setReviewType(String reviewType) {
        this.reviewType = reviewType;
    }

    @Basic
    @Column(name = "time", nullable = false)
    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Commitrecord that = (Commitrecord) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (projectId != null ? !projectId.equals(that.projectId) : that.projectId != null) return false;
        if (commitTime != null ? !commitTime.equals(that.commitTime) : that.commitTime != null) return false;
        if (codeLine != null ? !codeLine.equals(that.codeLine) : that.codeLine != null) return false;
        if (reviewType != null ? !reviewType.equals(that.reviewType) : that.reviewType != null) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (projectId != null ? projectId.hashCode() : 0);
        result = 31 * result + (commitTime != null ? commitTime.hashCode() : 0);
        result = 31 * result + (codeLine != null ? codeLine.hashCode() : 0);
        result = 31 * result + (reviewType != null ? reviewType.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        return result;
    }
}
