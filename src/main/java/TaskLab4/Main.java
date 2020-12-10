package TaskLab4;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

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


public class Main  {

    public static void main(String[] args) throws InterruptedException {
        //        Pattern pattern= Pattern.compile(".+jpg$");


        System.out.println("Введіть шаблон:\n");
        Scanner scanner=new Scanner(System.in);
        String text= scanner.nextLine();
        Pattern pattern=Pattern.compile(text);
        System.out.println("Enter your length of file:\n");
        scanner=new Scanner(System.in);
        double maxSize=scanner.nextDouble();

        File file=new File(String.valueOf(Paths.get("C:\\Users\\Admin")));
        ArrayList<Thread> threads = new ArrayList<>();
        Thread t = new Thread(new ThreadMonitor());
        t.start();
        int maxCountOfThreads=100;

        for(int i=0;i<maxCountOfThreads;i++) {
            Thread thread = new Thread();
            thread.setName("Thread #"+i);
            threads.add(thread);
        }

        TaskManager taskManager = new TaskManager(pattern,maxSize);
        long startTime = System.currentTimeMillis();

        taskManager.calculate(file,threads.get(0),threads);
        long endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime-startTime) + "ms");
        t.interrupt();



        Thread thread=new Thread();
        Thread.sleep(1000);
        System.out.println(TaskManager.getCountOfSubDirectories()+" "+TaskManager.getCountOfBigFiles()+" "+ TaskManager.getCountOfMathFiles());

    }
}

