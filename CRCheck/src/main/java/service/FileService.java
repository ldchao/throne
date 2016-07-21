package service;

import model.FileModel;
import java.util.List;

/**
 * Created by zs on 2016/7/21.
 */
public interface FileService {

    public List<FileModel> getDir(String path);
}
