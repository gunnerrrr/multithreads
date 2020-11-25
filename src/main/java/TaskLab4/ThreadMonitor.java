package TaskLab4;

public class ThreadMonitor {
    public void ShowInfoAboutThread (Thread thread) {
        System.out.println("Назва потоку: "+thread.getName());
        System.out.println("Стан потоку: "+thread.getState());
        System.out.println("Пріоритет потоку: "+thread.getPriority());
        if (thread.isAlive()) System.out.println("Потік є дієвим.");
        else System.out.println("Потік не є дієвим.");
    }
}
