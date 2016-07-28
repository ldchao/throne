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
    @RequestMapping(value = "/dir/{path}", method = RequestMethod.POST)
    @ResponseBody
    public List<FileModel> getContent(@PathVariable("path") String path,HttpServletRequest request,int projectId){
        String pathHeader=request.getSession().getServletContext().getRealPath("/");
        path=pathHeader+"/"+projectId+"/"+path;
        FileService s=new FileServiceImpl();
        List<FileModel> list=s.getDir(path);
        return list;
    }
    //读取文件
    @RequestMapping(value = "/file/{path}")
    @ResponseBody
    public List<String> getfileContent(@PathVariable("path")String path, HttpServletRequest request,int projectId,String type){
        String pathHeader=request.getSession().getServletContext().getRealPath("/");
        FileService s=new FileServiceImpl();
        path=pathHeader+"/"+projectId+"/"+path+"."+type;
        System.out.println(path);
        List<String> list=s.readFile(path);
        List<String> result=new ArrayList<String>();
        for(String str:list){
            str=str.replaceAll("<","&lt;");
            str=str.replaceAll(">","&gt;");
            result.add(str);
        }
        return result;
    }
}
