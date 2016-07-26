package service;

import model.PersonalQualityModel;
import model.ProjectQualityModel;
import model.ScatterDiagramModel;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by lvdechao on 2016/7/22.
 */
public interface ProjectFeedBackService {

    //返回散点图所需要的数据
   public ScatterDiagramModel getScatterDiagramData(int projectID);

    //返回个人评审质量统计图所需数据
    public ArrayList<PersonalQualityModel> getStatisticChartData(int projectID);

    //返回质量变化折线图所需数据
    public ArrayList<ProjectQualityModel> getLineChartData(int projectID);

}
