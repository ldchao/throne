package FileManager;

import com.qiniu.util.Auth;

public class DownloadDemo {
    //设置好账号的ACCESS_KEY和SECRET_KEY
    String ACCESS_KEY = "w28PsJx2KtX1xndjyubAl4DbAjdqgvRTvkZqHwT1";
    String SECRET_KEY = "KqkZSpyctdSXEOHsF1d-3d2snsUb1SZSzFmIsi8b";
    //密钥配置
    Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
    //构造私有空间需要生成的下载的链接
    String URL = "http://141250089/my-java.png";

    public void download(){
        //调用privateDownloadUrl方法生成下载链接,第二个参数可以设置Token的过期时间
        String downloadRUL = auth.privateDownloadUrl("http://141250089.qiniudn.com/my-java.png");
        System.out.println(downloadRUL);
    }
    public static void main(String args[]){
        new DownloadDemo().download();
    }
}
