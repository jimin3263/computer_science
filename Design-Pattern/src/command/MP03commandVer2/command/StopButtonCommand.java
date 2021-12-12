package command.MP03commandVer2.command;

import command.MP03commandVer2.subject.PrimeObservableThread;

import javax.swing.*;

public class StopButtonCommand extends JButton implements Command {
    private PrimeObservableThread primeThread;

    public StopButtonCommand(PrimeObservableThread primeThread) {
        super();
        this.primeThread = primeThread;
    }

    @Override
    public void execute() {
        primeThread.stopRunning();
    }
}
