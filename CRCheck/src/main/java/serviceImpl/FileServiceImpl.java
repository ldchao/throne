package serviceImpl;

import Dao.FileDao;
import DaoImpl.FileDaoImpl;
import enums.FileType;
import model.FileModel;
import service.FileService;


import java.io.*;
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
            LinkedList<File> list = new LinkedList<File>();

            File[] files = file.listFiles();
            for (File file2 : files) {
                if (file2.isDirectory()) {
                    String[] l=file2.list();
                    String fileNum=l.length+"";
                    POJO.File file1 = new POJO.File();
                    file1.setPath(file2.getAbsolutePath());
                    String state=fileDao.getFileState(file1);
                    String time=fileDao.getFileLastTime(file1);
                    pList.add(new FileModel(file2.getAbsolutePath(), FileType.Dir,state,fileNum,time));
                    list.add(file2);
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
                        POJO.File file1 = new POJO.File();
                        file1.setPath(file2.getAbsolutePath());
                        String state=fileDao.getFileState(file1);
                        String time=fileDao.getFileLastTime(file1);
                        pList.add(new FileModel(file2.getAbsolutePath(), FileType.Code,state, n, time));
                        //System.out.println("文件:" + file2.getAbsolutePath()+" "+file2.length());
                    }
                }
            }
            File temp_file;
            while (!list.isEmpty()) {
                temp_file = list.removeFirst();
                files = temp_file.listFiles();
                for (File file2 : files) {
                    if (file2.isDirectory()) {
                        String[] l=file2.list();
                        String fileNum=l.length+"";
                        POJO.File file1 = new POJO.File();
                        file1.setPath(file2.getAbsolutePath());
                        String state=fileDao.getFileState(file1);
                        String time=fileDao.getFileLastTime(file1);
                        pList.add(new FileModel(file2.getAbsolutePath(),FileType.Dir,state,fileNum,time));
                        //System.out.println("文件夹:" + file2.getAbsolutePath());
                        list.add(file2);
                    } else {
                        int fileNum=0;
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
                            fileNum++;
                        }else {
                            POJO.File file1 = new POJO.File();
                            file1.setPath(file2.getAbsolutePath());
                            String state=fileDao.getFileState(file1);
                            String time=fileDao.getFileLastTime(file1);
                            pList.add(new FileModel(file2.getAbsolutePath(), FileType.Code,state,n,time));
                            //System.out.println("文件:" + file2.getAbsolutePath()+" "+file2.length());
                            fileNum++;
                        }
                    }
                }
            }
        } else {
            System.out.println("文件不存在!");
        }
        return pList;
    }

    public static final int DEFAULT_BUFSIZE = 1024 * 16;
    /**
     * 解压Zip文件
     * @param srcZipFile
     * @param destDir
     */
    public  boolean unZip(String srcZipFile, String destDir) {
        try {
            ZipFile zipFile = new ZipFile(srcZipFile);
            unZip(zipFile, destDir);
        }catch(Exception e){
            return false;
        }
        return true;
    }

    public static void unZip(File srcZipFile, String destDir) throws IOException
    {
        ZipFile zipFile = new ZipFile(srcZipFile);
        unZip(zipFile, destDir);
    }
    
    public static void unZip(ZipFile zipFile, String destDir) throws IOException
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
        return false;
    }
}
