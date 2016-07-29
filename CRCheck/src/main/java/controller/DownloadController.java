package controller;

import model.FileModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import service.FileService;
import serviceImpl.FileServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@Controller
public class DownloadController {

	/**
	 *
	 * 将fileName放到session中
	 * 如果下载整个项目的zip，传输 fileName= ProjectCompressedFile/"+项目存储编号+"/文件名"
	 * 否则传输 fileName= ProjectDecompressedFile/"+项目存储编号+"/文件具体路径"
	 *
     */

	@RequestMapping("/download")
	public String download(@RequestParam String fileName , HttpServletRequest request, HttpServletResponse response){
		response.setContentType("text/html;charset=utf-8");
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;

		String ctxPath = request.getSession().getServletContext().getRealPath("/") + "ProjectResources/";

		System.out.println(ctxPath);

		String getCompressedPath=ctxPath+"ProjectCompressedFile/"+fileName;

		FileService fileService=new FileServiceImpl();
		List<FileModel> list= fileService.getDir(getCompressedPath);
		String downLoadPath ="";
		if(list.size()>0){
			FileModel f=list.get(0);
			downLoadPath=f.getPath();
		}

//		String downLoadPath = ctxPath + fileName;
		System.out.println(downLoadPath);
		try {
			long fileLength = new File(downLoadPath).length();
			response.setContentType("application/x-msdownload;");
			response.setHeader("Content-disposition", "attachment; filename=" + new String(fileName.getBytes("utf-8"), "ISO8859-1"));
			response.setHeader("Content-Length", String.valueOf(fileLength));
			bis = new BufferedInputStream(new FileInputStream(downLoadPath));
			bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bis != null)
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			if (bos != null)
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return null;

	}
	
}
