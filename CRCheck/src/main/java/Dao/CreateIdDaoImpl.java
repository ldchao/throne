package Dao;

/**
 * Created by mm on 2016/7/12.
 */
public interface CreateIdDaoImpl {
    //if creating failed,it will return 0
    public int CreateIntId(String Class);

    //if creating failed,it will return null
    public String CreateStringId(String Class);
}
