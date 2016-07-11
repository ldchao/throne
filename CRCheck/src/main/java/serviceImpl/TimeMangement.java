package serviceImpl;

/**
 * Created by lvdechao on 2016/7/11.
 */
public class TimeMangement implements Runnable{


    TimeMangement(){

        Thread t = new Thread(this);
        t.start();

    }


    public void run() {

        while(true){

        }

    }
}
