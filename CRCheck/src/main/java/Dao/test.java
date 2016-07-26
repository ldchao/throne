package Dao;

import DaoImpl.PersonalreviewDaoImpl;
import POJO.Personalreview;

import java.util.List;

/**
 * Created by lvdechao on 2016/7/26.
 */
public class test {

    public static void main(String[] args) {
        PersonalreviewDao p=new PersonalreviewDaoImpl();
        Personalreview po =new Personalreview();
        po.setProjectId(30);
        List<Personalreview> list=p.findValidPersonalReview(po);
        System.out.println(list.size());
        for (Personalreview r:list) {
            System.out.println(r.getId());
        }
    }
}
