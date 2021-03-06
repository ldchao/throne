package controller;

import model.FileModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.FileService;
import serviceImpl.FileServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zs on 2016/7/21.
 */
@Controller
public class FileController {
    //获取文件夹内容
    @RequestMapping(value = "/dir", method = RequestMethod.POST)
    @ResponseBody
    public List<FileModel> getContent(String path, HttpServletRequest request) {
        String pathHeader = request.getSession().getServletContext().getRealPath("/");
        String p = pathHeader + "ProjectResources/ProjectDecompressedFile/" + path;
        FileService fs = new FileServiceImpl();
        List<FileModel> list = fs.getDir(p);
        int length=0;
        for(FileModel model:list){
            String s=model.getPath();
            length=p.length();
            s=s.substring(length+1);
            model.setPath(s);
        }
        return list;
    }

    //读取文件
    @RequestMapping(value = "/file")
    @ResponseBody
    public List<String> getfileContent(String path, HttpServletRequest request) {
        String pathHeader = request.getSession().getServletContext().getRealPath("/");
        String p = pathHeader + "ProjectResources/ProjectDecompressedFile/"+path;
        FileService s = new FileServiceImpl();
        List<String> list = s.readFile(p);
        List<String> result = new ArrayList<String>();
        for (String str : list) {
            str = str.replaceAll("<", "&lt;");
            str = str.replaceAll(">", "&gt;");
            result.add(str);
        }
        return result;
    }
}
