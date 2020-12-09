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
                textArea = new JTextArea(20, 40);
                JScrollPane scroll = new JScrollPane(textArea,
                        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                        JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                panel.setLayout(new FlowLayout());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                panel.add(scroll);
                frame.add(panel);
                frame.setLocationRelativeTo(null);
                frame.setSize(700, 400);
                frame.setVisible(true);
            }
        public String Info() {
            try {
                StringBuilder sb = new StringBuilder();
                List<Thread> threads = TaskManager.threads;


                    if (threads == null || threads.size() == 0) {
                        return (sb.toString() + "\n");
                    }

                    threads.forEach(t -> sb.append("Назва потоку:").append(t.getName()).append("Стан потоку:").append(t.getState()).append("\n"));
                    sb.append("Вивід закінчено");

                    return (sb.toString() + "\n");

            } catch (Exception ex){
                return "";
            }
        }


        @Override
            public void run() {
                ActionListener task = evt -> this.textArea.append(Info());
                javax.swing.Timer timer = new Timer(1000, task);
                timer.setRepeats(true);
                timer.start();
                }
            }


