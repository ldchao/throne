package serviceImpl;

import Dao.AttendanceDao;
import Dao.SummaryDao;
import DaoImpl.AttendanceDaoImpl;
import DaoImpl.SummaryDaoImpl;
import service.CRCService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lvdechao on 2016/7/15.
 */
public class CRCServiceImpl implements CRCService {

    ArrayList<String> userList;
    SummaryDao summaryDao=new SummaryDaoImpl();
    int defectIndex=0;
    int inspectorIndex=0;
    int[][] result;

    public int[][] getMatrix(int projectID) {

        int defectNum=0;
        int inspectorNum=0;

        AttendanceDao attendanceDao=new AttendanceDaoImpl();
        userList=attendanceDao.finduserDone(projectID);
        inspectorNum=userList.size();

        Summary po=new Summary();
        po.setProjectId(projectID);
        List<Summary> summaryList=summaryDao.getValidSummary(po);
        defectNum=summaryList.size();

        result=new int[defectNum][inspectorNum];
        for (int i = 0; i < defectNum; i++) {
            for (int j = 0; j <inspectorNum ; j++) {
                result[i][j]=0;
            }
        }

        for (Summary summary:summaryList) {
            if(summary.getCombination().equals("")){
                inspectorIndex=userList.indexOf(summary.getUserId());
                result[defectIndex][inspectorIndex]=1;
            }else{
                setFlag(summary.getCombination());
            }
            defectIndex++;
        }

        return result;
    }

    private void setFlag(String combination){
        String[] idList=combination.split(";");
        Summary po=new Summary();
        Summary summary;
        for (String id:idList) {
            po.setId(Integer.parseInt(id));
            summary =summaryDao.findSummaryById(po);
            if(summary.getCombination().equals("")){
                inspectorIndex=userList.indexOf(summary.getUserId());
                result[defectIndex][inspectorIndex]=1;
            }else{
                setFlag(summary.getCombination());
            }
        }


    }
}
