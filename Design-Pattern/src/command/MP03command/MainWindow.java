package command.MP03command;


import command.MP03command.command.LabelButtonCommand;
import command.MP03command.command.StopButtonCommand;
import command.MP03command.command.TextFieldButtonCommand;
import command.MP03command.observer.FrameWindow;
import command.MP03command.observer.LabelWindow;
import command.MP03command.observer.TextFieldWindow;
import command.MP03command.subject.PrimeObservableThread;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainWindow extends FrameWindow implements ActionListener {
    private static final String MAIN_TITLE = "Main Window";
    private static final String TEXTFIELD_WINDOW_TITLE = "TextField Window";
    private static final String LABEL_WINDOW_TITLE = "Label Window";
    private static final String STOP_THREAD_BUTTON_TITLE = "Stop Generating Prime Number";
    private static final int X = 250;
    private static final int Y = 100;
    private static final int WIDTH = 600;
    private static final int HEIGHT = 200;
    private static final int GAP = 50;
    private JButton updateTextFieldObserverButton;
    private static final String REMOVE_TEXTFIELD_OBSERVER_BUTTON_TITLE = "Remove TextField Window Observer";
    private static final String REMOVE_LABEL_OBSERVER_BUTTON_TITLE = "Remove Label Window Observer";

    private JButton stopButton;
    private JButton updateLabelObserverButton;
    private PrimeObservableThread primeThread;
    private TextFieldWindow textFieldWindow;
    private LabelWindow labelWindow;

    private TextFieldButtonCommand textFieldButtonCommand;
    private LabelButtonCommand labelButtonCommand;
    private StopButtonCommand stopButtonCommand;


    public MainWindow(String title) {
        super(title, X, Y, WIDTH, HEIGHT); //젤 위창 -> createPanel포함함
        textFieldWindow = new TextFieldWindow(TEXTFIELD_WINDOW_TITLE, X, Y + HEIGHT + GAP, WIDTH, HEIGHT); //두번째 창
        labelWindow = new LabelWindow(LABEL_WINDOW_TITLE, X, Y + (HEIGHT + GAP) * 2, WIDTH, HEIGHT);//세번째창 만듦

        //버튼 클릭시
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                primeThread.stopRunning();
                textFieldWindow.closeWindow();
                labelWindow.closeWindow();
                System.exit(0);
            }
        });

        primeThread = new PrimeObservableThread(); // 객체 생성
        primeThread.registerObserver(textFieldWindow); //subject에 등록
        primeThread.registerObserver(labelWindow);

        textFieldButtonCommand = new TextFieldButtonCommand(textFieldWindow,primeThread,updateTextFieldObserverButton);
        labelButtonCommand = new LabelButtonCommand(primeThread,labelWindow,updateLabelObserverButton);
        stopButtonCommand = new StopButtonCommand(primeThread);

        primeThread.run();  // 소수 생성 시작,
    }

    public JPanel createPanel(int width, int height) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setPreferredSize(new Dimension(width, height));

        updateTextFieldObserverButton = createButton(REMOVE_TEXTFIELD_OBSERVER_BUTTON_TITLE, this, width, height);
        panel.add(updateTextFieldObserverButton);
        updateLabelObserverButton = createButton(REMOVE_LABEL_OBSERVER_BUTTON_TITLE, this, width, height);
        panel.add(updateLabelObserverButton);
        stopButton = createButton(STOP_THREAD_BUTTON_TITLE, this, width, height);
        panel.add(stopButton);
        return panel;
    }

    //수정할 수 있는 부분
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == updateTextFieldObserverButton) {
            textFieldButtonCommand.execute();
        }
        else if (e.getSource() == updateLabelObserverButton) {
            labelButtonCommand.execute();
        }
        else if (e.getSource() == stopButton) {
           stopButtonCommand.execute();
        }
    }

    private JButton createButton(String text, ActionListener listener, int width, int height) {
        JButton button = new JButton(text);
        button.addActionListener(listener);
        Dimension buttonDimension = new Dimension(width, height / 3);
        button.setMaximumSize(buttonDimension);
        button.setMinimumSize(buttonDimension);
        button.setPreferredSize(buttonDimension);
        return button;
    }

    public static void main(String[] args) {
        MainWindow mainWindow = new MainWindow(MainWindow.MAIN_TITLE);
    }
}
