package POJO;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by mm on 2016/7/11.
 */
@Entity
public class Project {
    private String id;
    private String userId;
    private String type;
    private String description;
    private String projectState;
    private String power;
    private String startTime;
    private String endTime;
    private String codePath;
    private String attendReview;
    private String qualityReview;

    @Id
    @Column(name = "id", nullable = false, length = 20)
    public String getId() {
        return id;
    }

    public void setId(String id) {
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
    @Column(name = "type", nullable = false, length = 30)
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
    @Column(name = "projectState", nullable = false, length = 20)
    public String getProjectState() {
        return projectState;
    }

    public void setProjectState(String projectState) {
        this.projectState = projectState;
    }

    @Basic
    @Column(name = "power", nullable = false, length = 20)
    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    @Basic
    @Column(name = "startTime", nullable = false, length = 20)
    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    @Basic
    @Column(name = "endTime", nullable = false, length = 20)
    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Basic
    @Column(name = "codePath", nullable = false, length = 255)
    public String getCodePath() {
        return codePath;
    }

    public void setCodePath(String codePath) {
        this.codePath = codePath;
    }

    @Basic
    @Column(name = "attendReview", nullable = false, length = 20)
    public String getAttendReview() {
        return attendReview;
    }

    public void setAttendReview(String attendReview) {
        this.attendReview = attendReview;
    }

    @Basic
    @Column(name = "qualityReview", nullable = false, length = -1)
    public String getQualityReview() {
        return qualityReview;
    }

    public void setQualityReview(String qualityReview) {
        this.qualityReview = qualityReview;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Project project = (Project) o;

        if (id != null ? !id.equals(project.id) : project.id != null) return false;
        if (userId != null ? !userId.equals(project.userId) : project.userId != null) return false;
        if (type != null ? !type.equals(project.type) : project.type != null) return false;
        if (description != null ? !description.equals(project.description) : project.description != null) return false;
        if (projectState != null ? !projectState.equals(project.projectState) : project.projectState != null)
            return false;
        if (power != null ? !power.equals(project.power) : project.power != null) return false;
        if (startTime != null ? !startTime.equals(project.startTime) : project.startTime != null) return false;
        if (endTime != null ? !endTime.equals(project.endTime) : project.endTime != null) return false;
        if (codePath != null ? !codePath.equals(project.codePath) : project.codePath != null) return false;
        if (attendReview != null ? !attendReview.equals(project.attendReview) : project.attendReview != null)
            return false;
        if (qualityReview != null ? !qualityReview.equals(project.qualityReview) : project.qualityReview != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (projectState != null ? projectState.hashCode() : 0);
        result = 31 * result + (power != null ? power.hashCode() : 0);
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        result = 31 * result + (codePath != null ? codePath.hashCode() : 0);
        result = 31 * result + (attendReview != null ? attendReview.hashCode() : 0);
        result = 31 * result + (qualityReview != null ? qualityReview.hashCode() : 0);
        return result;
    }
}
