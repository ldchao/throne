package serviceImpl;

import Dao.*;
import DaoImpl.*;
import POJO.*;
import enums.AchievementType;
import enums.ApproveState;
import enums.UniversalState;
import model.AchievementModel;
import model.ContributionModel;
import service.ContributionService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lvdechao on 2016/7/28.
 */
public class ContributionServiceImpl implements ContributionService{

    public UniversalState addContribution(int projectID) {

        boolean result=true;

        AttendanceDao attendanceDao=new AttendanceDaoImpl();
        ArrayList<String> userList=attendanceDao.finduserDone(projectID);

        CommitrecordDao commitrecordDao=new CommitrecordDaoImpl();
        Commitrecord commitrecord=new Commitrecord();
        commitrecord.setProjectId(projectID);

        PersonalreviewDao personalreviewDao=new PersonalreviewDaoImpl();
        Personalreview prpo=new Personalreview();
        prpo.setProjectId(projectID);
        prpo.setResult(ApproveState.Error.toString());


        ProjectqualityDao projectqualityDao=new ProjectqualityDaoImpl();
        Projectquality po=new Projectquality();
        po.setProjectId(projectID);
        double allNum=projectqualityDao.getPredictedDefect(po,"Method2");

        ContributionDao contributionDao=new ContributionDaoImpl();
        CreateIdDao createIdDao=new CreateIdDaoImpl();

        AchievementDao achievementDao=new AchievementDaoImpl();
        for (String userID:userList) {

            //得到行数和时间数
            commitrecord.setUserId(userID);
            double codeLine=commitrecordDao.getReviewCodeLine(commitrecord);
            double reviewTime=commitrecordDao.getReviewTimeOfProject(commitrecord);

            //得到缺陷数
            prpo.setUserId(userID);
            double amount=personalreviewDao.getUserFoundDefect(prpo);

            //得到正确率
            double errorNum=personalreviewDao.getDefectOfResult(prpo);
            double accuracy=-1;
            if(amount!=0)
                accuracy=(amount-errorNum)/amount;

            //得到覆盖率
            double coverage=-1;
            if(allNum!=0)
                coverage=(amount-errorNum)/allNum;

            Contribution contribution=new Contribution();
            int id=createIdDao.CreateIntId("Contribution");
            contribution.setId(id);
            contribution.setProjectId(projectID);
            contribution.setUserId(userID);
            contribution.setRow(codeLine);
            contribution.setTime(reviewTime);
            contribution.setAmount(amount);
            contribution.setAccuracy(accuracy);
            contribution.setCoverage(coverage);
            result=result&contributionDao.addContribution(contribution);

            Achievement a=new Achievement();
            List<Achievement> list;
            a.setType(AchievementType.Row.toString());
            a.setValue(codeLine);
            list=achievementDao.getSatisfactoryAchievement(a,userID);
            addAchievement(list,userID);

            a.setType(AchievementType.Time.toString());
            a.setValue(reviewTime);
            list=achievementDao.getSatisfactoryAchievement(a,userID);
            addAchievement(list,userID);

            a.setType(AchievementType.Amount.toString());
            a.setValue(amount);
            list=achievementDao.getSatisfactoryAchievement(a,userID);
            addAchievement(list,userID);

            a.setType(AchievementType.Accuracy.toString());
            a.setValue(accuracy);
            list=achievementDao.getSatisfactoryAchievement(a,userID);
            addAchievement(list,userID);

            a.setType(AchievementType.Coverage.toString());
            a.setValue(coverage);
            list=achievementDao.getSatisfactoryAchievement(a,userID);
            addAchievement(list,userID);

        }
        return result?UniversalState.SUCCESS:UniversalState.FAIL;
    }

    private boolean addAchievement(List<Achievement> list,String userID){
        if(list.size()==0)
            return true;
        boolean result=true;
        CreateIdDao createIdDao=new CreateIdDaoImpl();
        PersonalAchievementDao personalAchievementDao=new PersonalAchievementDaoImpl();
        for (Achievement achievement:list) {
            int personal_achievementId=createIdDao.CreateIntId("PersonalAchievement");
            PersonalAchievement pa=new PersonalAchievement();
            pa.setId(personal_achievementId);
            pa.setUserId(userID);
            pa.setAchievementId(achievement.getId());
            result=result&personalAchievementDao.addPersonalAchievement(pa);
        }
        return result;
    }

    public ArrayList<ContributionModel> checkContributionList(String userID) {

        ArrayList<ContributionModel> result=new ArrayList<ContributionModel>();
        ContributionDao contributionDao=new ContributionDaoImpl();
        Contribution contribution=new Contribution();
        contribution.setUserId(userID);
        List<Contribution> list=contributionDao.getContributionsByUserId(contribution);
        for (Contribution c:list) {
            ContributionModel cm=new ContributionModel();
            cm.setId(c.getId());
            cm.setUserId(c.getUserId());
            cm.setProjectId(c.getProjectId());
            cm.setRow(c.getRow());
            cm.setTime(c.getTime());
            cm.setAmount(c.getAmount());
            cm.setAccuracy(c.getAccuracy());
            cm.setCoverage(c.getCoverage());
            result.add(cm);
        }
        return result;
    }

    public ContributionModel checkContributionSum(String userID) {

        ContributionDao contributionDao=new ContributionDaoImpl();
        Contribution contribution=new Contribution();
        contribution.setUserId(userID);

        ContributionModel cm=new ContributionModel();
        cm.setRow(contributionDao.getSumOfRow(contribution));
        cm.setTime(contributionDao.getSumOfTime(contribution));
        cm.setAmount(contributionDao.getSumOfAmount(contribution));
        cm.setAccuracy(contributionDao.getAverageOfAccuracy(contribution));
        cm.setCoverage(contributionDao.getAverageOfCoverage(contribution));
        return cm;
    }

    public ArrayList<Integer> checkAchievement(String userID) {

        ArrayList<Integer> result=new ArrayList<Integer>();

        PersonalAchievementDao personalAchievementDao=new PersonalAchievementDaoImpl();
        PersonalAchievement po=new PersonalAchievement();
        po.setUserId(userID);
        List<Integer> list=personalAchievementDao.getAchievements(po);

        AchievementDao achievementDao=new AchievementDaoImpl();
        int[] count={0,0,0,0,0};

        for (Integer id:list) {
            count[id/5]++;
        }
        for(int i=0;i<5;i++){
            result.add(count[i]);
        }
        return result;
    }
}
