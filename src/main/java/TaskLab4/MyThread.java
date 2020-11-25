package TaskLab4;


import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyThread implements Runnable {
   public static int countOfThreads;

    public MyThread () {
        countOfThreads++;
    }

    public void run(){

        System.out.printf("%s started... \n", Thread.currentThread().getName());
        try{
            Thread.sleep(1000);
        }
        catch(InterruptedException e){
            System.out.println("Thread has been interrupted");
        }
        System.out.printf("%s finished... \n", Thread.currentThread().getName());
    }
} 