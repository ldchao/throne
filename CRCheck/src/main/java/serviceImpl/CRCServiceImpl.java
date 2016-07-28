package serviceImpl;

import Dao.*;
import DaoImpl.*;
import POJO.Personalreview;
import POJO.Projectquality;
import POJO.Summary;
import enums.CommitState;
import enums.UniversalState;
import model.CrcCalculation;
import model.ProjectQualityModel;
import service.CRCService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lvdechao on 2016/7/15.
 */
public class CRCServiceImpl implements CRCService {

    public int[][] getMatrix(int projectID) {

        int defectNum=0;
        int inspectorNum=0;
        int defectIndex=0;
        int inspectorIndex=0;
        int[][] result;

        AttendanceDao attendanceDao=new AttendanceDaoImpl();
        ArrayList<String> userList=attendanceDao.finduserDone(projectID);
        inspectorNum=userList.size();

        PersonalreviewDao personalreviewDao=new PersonalreviewDaoImpl();
        Personalreview po=new Personalreview();
        po.setProjectId(projectID);
        List<Personalreview> reviewList=personalreviewDao.findValidPersonalReview(po);
//        for (Personalreview personalreview:reviewList) {
//            if(userList.indexOf(personalreview.getUserId())==-1){
//                reviewList.remove(personalreview);
//            }
//        }
        defectNum=reviewList.size();

        result=new int[defectNum][inspectorNum];
        for (int i = 0; i < defectNum; i++) {
            for (int j = 0; j <inspectorNum ; j++) {
                result[i][j]=0;
            }
        }

        SummaryDao summaryDao=new SummaryDaoImpl();

        for (Personalreview personalreview:reviewList) {
            if(personalreview.getState().equals(CommitState.Combination.toString())){
                Summary spo=new Summary();
                spo.setNewPersonalReviewId(personalreview.getId());
                spo.setProjectId(projectID);
                List<Summary> summaryList=summaryDao.getMergedSummary(spo);
                for (Summary summary:summaryList) {
                    po.setId(summary.getOldPersonalReviewId());
                    Personalreview personal=personalreviewDao.findPersonalreviewById(po);
                    inspectorIndex=userList.indexOf(personal.getUserId());
                    result[defectIndex][inspectorIndex]=1;
                }
            }else{
                inspectorIndex=userList.indexOf(personalreview.getUserId());
                result[defectIndex][inspectorIndex]=1;
            }
            defectIndex++;
        }

        return result;
    }

    public UniversalState addQualityReview(ProjectQualityModel projectQualityModel) {

        Projectquality projectquality=new Projectquality();
        CreateIdDao createIdDao=new CreateIdDaoImpl();
        int id=createIdDao.CreateIntId("Projectquality");
        projectquality.setId(id);
        projectquality.setProjectId(projectQualityModel.getProjectId());
        projectquality.setUserId(projectQualityModel.getUserId());
        projectquality.setDescription(projectQualityModel.getDescription());
        Date date=new Date();
        DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time=format.format(date);
        projectquality.setEndTime(time);

        int[][] matrix=getMatrix(projectQualityModel.getProjectId());
        if(matrix==null||matrix[0].length==1){
            projectquality.setMethod1(0.0);
            projectquality.setMethod2(0.0);
        }else {
            CrcCalculation crcCalculation = new CrcCalculation(matrix);
            projectquality.setMethod1(crcCalculation.getMhCH());
            projectquality.setMethod2(crcCalculation.getMtCH());
        }
        ProjectqualityDao projectqualityDao=new ProjectqualityDaoImpl();
        System.out.println(projectquality.getId()+" "+projectquality.getProjectId()
        +" "+projectquality.getUserId()+" "+projectquality.getEndTime()+" "+projectquality.getDescription()
        +" "+projectquality.getMethod1()+" "+projectquality.getMethod2());
        return projectqualityDao.addProjectquality(projectquality)?UniversalState.SUCCESS:UniversalState.FAIL;
    }


//   迭代树状结构
//    private void setFlag(String combination){
//        String[] idList=combination.split(";");
//        Summary po=new Summary();
//        Summary summary;
//        for (String id:idList) {
//            po.setId(Integer.parseInt(id));
//            summary =summaryDao.findSummaryById(po);
//            if(summary.getCombination().equals("")){
//                inspectorIndex=userList.indexOf(summary.getUserId());
//                result[defectIndex][inspectorIndex]=1;
//            }else{
//                setFlag(summary.getCombination());
//            }
//        }
//    }
}
