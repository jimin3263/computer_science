package observer.MP03.observer;

import javax.swing.*;
import java.awt.event.WindowListener;

public abstract class FrameWindow {
    private JFrame frame;

    public FrameWindow(String title, int x, int y, int width, int height) {
        frame = createWindow(title, x, y, width, height);
    }

    public FrameWindow(String title, int x, int y, int width, int height, WindowListener lis) {
        frame = createWindow(title, x, y, width, height);
        frame.addWindowListener(lis);
    }

    //네모 하나 만드는 역할
    public JFrame createWindow(String title, int x, int y, int width, int height) {
        JFrame frame;
        frame = new JFrame(title);
        frame.setBounds(x, y, width, height); //x 축, y 축, 가로, 세로 길이

        JPanel panel = createPanel(width, height); //버튼 추가하는 판넬 추가함
        frame.getContentPane().add(panel); //content frame에 추가
        frame.pack();
        frame.setVisible(true);
        return frame;
    }

    public void closeWindow() {
        frame.setVisible(false);
        frame.dispose();
    }

    public void addWindowListener(WindowListener lis) {
        frame.addWindowListener(lis);
    }

    public abstract JPanel createPanel(int width, int height);
}
