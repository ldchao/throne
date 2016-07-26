package serviceImpl;

import Dao.AttendanceDao;
import Dao.CommitrecordDao;
import Dao.PersonalreviewDao;
import Dao.ProjectqualityDao;
import DaoImpl.AttendanceDaoImpl;
import DaoImpl.CommitrecordDaoImpl;
import DaoImpl.PersonalreviewDaoImpl;
import DaoImpl.ProjectqualityDaoImpl;
import POJO.Commitrecord;
import POJO.Personalreview;
import POJO.Projectquality;
import enums.ApproveState;
import enums.FileType;
import model.*;
import service.CRCService;
import service.ProjectFeedBackService;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lvdechao on 2016/7/22.
 */
public class ProjectFeedBackServiceImpl implements ProjectFeedBackService{

    public ScatterDiagramModel getScatterDiagramData(int projectID) {
        ScatterDiagramModel result=new ScatterDiagramModel();

        AttendanceDao attendanceDao=new AttendanceDaoImpl();
        ArrayList<String> userList=attendanceDao.finduserDone(projectID);
        result.setUserList(userList);

        PersonalreviewDao personalreviewDao=new PersonalreviewDaoImpl();
        Personalreview po=new Personalreview();
        po.setProjectId(projectID);
        List<Personalreview> reviewList=personalreviewDao.findValidPersonalReview(po);
        ArrayList<PersonalReviewRecord> personalReviewRecords=new ArrayList<PersonalReviewRecord>();
        for (Personalreview p:reviewList) {
            PersonalReviewRecord r=new PersonalReviewRecord();
            FileType fileType=FileType.valueOf(p.getFileType());
            r.setFileType(fileType);
            String[] location=p.getLocation().split(" ");
            r.setPath(location[0]);
            if(fileType == FileType.File){
                r.setLineNum(location[1]+"页"+location[2]+"行");
            }else{
                r.setLineNum(location[1]+"行");
            }
            r.setType(p.getType());
            String discription=p.getDescription();
            r.setDescription(getStringByEnter(24,discription));
            personalReviewRecords.add(r);
        }
        result.setDefectList(personalReviewRecords);

        ArrayList<UserAndDefect> userAndDefectList=new ArrayList<UserAndDefect>();
        CRCService crcService=new CRCServiceImpl();
        int[][] matrix=crcService.getMatrix(projectID);
        int length=matrix.length;
        int index=0;
        for (String userID:userList) {
            for (int i = 0; i < length; i++) {
                if(matrix[i][index]==1){
                    UserAndDefect u=new UserAndDefect();
                    u.setUserId(userID);
                    u.setDefectNum(i);
                    userAndDefectList.add(u);
                }
            }
            index++;
        }
        result.setUserAndDefectsList(userAndDefectList);
        return result;
    }

    public ArrayList<PersonalQualityModel> getStatisticChartData(int projectID) {

        ArrayList<PersonalQualityModel> result=new ArrayList<PersonalQualityModel>();

        AttendanceDao attendanceDao=new AttendanceDaoImpl();
        ArrayList<String> userList=attendanceDao.finduserDone(projectID);

        PersonalreviewDao personlreviewDao=new PersonalreviewDaoImpl();
        Personalreview po=new Personalreview();
        po.setProjectId(projectID);
        Commitrecord commitrecord=new Commitrecord();
        commitrecord.setProjectId(projectID);

        for (String userID:userList) {
            PersonalQualityModel p=new PersonalQualityModel();
            p.setUserID(userID);

            po.setUserId(userID);
            int amoutNum=personlreviewDao.getUserFoundDefect(po);
            po.setResult(ApproveState.Unapprove.toString());
            int unApproveNum=personlreviewDao.getDefectOfResult(po);
            p.setUnApproveDefectNum(unApproveNum);
            po.setResult(ApproveState.Correct.toString());
            int correctNum=personlreviewDao.getDefectOfResult(po);
            p.setCorrectDefectNum(correctNum);
            po.setResult(ApproveState.Error.toString());
            int errorNum=personlreviewDao.getDefectOfResult(po);
            p.setErrorDefectNum(errorNum);

            CommitrecordDao commitrecordDao=new CommitrecordDaoImpl();
            commitrecord.setUserId(userID);
            int codeLine=commitrecordDao.getReviewCodeLine(commitrecord);
            int reviewTime=commitrecordDao.getReviewTimeOfProject(commitrecord);

            double efficiency=(codeLine*amoutNum*1.0)/(reviewTime*1.0);
            p.setEfficiency(efficiency);
            result.add(p);
        }
        return result;
    }

    public ArrayList<ProjectQualityModel> getLineChartData(int projectID) {
        ArrayList<ProjectQualityModel> result=new ArrayList<ProjectQualityModel>();
        ProjectqualityDao projectqualityDao=new ProjectqualityDaoImpl();
        Projectquality po=new Projectquality();
        po.setProjectId(projectID);
        List<Projectquality> projectqualities=projectqualityDao.getProjectqualitys(po);
        for (Projectquality p:projectqualities) {
            ProjectQualityModel r=new ProjectQualityModel();
            r.setProjectId(p.getProjectId());
            r.setUserId(p.getUserId());
            r.setDescription(p.getDescription());
            r.setEndTime(p.getEndTime());
            r.setMethod1(p.getMethod1());
            r.setMethod2(p.getMethod2());
            result.add(r);
        }
        return result;
    }

    private String getStringByEnter(int length, String string){
        for (int i = 1; i <= string.length(); i++){
            try {
                if (string.substring(0, i).getBytes("GBK").length > length){
                    return string.substring(0, i - 1) + "<br/>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"
                            + getStringByEnter(length, string.substring(i - 1));
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return string;
    }
}
