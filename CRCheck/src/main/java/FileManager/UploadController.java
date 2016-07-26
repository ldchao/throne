package FileManager;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * Created by mm on 2016/7/25.
 */
public class UploadController {
    @RequestMapping("/oneUpload")
    public String oneUpload(@RequestParam("imageFile")MultipartFile imageFile, HttpServletRequest request){
        String uploadUrl=request.getSession().getServletContext().getRealPath("/") + "upload//";
        String filename=imageFile.getOriginalFilename();

        File dir=new File(uploadUrl);
        if(!dir.exists()){
            dir.mkdirs();
        }
        System.out.println("文件上传到："+uploadUrl+ filename);

        File targetFile=new File(uploadUrl+ filename);
        if(!targetFile.exists()){
            try {
                targetFile.createNewFile();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        try {
            imageFile.transferTo(targetFile);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "http://localhost:8082/CRCheck/upload/"+filename;
    }
}
