package POJO;

import enums.Power;
import enums.ProjectState;

import java.util.Date;

/**
 * Created by mm on 2016/7/10.
 */
public class Project {
    private String managerId;
    private String id;
    private String name;
    private String description;
    private String projectState;
    private String power;
    private Date starTime;
    private String endTime;
    private String codePath;
    private String reviewResultPath;
    private String qualityReviewResult;

    public Project() {
    }

}
