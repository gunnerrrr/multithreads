package TaskLab4;
import TaskLab4.MyThread;
import TaskLab4.TaskManager;

import javax.swing.*;
import java.io.File;
import java.nio.file.Paths;
import java.util.regex.Pattern;


public class Main  {

    public static void main(String[] args) throws InterruptedException {
        Pattern pattern= Pattern.compile(".+jpg$");
        File file=new File(String.valueOf(Paths.get("/Users/mac/Desktop/re/Users/max")));
        double maxSize=100000000;
        int maxCountOfThreads=400;
//Where member variables are declared:
        JProgressBar progressBar;

//Where the GUI is constructed:
        progressBar = new JProgressBar(0, 10000000);
        progressBar.setValue(0);
        progressBar.setStringPainted(true);
        TaskManager taskManager = new TaskManager(file,maxSize,pattern);
        taskManager.calculateCountOfSubDirectories(file,maxCountOfThreads);
        taskManager.calculateCountOfBigFiles(maxSize,file,maxCountOfThreads);
        taskManager.SearchByPattern(file,maxCountOfThreads);


        Thread thread=new Thread();
        thread.sleep(1000);
        System.out.println(TaskManager.getCountOfSubDirectories()+" "+TaskManager.getCountOfBigFiles()+" "+ TaskManager.getCountOfMathFiles());

//        taskManager.calculateCountOfSubDirectories(file);
//        taskManager.calculateCountOfBigFiles(1000000000,file);
//        taskManager.setP(pattern);
//        taskManager.SearchByPattern(file);
//        FindFile ff = new FindFile();
//        Scanner scan = new Scanner(System.in);
//        System.out.println("Enter the file to be searched.. " );
//        String name = scan.next();
//        System.out.println("Enter the directory where to search ");
//        String directory = scan.next();
//        ff.findFile(name,new File(directory));
    }

    }

