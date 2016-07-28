package serviceImpl;

import Dao.FileDao;
import DaoImpl.FileDaoImpl;
import POJO.*;
import enums.FileType;
import model.FileModel;
import service.FileService;


import java.io.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Created by zs on 2016/7/21.
 */
public class FileServiceImpl implements FileService {

   FileDao fileDao = new FileDaoImpl();

    public List<FileModel> getDir(String path){
        File file = new File(path);
        ArrayList<FileModel> pList = new ArrayList<FileModel>();
        if (file.exists()) {
            File[] files = file.listFiles();
            for (File file2 : files) {
                if (file2.isDirectory()) {
                    String s=file2.getAbsolutePath();
                    int a=s.length()-9;
                    if(s.length()-9<0){
                        a=0;
                    }
                    if(!(s.substring(s.length()-7,s.length()).equals("_MACOSX"))
                            &&(!(s.substring(a,s.length()).equals(".DS_Store")))) {
                        String[] l = file2.list();
                        String fileNum = l.length + "";
                        POJO.File file1 = new POJO.File();
                        file1.setPath(file2.getAbsolutePath());
                        int filNum=getDirContent(file2.getAbsolutePath(),0);
                        String content=fileNum+"";
                        String time=getDirTime(file2.getAbsolutePath(),"");
                        pList.add(new FileModel(file2.getAbsolutePath(), FileType.Dir,content, fileNum, time));
                    }
                } else {
                    String s=file2.getAbsolutePath();
                    String n;
                    if(file2.length()>=1024) {
                        n = file2.length() /1024+ "KB";
                    }else{
                        n=file2.length()+"B";
                    }
                    if(s.substring(s.length()-3,s.length()).equals("doc")){
                        POJO.File file1 = new POJO.File();
                        file1.setPath(file2.getAbsolutePath());
                        String state=fileDao.getFileState(file1);
                        String time=fileDao.getFileLastTime(file1);
                        pList.add(new FileModel(file2.getAbsolutePath(),FileType.File,state,n,time));
                    }else {
                        int a=s.length()-9;
                        if(s.length()-9<0){
                            a=0;
                        }
                        if(!(s.substring(s.length()-7,s.length()).equals("_MACOSX"))
                                &&(!(s.substring(a,s.length()).equals(".DS_Store")))) {
                            POJO.File file1 = new POJO.File();
                            String p = file2.getAbsolutePath();
                            p=p.replaceAll("\\\\","/");
                            file1.setPath(p);
                            String state = fileDao.getFileState(file1);
                            //System.out.println("state:"+state);
                            String time = fileDao.getFileLastTime(file1);
                            pList.add(new FileModel(file2.getAbsolutePath(), FileType.Code, state, n, time));
                        }
                    }
                }
            }
        } else {
            System.out.println("文件不存在!");
        }
        return pList;
    }

    private int getDirContent(String path,int fileNum) {
        File file = new File(path);
        FileDao fileDao = new FileDaoImpl();
        if (file.exists()) {
            File[] files = file.listFiles();
            for (File file2 : files) {
                if (file2.isDirectory()) {
                    String s = file2.getAbsolutePath();
                    int a=s.length()-9;
                    if(s.length()-9<0){
                        a=0;
                    }
                    if(!(s.substring(s.length()-7,s.length()).equals("_MACOSX"))
                            &&(!(s.substring(a,s.length()).equals(".DS_Store")))) {
                        getDirContent(file2.getAbsolutePath(),fileNum);
                    }
                }else {
                    String s = file2.getAbsolutePath();
                    int a=s.length()-9;
                    if(s.length()-9<0){
                        a=0;
                    }
                    if(!(s.substring(s.length() - 7, s.length()).equals("_MACOSX"))
                            && (!(s.substring(a, s.length()).equals(".DS_Store")))) {
                        POJO.File file1 = new POJO.File();
                        String p = file2.getAbsolutePath();
                        p = p.replaceAll("\\\\", "/");
                        file1.setPath(p);
                        String state = fileDao.getFileState(file1);
                        if (state.equals("REVIEWED")) {
                            fileNum++;
                        }
                    }
                }
            }
        }else {
            System.out.println("文件不存在!");
        }
        return fileNum;
    }

    private String getDirTime(String path,String time){
        File file = new File(path);
        FileDao fileDao = new FileDaoImpl();
        if (file.exists()) {
            File[] files = file.listFiles();
            for (File file2 : files) {
                if (file2.isDirectory()) {
                    String s = file2.getAbsolutePath();
                    int a=s.length()-9;
                    if(s.length()-9<0){
                        a=0;
                    }
                    if(!(s.substring(s.length() - 7, s.length()).equals("_MACOSX"))
                            && (!(s.substring(a, s.length()).equals(".DS_Store")))) {
                        getDirTime(file2.getAbsolutePath(),time);
                    }
                }else{
                    String s = file2.getAbsolutePath();
                    int a=s.length()-9;
                    if(s.length()-9<0){
                        a=0;
                    }
                    if(!(s.substring(s.length() - 7, s.length()).equals("_MACOSX"))
                            && (!(s.substring(a, s.length()).equals(".DS_Store")))) {
                        POJO.File file1 = new POJO.File();
                        s=s.replaceAll("\\\\","/");
                        file1.setPath(s);
                        String thisTime = fileDao.getFileLastTime(file1);
                        if(thisTime.compareTo(time)>0){
                            time=thisTime;
                        }
                    }
                }
            }
        }else {
            System.out.println("文件不存在!");
        }
        return time;
    }

    public static final int DEFAULT_BUFSIZE = 1024 * 16;
    /**
     * 解压Zip文件
     * @param srcZipFile
     * @param destDir
     */
    public  boolean unZip(String proId,String srcZipFile, String destDir) {
        try {
            ZipFile zipFile = new ZipFile(srcZipFile);
            unZip(proId,zipFile, destDir);
        }catch(Exception e){
            return false;
        }

        return true;
    }

    public static void unZip(String proId,File srcZipFile, String destDir) throws IOException
    {
        ZipFile zipFile = new ZipFile(srcZipFile);
        unZip(proId,zipFile, destDir);
    }

    public static void unZip(String proId,ZipFile zipFile, String destDir) throws IOException
    {
        Enumeration<? extends ZipEntry> entryEnum = zipFile.entries();
        ZipEntry entry = null;
        while (entryEnum.hasMoreElements()) {
            entry = entryEnum.nextElement();
            File destFile = new File(destDir + entry.getName());
            if (entry.isDirectory()) {
                destFile.mkdirs();
            }
            else {
                destFile.getParentFile().mkdirs();
                InputStream eis = zipFile.getInputStream(entry);
                System.out.println(eis.read());
                write(eis, destFile);
                POJO.File file = new POJO.File();
                int id = Integer.parseInt(proId);
                file.setProjectId(id);
                file.setPath(destDir+entry.getName());
                file.setState("NOTREVIEWED");
                file.setLastTime("未开始评审");
                FileDao fileDao = new FileDaoImpl();
                fileDao.addFile(file);
            }
        }
    }

    /**
     * 将输入流中的数据写到指定文件
     */
    public static void write(InputStream inputStream, File destFile) throws IOException
    {
        BufferedInputStream bufIs = null;
        BufferedOutputStream bufOs = null;
        try {
            bufIs = new BufferedInputStream(inputStream);
            bufOs = new BufferedOutputStream(new FileOutputStream(destFile));
            byte[] buf = new byte[DEFAULT_BUFSIZE];
            int len = 0;
            while ((len = bufIs.read(buf, 0, buf.length)) > 0) {
                bufOs.write(buf, 0, len);
            }
        } catch (IOException ex) {
            throw ex;
        } finally {
            close(bufOs, bufIs);
        }
    }

    /**
     * 安全关闭多个流
     */
    public static void close(Closeable... streams)
    {
        try {
            for (Closeable s : streams) {
                if (s != null)
                    s.close();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace(System.err);
        }
    }

    public boolean changeState(FileModel fileModel) {
        FileDao fileDao = new FileDaoImpl();
        POJO.File file = new POJO.File();
        file.setPath(fileModel.getPath());
        boolean a = fileDao.changeFileState(file);
        return a;
    }

    public List<String> readFile(String path) {
        ArrayList<String> s = new ArrayList<String>();
        try {
            FileReader fr = new FileReader(path);
            BufferedReader br = new BufferedReader(fr);
            String str;
            while ((str = br.readLine()) != null) {
               s.add(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }
}
