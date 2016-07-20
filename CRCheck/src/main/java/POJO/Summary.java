package POJO;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by mm on 2016/7/20.
 */
@Entity
public class Summary {
    private Integer id;
    private Integer projectId;
    private Integer newPersonalReviewId;
    private Integer oldPersonalReviewId;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
    @Column(name = "newPersonalReviewId", nullable = false)
    public Integer getNewPersonalReviewId() {
        return newPersonalReviewId;
    }

    public void setNewPersonalReviewId(Integer newPersonalReviewId) {
        this.newPersonalReviewId = newPersonalReviewId;
    }

    @Basic
    @Column(name = "oldPersonalReviewId", nullable = false)
    public Integer getOldPersonalReviewId() {
        return oldPersonalReviewId;
    }

    public void setOldPersonalReviewId(Integer oldPersonalReviewId) {
        this.oldPersonalReviewId = oldPersonalReviewId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Summary summary = (Summary) o;

        if (id != null ? !id.equals(summary.id) : summary.id != null) return false;
        if (projectId != null ? !projectId.equals(summary.projectId) : summary.projectId != null) return false;
        if (newPersonalReviewId != null ? !newPersonalReviewId.equals(summary.newPersonalReviewId) : summary.newPersonalReviewId != null)
            return false;
        if (oldPersonalReviewId != null ? !oldPersonalReviewId.equals(summary.oldPersonalReviewId) : summary.oldPersonalReviewId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (projectId != null ? projectId.hashCode() : 0);
        result = 31 * result + (newPersonalReviewId != null ? newPersonalReviewId.hashCode() : 0);
        result = 31 * result + (oldPersonalReviewId != null ? oldPersonalReviewId.hashCode() : 0);
        return result;
    }
}
