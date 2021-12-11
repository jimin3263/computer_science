package command.MP08.MP08student.command;

import command.MP08.MP08student.Calculator;

import javax.swing.*;

public abstract class CommandButton extends JButton implements Command {

    private Calculator calculator;

    public CommandButton(Calculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public abstract void execute();

    public Calculator getCalculator() {
        return calculator;
    }
}
