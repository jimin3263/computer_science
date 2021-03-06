package command.MP03commandVer2.observer;

import javax.swing.*;
import java.awt.*;


public class TextFieldWindow extends FrameWindow implements Observer {
    private JTextField textField;

    public TextFieldWindow(String title, int x, int y, int width, int height) {
        super(title, x, y, width, height);
    }

    public void updateText(String msg) {
        textField.setText(msg);
        textField.validate();
    }

    public JPanel createPanel(int width, int height) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        textField = new JTextField();
        panel.add(textField); //νλ μΆκ°
        panel.setPreferredSize(new Dimension(width, height));
        return panel;
    }

}
