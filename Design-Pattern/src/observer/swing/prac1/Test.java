package observer.swing.prac1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Test {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setTitle("main");
        frame.setSize(600,400);

        //panel에 추가
        JPanel panel = new JPanel();
        JButton button1 = new JButton("hello1");
        button1.addActionListener(new ButtonListener());
        JButton button2 = new JButton("hello2");
        panel.add(button1);
        panel.add(button2);

        Container contentPane = frame.getContentPane();
        contentPane.add(panel);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //종료
    }
}

//이벤트 처리 방식 -> 옵저버
//특정 이벤트 발생 -> 함수 호출
class ButtonListener implements ActionListener{ //옵저버, 버튼 -> subject
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("button was clicked"); //이벤트 호출하면 나를 불러줭.
    }
}
