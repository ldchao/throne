package model;

/**
 * Created by lvdechao on 2016/7/20.
 */
public class ProjectQualityModel {

    private Integer id;
    private String userId;
    private Integer projectId;
    private String endTime;
    private String description;
    private Double method1;//Hchao
    private Double method2;//Tchao

    public ProjectQualityModel() {
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

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getMethod1() {
        return method1;
    }

    public void setMethod1(Double method1) {
        this.method1 = method1;
    }

    public Double getMethod2() {
        return method2;
    }

    public void setMethod2(Double method2) {
        this.method2 = method2;
    }
}
