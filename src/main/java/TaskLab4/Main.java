package TaskLab4;
import TaskLab4.MyThread;
import TaskLab4.TaskManager;

//Потрібно реалізувати програмний додаток з використанням потоків. Продемонструвати створення потоків 2 способами.  Програмний додаток повинен відповідати наступним вимогам.
//        •	Для реалізації, при потребі синхронізації не використовувати класи-надбудови для роботи з групами потоків.
//        •	Створити іншу версію програмного додатку, який, навпаки,  застосовує класи-надбудови.
//        •	Створити клас монітору потоку, який буде відображати інформацію про стан потоку в режимі реального часу, включаючи інформацію про ім’я, стан, пріоритет,  чи є потік дієвим.
//        •	Монітор повинен запускатись в своєму потоці і не залежати від досліджуваного потоку. Монітор отримує посилання на потік, який має спостерігати. Після цього він використовує таймер для обновлення( через задані регулярні інтервали часу) для відображення стану потоку в вікні, побудованому за технологією Swing.
//        •	Проаналізувати час виконання програми без використання потоків та з викликом  заданої кількості потоків.
//        •	Номер варіанту завдання формується шляхом остачі від ділення на 4 порядкового номеру прізвища студента у списку журналу  плюс 1.
//        Варіант 2. Вибрати один з найбільших каталогів операційної системи.
//        Організувати паралельну обробку статистики підкаталогів,
//        яка включає кількість файлів, що перевищують заданий розмір,
//        кількість файлів, що відповідають заданому шаблону пошуку та визначенння кількості підкаталогів.
//        Кількість потоків для обробки встановлює користувач. Кожен потік обирає підкаталог, з яким працює.
//        У випадку звільнення потоку, він обирає наступний вільний підкаталог.
//        Так триває до тих пір, поки не буде отримана вся статистика.


import javax.swing.*;
import java.io.File;
import java.nio.file.Paths;
import java.util.regex.Pattern;


public class Main  {

    public static void main(String[] args) throws InterruptedException {
        Pattern pattern= Pattern.compile(".+png$");
        File file=new File(String.valueOf(Paths.get("/Users/mac/Desktop/re/Users/max/Samsung")));
        double maxSize=100000000;
        int maxCountOfThreads=500;

        TaskManager taskManager = new TaskManager(pattern,file,maxSize);
        long startTime = System.currentTimeMillis();

        taskManager.calculateCountOfSubDirectories(file);
        taskManager.calculateCountOfBigFiles(maxSize,file);
        taskManager.SearchByPattern(file);
        long endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime-startTime) + "ms");



        Thread thread=new Thread();
        thread.sleep(1000);
        System.out.println(TaskManager.getCountOfSubDirectories()+" "+TaskManager.getCountOfBigFiles()+" "+ TaskManager.getCountOfMathFiles());

//        FindFile ff = new FindFile();
//        Scanner scan = new Scanner(System.in);
//        System.out.println("Enter the file to be searched.. " );
//        String name = scan.next();
//        System.out.println("Enter the directory where to search ");
//        String directory = scan.next();
//        ff.findFile(name,new File(directory));
    }

    }

