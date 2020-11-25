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

    private File file=null;
    private double maxSize=0;
    private Pattern p;
    private Matcher m = null;
    List<Thread> threads = new ArrayList<>();

    public TaskManager(File file, double maxSize, Pattern p) {
        this.file = file;
        this.maxSize = maxSize;
        this.p = p;
    }

    public File getFile() {
        return file;
    }

    public double getMaxSize() {
        return maxSize;
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
        if (m.matches()) {
            return true;
        }
            return false;
    }

    public synchronized void calculateCountOfSubDirectories(File file, int maxCountOfThreads) throws InterruptedException {
        File[] list = file.listFiles();
        if (list != null)
            for (File fil : list) {
                if (fil.isDirectory()) {
                    System.out.println(true);
                    countOfSubDirectories++;
                    CreateNewThread(maxCountOfThreads);
                    calculateCountOfSubDirectories(fil, maxCountOfThreads);
                }

            }
    }

    private void CreateNewThread(int maxCountOfThreads) throws InterruptedException {
        if(MyThread.countOfThreads<maxCountOfThreads) {
            Thread thread = new Thread(new MyThread());
            ThreadMonitor threadMonitor= new ThreadMonitor();
            thread.setName("Thread " + MyThread.countOfThreads);
            thread.setDaemon(true);
            threads.add(thread);
            threadMonitor.ShowInfoAboutThread(thread);
            thread.start();
            System.out.println("Now works " + thread.getName());
        }
    }

    public synchronized void calculateCountOfBigFiles(double size, File file, int maxCountOfThreads) throws InterruptedException {
        File[] list = file.listFiles();
        if (list != null)
            for (File fil : list) {
                if (fil.length() > size) {
                    countOfBigFiles++;
                }
                if (fil.isDirectory()) {
                   // System.out.println(true);
                    CreateNewThread(maxCountOfThreads);
                    calculateCountOfBigFiles(size, fil, maxCountOfThreads);
                }

            }
    }

    public synchronized void SearchByPattern(File topDirectory, int maxCountOfThreads) throws InterruptedException {
        File[] list = topDirectory.listFiles();
        for (int i = 0; i < list.length; i++) {
            System.out.println(list[i].getName());

            if (list[i].isDirectory()) {
                if (accept(list[i].getName())) {
                    countOfMathFiles++;

                    //System.out.println(list[i]);
                }
                CreateNewThread(maxCountOfThreads);
                SearchByPattern(list[i], maxCountOfThreads);

            }
            else {
                if (accept(list[i].getName())) {
                    countOfMathFiles++;
                    //System.out.println(list[i]);
                }
            }
        }
    }
}

