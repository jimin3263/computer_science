package command.MP03command.command;

import command.MP03command.subject.PrimeObservableThread;

public class StopButtonCommand implements Command{
    private PrimeObservableThread primeThread;

    public StopButtonCommand(PrimeObservableThread primeThread) {
        this.primeThread = primeThread;
    }

    @Override
    public void execute() {
        primeThread.stopRunning();
    }
}
