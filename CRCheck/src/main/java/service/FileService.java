package service;

import model.FileModel;
import java.util.List;

/**
 * Created by zs on 2016/7/21.
 */
public interface FileService {

    //get all dir and file in this path
    public List<FileModel> getDir(String path);

    public boolean unZip(String filePath,String destination);

    public boolean changeState(FileModel fileModel);
}
