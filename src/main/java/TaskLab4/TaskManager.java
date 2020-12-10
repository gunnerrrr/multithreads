package TaskLab4;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TaskManager {
    private static int countOfSubDirectories = 0;
    private static int countOfBigFiles = 0;
    private static int countOfMathFiles = 0;


    private Pattern p;
    private Matcher m = null;
    private double size;
    public static List<Thread> threads = new ArrayList<>();



    public TaskManager(Pattern p, double size) {

        this.p = p;
        this.size = size;
    }


    public void setP(Pattern p) {
        this.p = p;
    }

    public static int getCountOfSubDirectories() {
        return countOfSubDirectories;
    }

    public static int getCountOfBigFiles() {
        return countOfBigFiles;
    }

    public static int getCountOfMathFiles() {
        return countOfMathFiles;
    }


    private synchronized boolean accept(String name) {
        if (p == null) {
            return true;
        }
        m = p.matcher(name);
        return m.matches();
    }

    public void calculate(File file, Thread thread, ArrayList<Thread> threads) {
        System.out.println(thread.getName() + thread.getState() + thread.isAlive());
        thread.start();
        TaskManager.threads.add(thread);
        System.out.println(thread.getName() + thread.getState() + thread.isAlive());
        File[] list = file.listFiles();
        if (list != null) {
            for (File fil : list) {
                if (fil.length() > size) {
                    countOfBigFiles++;
                }
                if (accept(fil.getName())) {
                    countOfMathFiles++;
                }

                if (fil.isDirectory()) {
                    countOfSubDirectories++;
                    boolean isFreeThread = false;
                    Thread newThread;
                    for (int i = 0; i < threads.size(); i++) {
                        if (!(threads.get(i).isAlive()) && threads.get(i).getState() != Thread.State.TERMINATED) {
                            isFreeThread = true;
                            calculate(fil, threads.get(i), threads);
                            break;
                        }
                    }
                    if (!isFreeThread) {
                        newThread = new Thread();
                        newThread.setName("Thread #" + threads.size());
                        threads.add(newThread);
                        calculate(fil, newThread, threads);
                    }

                }

            }
        }
    }
}


//    private void CreateNewThread(int maxCountOfThreads) throws InterruptedException {
//        if(MyThread.countOfThreads<maxCountOfThreads) {
//            Thread thread = new Thread(new MyThread());
//            ThreadMonitor threadMonitor= new ThreadMonitor();
//            thread.setName("Thread " + MyThread.countOfThreads);
//            thread.setDaemon(true);
//            threads.add(thread);
//            threadMonitor.ShowInfoAboutThread(thread);
//            thread.start();
//            System.out.println("Now works " + thread.getName());
//        }
//    }
//
//    public synchronized void calculateCountOfBigFiles(double size, File file) throws InterruptedException {
//        File[] list = file.listFiles();
//        if (list != null)
//            for (File fil : list) {
//                if (fil.length() > size) {
//                    countOfBigFiles++;
//                }
//                if (fil.isDirectory()) {
//
////                    CreateNewThread(maxCountOfThreads);
//                    calculateCountOfBigFiles(size, fil);
//                }
//
//            }
//    }
//
//    public synchronized void SearchByPattern(File topDirectory) throws InterruptedException {
//        File[] list = topDirectory.listFiles();
//        if (list != null) {
//            for (int i = 0; i < list.length; i++) {
//               // System.out.println(list[i].getName());
//
//                if (list[i].isDirectory()) {
//                    if (accept(list[i].getName())) {
//                        countOfMathFiles++;
//                    }
////                CreateNewThread(maxCountOfThreads);
//                    SearchByPattern(list[i]);
//
//                } else {
//                    if (accept(list[i].getName())) {
//                        countOfMathFiles++;
//                    }
//                }
//            }
//        }
//    }
//}

