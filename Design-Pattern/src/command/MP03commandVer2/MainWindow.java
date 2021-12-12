package command.MP03commandVer2;


import command.MP03commandVer2.command.Command;
import command.MP03commandVer2.command.LabelButtonCommand;
import command.MP03commandVer2.command.StopButtonCommand;
import command.MP03commandVer2.command.TextFieldButtonCommand;
import command.MP03commandVer2.observer.FrameWindow;
import command.MP03commandVer2.observer.LabelWindow;
import command.MP03commandVer2.observer.TextFieldWindow;
import command.MP03commandVer2.subject.PrimeObservableThread;

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

    private static final String REMOVE_TEXTFIELD_OBSERVER_BUTTON_TITLE = "Remove TextField Window Observer";
    private static final String REMOVE_LABEL_OBSERVER_BUTTON_TITLE = "Remove Label Window Observer";

    private JButton updateTextFieldObserverButton;
    private JButton stopButton;
    private JButton updateLabelObserverButton;

    private PrimeObservableThread primeThread;
    private TextFieldWindow textFieldWindow;
    private LabelWindow labelWindow;



    public MainWindow(String title) {
        super(title, X, Y, WIDTH, HEIGHT); //젤 위창 -> createPanel포함함
        primeThread = new PrimeObservableThread(); // 객체 생성


        textFieldWindow = new TextFieldWindow(TEXTFIELD_WINDOW_TITLE, X, Y + HEIGHT + GAP, WIDTH, HEIGHT); //두번째 창
        labelWindow = new LabelWindow(LABEL_WINDOW_TITLE, X, Y + (HEIGHT + GAP) * 2, WIDTH, HEIGHT);//세번째창 만듦

        JFrame frame = createWindow(title, X, Y, WIDTH, HEIGHT);
        setFrame(frame);

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

        primeThread.registerObserver(textFieldWindow); //subject에 등록
        primeThread.registerObserver(labelWindow);



        primeThread.run();  // 소수 생성 시작,
    }

    public JPanel createPanel(int width, int height) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setPreferredSize(new Dimension(width, height));

        updateTextFieldObserverButton =  new TextFieldButtonCommand(textFieldWindow,primeThread);
        modifyButton(updateTextFieldObserverButton,REMOVE_TEXTFIELD_OBSERVER_BUTTON_TITLE, this, width, height);
        panel.add(updateTextFieldObserverButton);

        updateLabelObserverButton = new LabelButtonCommand(primeThread,labelWindow);
        modifyButton(updateLabelObserverButton,REMOVE_LABEL_OBSERVER_BUTTON_TITLE, this, width, height);
        panel.add(updateLabelObserverButton);

        stopButton = new StopButtonCommand(primeThread);
        modifyButton(stopButton, STOP_THREAD_BUTTON_TITLE, this, width, height);
        panel.add(stopButton);

        return panel;
    }

    //수정할 수 있는 부분
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() instanceof Command){ //Command를 implement하는 JButton
            ((Command) e.getSource()).execute();
        }
    }

    private void modifyButton (JButton button, String text, ActionListener listener, int width, int height) {
        button.setText(text);
        button.addActionListener(listener);
        Dimension buttonDimension = new Dimension(width, height / 3);
        button.setMaximumSize(buttonDimension);
        button.setMinimumSize(buttonDimension);
        button.setPreferredSize(buttonDimension);
    }

    public static void main(String[] args) {
        MainWindow mainWindow = new MainWindow(MainWindow.MAIN_TITLE);
    }
}
