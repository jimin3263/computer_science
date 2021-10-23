package observer.swing.prac2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Test implements ActionListener {
    JButton b1 = new JButton("hello 1");
    JButton b2 = new JButton("hello 2");

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton b = (JButton) e.getSource();
        if (b == b1 ){
            System.out.println("button1 was clicked");
        }
        else if (b == b2){
            System.out.println("button2 was clicked");
        }
    }

    public static void main(String[] args) {
        Test t = new Test();
        t.extracted();
    }

    private void extracted() {
        JFrame frame = new JFrame();
        frame.setTitle("main");
        frame.setSize(600,400);

        //panel에 추가
        JPanel panel = new JPanel();

        b1.addActionListener(this);
        b2.addActionListener(this);

        b1.setPreferredSize(new Dimension(1000,100)); // 크기 지정

        panel.add(b1);
        panel.add(b2);

        Container contentPane = frame.getContentPane();
        contentPane.add(panel);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //종료
    }


}


