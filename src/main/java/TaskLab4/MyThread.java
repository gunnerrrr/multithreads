package TaskLab4;


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
//
//
//public class MyThread  implements Callable<Integer> {
//TaskManager taskManager=null;
//
//    public MyThread () {
//
//    }
//
//    public Integer call() throws InterruptedException {
//
////        System.out.printf("%s started... \n", Thread.currentThread().getName());
////        try{
////            Thread.sleep(1000);
////        }
////        catch(InterruptedException e){
////            System.out.println("Thread has been interrupted");
////        }
////        System.out.printf("%s finished... \n", Thread.currentThread().getName());
//
//    }
//}
