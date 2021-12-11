package command.MP03command.command;

import command.MP03command.observer.TextFieldWindow;
import command.MP03command.subject.PrimeObservableThread;

import javax.swing.*;

public class TextFieldButtonCommand implements Command{
    private TextFieldWindow textFieldWindow;
    private PrimeObservableThread primeThread;
    private boolean TextFieldObserverAdded= true;
    private JButton updateTextFieldObserverButton;
    private static final String REMOVE_TEXTFIELD_OBSERVER_BUTTON_TITLE = "Remove TextField Window Observer";
    private static final String ADD_TEXTFIELD_OBSERVER_BUTTON_TITLE = "Add TextField Window Observer";

    public TextFieldButtonCommand(TextFieldWindow textFieldWindow, PrimeObservableThread primeThread,
                                  JButton updateTextFieldObserverButton) {
        this.textFieldWindow = textFieldWindow;
        this.primeThread = primeThread;
        this.updateTextFieldObserverButton = updateTextFieldObserverButton;
    }

    @Override
    public void execute() {
        if (TextFieldObserverAdded){
            primeThread.removeObserver(textFieldWindow);
            TextFieldObserverAdded = false;
            updateTextFieldObserverButton.setText(ADD_TEXTFIELD_OBSERVER_BUTTON_TITLE);
        }
        else{
            primeThread.registerObserver(textFieldWindow);
            TextFieldObserverAdded = true;
            updateTextFieldObserverButton.setText(REMOVE_TEXTFIELD_OBSERVER_BUTTON_TITLE);
        }
    }
}
