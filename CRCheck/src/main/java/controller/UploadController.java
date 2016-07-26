package controller;

import POJO.User;
import enums.UniversalState;
import model.UserModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import service.FileService;
//import serviceImpl.FileServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class UploadController {

	@RequestMapping("/oneUpload")
	public String oneUpload(@RequestParam("oneFile") MultipartFile oneFile, @RequestParam("projectId") String projectId,HttpServletRequest request){

        // TODO: 2016/7/26 取id ，用来创建文件名，返回id用来存储
		String fileId =projectId;

		String realPath=request.getSession().getServletContext().getRealPath("/");
		String uploadUrl = realPath + "ProjectResources/ProjectCompressedFile/"+fileId+"/";
		String decompressionUrl=realPath+"ProjectResources/ProjectDecompressedFile/"+fileId+"/";
		String filename = oneFile.getOriginalFilename();

//		String originalName = oneFile.getOriginalFilename();
//		String nameSplit[]=originalName.split("\\.");
//		int index=nameSplit.length-1;
//		String filename =fileId;
//		if(index>0)
//			filename+="."+nameSplit[index];
		//文件夹不存在时新建文件夹
		File dir = new File(uploadUrl);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		
		System.out.println("文件上传到: " + uploadUrl + filename);
		
		File targetFile = new File(uploadUrl + filename);
		if (!targetFile.exists()) {
			try {
				targetFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		try {
			oneFile.transferTo(targetFile);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
//
//		FileService fileService=new FileServiceImpl();
//		fileService.unZip( uploadUrl + filename,decompressionUrl);

		return null;
	}

	@RequestMapping("/headPortraitsUpload")
	@ResponseBody
	public String headPortraitsUpload(@RequestParam("imageFile") MultipartFile imageFile, HttpServletRequest request){

		UserModel user=(UserModel)request.getSession().getAttribute("User");
		String uploadUrl = request.getSession().getServletContext().getRealPath("/") + "HeadPortraits/";

		String originalName = imageFile.getOriginalFilename();
		String nameSplit[]=originalName.split("\\.");
		int index=nameSplit.length-1;
		String filename =user.getId();
		if(index>0)
			filename+="."+nameSplit[index];

		File dir = new File(uploadUrl);
		if (!dir.exists()) {
			dir.mkdirs();
		}

		System.out.println("文件上传到: " + uploadUrl + filename);

		File targetFile = new File(uploadUrl + filename);
		if (!targetFile.exists()) {
			try {
				targetFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				return UniversalState.FAIL.toString();
			}
		}

		try {
			imageFile.transferTo(targetFile);
		} catch (IllegalStateException e) {
			e.printStackTrace();
			return UniversalState.FAIL.toString();
		} catch (IOException e) {
			e.printStackTrace();
			return UniversalState.FAIL.toString();
		}

		return UniversalState.SUCCESS.toString();
	}

	@RequestMapping("/moreUpload")
	public String moreUpload(HttpServletRequest request,@RequestParam("projectId") String projectId){

		String fileId =projectId;

		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
		Map<String, MultipartFile> files = multipartHttpServletRequest.getFileMap();
		
		String uploadUrl = request.getSession().getServletContext().getRealPath("/")
				+ "ProjectResources/ProjectDecompressedFile/"+fileId+"/";
		File dir = new File(uploadUrl);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		
//		List<String> fileList = new ArrayList<String>();
		
		for (MultipartFile file :  files.values()) {
			File targetFile = new File(uploadUrl + file.getOriginalFilename());
			if (!targetFile.exists()) {
				try {
					targetFile.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				try {
					file.transferTo(targetFile);
//					fileList.add("http://localhost:8082/upload/" + file.getOriginalFilename());
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		}
//		request.setAttribute("files", fileList);
		return null;
	}
	
}
