package POJO;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by mm on 2016/7/20.
 */
@Entity
public class Projectquality {
    private Integer id;
    private String userId;
    private Integer projectId;
    private String endTime;
    private String description;
    private Double method1;
    private Double method2;

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
    @Column(name = "endTime", nullable = false, length = 20)
    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
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
    @Column(name = "method1", nullable = false, precision = 0)
    public Double getMethod1() {
        return method1;
    }

    public void setMethod1(Double method1) {
        this.method1 = method1;
    }

    @Basic
    @Column(name = "method2", nullable = false, precision = 0)
    public Double getMethod2() {
        return method2;
    }

    public void setMethod2(Double method2) {
        this.method2 = method2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Projectquality that = (Projectquality) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (projectId != null ? !projectId.equals(that.projectId) : that.projectId != null) return false;
        if (endTime != null ? !endTime.equals(that.endTime) : that.endTime != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (method1 != null ? !method1.equals(that.method1) : that.method1 != null) return false;
        if (method2 != null ? !method2.equals(that.method2) : that.method2 != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (projectId != null ? projectId.hashCode() : 0);
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (method1 != null ? method1.hashCode() : 0);
        result = 31 * result + (method2 != null ? method2.hashCode() : 0);
        return result;
    }
}
