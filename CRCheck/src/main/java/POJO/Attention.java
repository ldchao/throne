package POJO;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by mm on 2016/7/11.
 */
@Entity
public class Attention {
    private Integer id;
    private String state;
    private String qualityReviewPath;
    private String userId;
    private String projectId;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
    @Column(name = "qualityReviewPath", nullable = false, length = -1)
    public String getQualityReviewPath() {
        return qualityReviewPath;
    }

    public void setQualityReviewPath(String qualityReviewPath) {
        this.qualityReviewPath = qualityReviewPath;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Attention attention = (Attention) o;

        if (id != null ? !id.equals(attention.id) : attention.id != null) return false;
        if (state != null ? !state.equals(attention.state) : attention.state != null) return false;
        if (qualityReviewPath != null ? !qualityReviewPath.equals(attention.qualityReviewPath) : attention.qualityReviewPath != null)
            return false;
        if (userId != null ? !userId.equals(attention.userId) : attention.userId != null) return false;
        if (projectId != null ? !projectId.equals(attention.projectId) : attention.projectId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (qualityReviewPath != null ? qualityReviewPath.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (projectId != null ? projectId.hashCode() : 0);
        return result;
    }
}
