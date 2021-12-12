package command.MP03commandVer2.command;

import command.MP03commandVer2.observer.LabelWindow;
import command.MP03commandVer2.subject.PrimeObservableThread;

import javax.swing.*;

public class LabelButtonCommand extends JButton  implements Command {

    private static final String REMOVE_LABEL_OBSERVER_BUTTON_TITLE = "Remove Label Window Observer";
    private static final String ADD_LABEL_OBSERVER_BUTTON_TITLE = "Add Label Window Observer";
    private boolean LabelObserverAdded= true;
    private PrimeObservableThread primeThread;
    private LabelWindow labelWindow;

    public LabelButtonCommand(PrimeObservableThread primeThread, LabelWindow labelWindow) {
        super();
        this.primeThread = primeThread;
        this.labelWindow = labelWindow;
    }

    @Override
    public void execute() {
        if (LabelObserverAdded){
            primeThread.removeObserver(labelWindow);
            LabelObserverAdded = false;
            setText(ADD_LABEL_OBSERVER_BUTTON_TITLE);
        }
        else{
            primeThread.registerObserver(labelWindow);
            LabelObserverAdded = true;
            setText(REMOVE_LABEL_OBSERVER_BUTTON_TITLE);
        }
    }
}
