package serviceImpl;

/**
 * Created by lvdechao on 2016/7/11.
 */
public class TimeController implements Runnable{


    TimeController(){

        Thread t = new Thread(this);
        t.start();

    }


    public void run() {

        while(true){
            
        }

    }
}
