package POJO;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by mm on 2016/7/11.
 */
@Entity
public class Summary {
    private Integer id;
    private String projectId;
    private String location;
    private String type;
    private String description;
    private String combination;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
    @Column(name = "combination", nullable = false, length = 255)
    public String getCombination() {
        return combination;
    }

    public void setCombination(String combination) {
        this.combination = combination;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Summary summary = (Summary) o;

        if (id != null ? !id.equals(summary.id) : summary.id != null) return false;
        if (projectId != null ? !projectId.equals(summary.projectId) : summary.projectId != null) return false;
        if (location != null ? !location.equals(summary.location) : summary.location != null) return false;
        if (type != null ? !type.equals(summary.type) : summary.type != null) return false;
        if (description != null ? !description.equals(summary.description) : summary.description != null) return false;
        if (combination != null ? !combination.equals(summary.combination) : summary.combination != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (projectId != null ? projectId.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (combination != null ? combination.hashCode() : 0);
        return result;
    }
}
