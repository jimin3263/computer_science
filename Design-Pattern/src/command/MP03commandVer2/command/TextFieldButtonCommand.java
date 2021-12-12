package command.MP03commandVer2.command;

import command.MP03commandVer2.observer.TextFieldWindow;
import command.MP03commandVer2.subject.PrimeObservableThread;

import javax.swing.*;

public class TextFieldButtonCommand extends JButton implements Command {
    private TextFieldWindow textFieldWindow;
    private PrimeObservableThread primeThread;
    private boolean TextFieldObserverAdded= true;
    private static final String REMOVE_TEXTFIELD_OBSERVER_BUTTON_TITLE = "Remove TextField Window Observer";
    private static final String ADD_TEXTFIELD_OBSERVER_BUTTON_TITLE = "Add TextField Window Observer";

    public TextFieldButtonCommand(TextFieldWindow textFieldWindow, PrimeObservableThread primeThread) {
        super();
        this.textFieldWindow = textFieldWindow;
        this.primeThread = primeThread;
    }

    @Override
    public void execute() {
        if (TextFieldObserverAdded){
            primeThread.removeObserver(textFieldWindow);
            TextFieldObserverAdded = false;
            setText(ADD_TEXTFIELD_OBSERVER_BUTTON_TITLE);
        }
        else{
            primeThread.registerObserver(textFieldWindow);
            TextFieldObserverAdded = true;
            setText(REMOVE_TEXTFIELD_OBSERVER_BUTTON_TITLE);
        }
    }
}
