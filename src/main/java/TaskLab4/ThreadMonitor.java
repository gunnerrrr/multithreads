package TaskLab4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

    public class ThreadMonitor implements Runnable {
            private static JFrame frame;
            public static JTextArea textArea;

            public ThreadMonitor() {
                frame = new JFrame();


                JPanel panel = new JPanel();
                textArea = new JTextArea(40, 70);
                JScrollPane scroll = new JScrollPane(textArea,
                        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                        JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                panel.setLayout(new FlowLayout());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                panel.add(scroll);
                frame.add(panel);
                frame.setLocationRelativeTo(null);
                frame.setSize(1900, 900);
                frame.setVisible(true);
            }
        public String GetInfoAboutThreads() {
            try {
                StringBuilder sb = new StringBuilder();
                List<Thread> threads = TaskManager.threads;


                    if (threads == null || threads.size() == 0) {
                        return (sb.toString() + "\n");
                    }

                    threads.forEach(t -> sb.append("Назва потоку: ").append(t.getName()).append("    Пріоритет потоку: ").append(t.getPriority()).append("     Стан потоку:").append(t.getState()).append("\n"));
                    sb.append("Вивід закінчено");

                    return (sb.toString() + "\n");

            } catch (Exception ex){
                return "";
            }
        }


        @Override
            public void run() {
                ActionListener actionListener = actionEvent -> textArea.append(GetInfoAboutThreads());
                javax.swing.Timer timer = new Timer(100, actionListener);
                timer.setRepeats(true);
                timer.start();
                }
            }


