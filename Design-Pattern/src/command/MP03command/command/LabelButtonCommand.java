package command.MP03command.command;

import command.MP03command.observer.LabelWindow;
import command.MP03command.subject.PrimeObservableThread;

import javax.swing.*;

public class LabelButtonCommand implements Command{

    private static final String REMOVE_LABEL_OBSERVER_BUTTON_TITLE = "Remove Label Window Observer";
    private static final String ADD_LABEL_OBSERVER_BUTTON_TITLE = "Add Label Window Observer";
    private boolean LabelObserverAdded= true;
    private PrimeObservableThread primeThread;
    private LabelWindow labelWindow;
    private JButton updateLabelObserverButton;

    public LabelButtonCommand(PrimeObservableThread primeThread, LabelWindow labelWindow, JButton updateLabelObserverButton) {
        this.primeThread = primeThread;
        this.labelWindow = labelWindow;
        this.updateLabelObserverButton = updateLabelObserverButton;
    }

    @Override
    public void execute() {
        if (LabelObserverAdded){
            primeThread.removeObserver(labelWindow);
            LabelObserverAdded = false;
            updateLabelObserverButton.setText(ADD_LABEL_OBSERVER_BUTTON_TITLE);
        }
        else{
            primeThread.registerObserver(labelWindow);
            LabelObserverAdded = true;
            updateLabelObserverButton.setText(REMOVE_LABEL_OBSERVER_BUTTON_TITLE);
        }
    }
}
