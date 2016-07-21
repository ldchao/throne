package controller;

import model.FileModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.FileService;
import serviceImpl.FileServiceImpl;

import java.util.List;

/**
 * Created by zs on 2016/7/21.
 */
@Controller
public class FileController {
    //获取文件
    @RequestMapping(value = "/dir/{path}", method = RequestMethod.POST)
    @ResponseBody
    public List<FileModel> getContent(@PathVariable("path") String path){
        FileService s=new FileServiceImpl();
        List<FileModel> list=s.getDir(path);
        return list;
    }
}
