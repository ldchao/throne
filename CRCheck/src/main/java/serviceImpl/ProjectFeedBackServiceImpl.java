package serviceImpl;

import Dao.AttendanceDao;
import Dao.PersonalreviewDao;
import DaoImpl.AttendanceDaoImpl;
import DaoImpl.PersonalreviewDaoImpl;
import POJO.Personalreview;
import enums.FileType;
import model.PersonalReviewRecord;
import model.ScatterDiagramModel;
import model.UserAndDefect;
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
        }
        result.setUserAndDefectsList(userAndDefectList);
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
