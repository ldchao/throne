package DaoTest;

import model.FileModel;
import service.FileService;
import serviceImpl.FileServiceImpl;

import java.util.List;

/**
 * Created by dlydd on 2016/7/28.
 */
public class TestFile {
    public static void main(String args[]){
        FileService fileService = new FileServiceImpl();
//        List<FileModel> fileModelList= fileService.getDir("C://aaaa");
//        for(int i=0;i<fileModelList.size();i++){
//            FileModel fileModel = fileModelList.get(i);
//            System.out.println(fileModel.getContent());
//            System.out.println(fileModel.getN());
//            System.out.println(fileModel.getPath());
//            System.out.println(fileModel.getTime());
//            System.out.println(fileModel.getType());
//        }
        fileService.unZip("2","C://ab.zip","C://");
    }
}
