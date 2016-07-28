package Dao;

import POJO.File;

/**
 * Created by mm on 2016/7/26.
 */
public interface FileDao {
    //need the "path" of "File",it will return the "state" of "File"
    public String getFileState(File po);

    //need the "path" of "File",it will return the "lastTime" of "File"
    public String getFileLastTime(File po);

    //need the "path" of "File",it will change the "state" of "File"
    public boolean changeFileState(File po);

    //add "File" into database
    public boolean addFile(File po);
}
