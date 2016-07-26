package tool;

import enums.FileType;
import model.PersonalReviewRecord;

/**
 * Created by zs on 2016/7/26.
 */
public class RecordTransfer {
    public static PersonalReviewRecord change(String[] strs){
        PersonalReviewRecord r=new PersonalReviewRecord();
        //第一项，所在路径名
        r.setPath(strs[0]);
        //第二项，所在行数
        r.setLineNum(strs[1]);
        //第三项，错误类型
        r.setType(strs[2]);
        //TODO
//            //第四项，文件类型，分为Dir,File或Code三种，Dir不出现
//            FileType ft=FileType.valueOf(strs[3]);
//            r.setFileType(ft);
//            //第五项，错误细节
//            r.setDescription(strs[4]);
        //第四项，文件类型，先默认为code
        FileType ft=FileType.Code;
        r.setFileType(ft);
        //第五项，错误细节
        r.setDescription(strs[3]);
//        //第六项，若为文档增加一个页数
//        if(ft.equals(FileType.File))
//            r.setPagesNum(strs[5]);
        return r;
    }
}
