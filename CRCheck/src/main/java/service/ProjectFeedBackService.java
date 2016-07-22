package service;

import model.ScatterDiagramModel;

/**
 * Created by lvdechao on 2016/7/22.
 */
public interface ProjectFeedBackService {

    //返回散点图所需要的数据
   public ScatterDiagramModel getScatterDiagramData(int projectID);

}
