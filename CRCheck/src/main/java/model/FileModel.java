package model;

import enums.FileType;

/**
 * Created by zs on 2016/7/21.
 */
public class FileModel {
    //文件路径
    String path;
    //文件类型
    FileType type;
    //文件夹-已评审多少;文件-评审状态 "REVIEWED", "NOTREVIEWED"
    String content;
    //文件数/大小
    String n;
    //最近评审时间
    String time;

    public String getPath() {
        return path;
    }

    public FileType getType() {
        return type;
    }

    public String getContent() {
        return content;
    }

    public String getN() {
        return n;
    }

    public String getTime() {
        return time;
    }

    public FileModel(String path, FileType type, String content, String n, String time) {
        this.path=path;
        this.content=content;
        this.n=n;
        this.time=time;
        this.type=type;
    }
}
