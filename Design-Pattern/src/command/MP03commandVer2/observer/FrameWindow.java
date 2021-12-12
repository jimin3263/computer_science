package command.MP03commandVer2.observer;

import javax.swing.*;
import java.awt.event.WindowListener;

public abstract class FrameWindow {
    private JFrame frame;

    public FrameWindow(String title, int x, int y, int width, int height) { //textfield, label 호출 시 사용
        frame = createWindow(title, x, y, width, height);
//        frame = null;
    }

    public FrameWindow(){ //메인 호출 시 사용
        frame = null;
    }

//    public FrameWindow(String title, int x, int y, int width, int height, WindowListener lis) {
//        frame = createWindow(title, x, y, width, height); //생성자에서 window를 만들고 있었으나, 메인에서 만들도록 한다.
//        frame.addWindowListener(lis);
//    }
    public void setFrame(JFrame frame){
        this.frame = frame;
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
