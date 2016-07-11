package POJO;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by mm on 2016/7/11.
 */
@Entity
public class Personalreview {
    private Integer id;
    private String userId;
    private String projectId;
    private String commitTime;
    private String location;//the location of the error
    private String type;//the type of the error
    private String description;//the detail of the error
    private String state;//two state:NOT_ENTIRELY_COMMIT, ENTIRELY_COMMIT
    private String result;//NOT_ADMITED,ADMITTED

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
    @Column(name = "projectId", nullable = false, length = 20)
    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
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
    @Column(name = "location", nullable = false, length = 255)
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Basic
    @Column(name = "type", nullable = false, length = 255)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "description", nullable = false, length = 255)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "state", nullable = false, length = 20)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Basic
    @Column(name = "result", nullable = false, length = 20)
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Personalreview that = (Personalreview) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (projectId != null ? !projectId.equals(that.projectId) : that.projectId != null) return false;
        if (commitTime != null ? !commitTime.equals(that.commitTime) : that.commitTime != null) return false;
        if (location != null ? !location.equals(that.location) : that.location != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;
        if (result != null ? !result.equals(that.result) : that.result != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result1 = id != null ? id.hashCode() : 0;
        result1 = 31 * result1 + (userId != null ? userId.hashCode() : 0);
        result1 = 31 * result1 + (projectId != null ? projectId.hashCode() : 0);
        result1 = 31 * result1 + (commitTime != null ? commitTime.hashCode() : 0);
        result1 = 31 * result1 + (location != null ? location.hashCode() : 0);
        result1 = 31 * result1 + (type != null ? type.hashCode() : 0);
        result1 = 31 * result1 + (description != null ? description.hashCode() : 0);
        result1 = 31 * result1 + (state != null ? state.hashCode() : 0);
        result1 = 31 * result1 + (result != null ? result.hashCode() : 0);
        return result1;
    }
}
