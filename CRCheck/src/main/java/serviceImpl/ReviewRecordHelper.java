package serviceImpl;

import POJO.Personalreview;
import enums.ApproveState;
import enums.CommitState;
import enums.FileType;
import model.PersonalReviewRecord;

/**
 * Created by lvdechao on 2016/7/28.
 */
public class ReviewRecordHelper {

    //将Personalreview转换成PersonalReviewRecord
    public static PersonalReviewRecord exchange(Personalreview p){
        PersonalReviewRecord r=new PersonalReviewRecord();
        r.setId(p.getId());
        r.setUserId(p.getUserId());
        r.setUserId(p.getUserId());
        r.setProjectId(p.getProjectId());
        r.setCommitTime(p.getCommitTime());
        FileType fileType = FileType.valueOf(p.getFileType());
        String[] location=p.getLocation().split(" ");
        r.setPath(location[0]);
        if(fileType == FileType.File){
            r.setPagesNum(location[1]);
            r.setLineNum(location[2]);
        }else{
            r.setLineNum(location[1]);
        }
        r.setType(p.getType());
        r.setDescription(p.getDescription());
        CommitState finishState= CommitState.valueOf(p.getState());
        String state;
        switch (finishState){
            case Done:
                state="后续提交";
                break;
            case NotDone:
                state="正常提交";
                break;
            default:
                state="合并项";
        }
        r.setState(state);
        r.setResult(ApproveState.valueOf(p.getResult()));
        return r;
    }

}
