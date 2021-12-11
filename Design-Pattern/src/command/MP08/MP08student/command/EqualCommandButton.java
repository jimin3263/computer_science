package command.MP08.MP08student.command;

import command.MP08.MP08student.CalcGUIV1;
import command.MP08.MP08student.Calculator;


public class EqualCommandButton extends CommandButton{
    private CalcGUIV1 display;

    public EqualCommandButton( Calculator calculator, CalcGUIV1 display) {
        super(calculator);
        this.display = display;
    }

    @Override
    public void execute() {
        int result = 0;
        Calculator calculator = getCalculator();
        char operator = calculator.getOperator();
        int operand1 = calculator.getOperand1();
        int operand2 = calculator.getOperand2();

        if (calculator.isOperand1Set() && calculator.isOperand2Set() && calculator.isOperatorSet()) { // 두 개 피 연산자값과 연산자가 지정되었다면
            if (operator  == '+') {
                result = operand1 + operand2;
            }
            else if (operator == '-') {
                result = operand1 - operand2;
            }
            else if (operator == '*') {
                result = operand1 * operand2;
            }
            else if (operator  == '/') {
                result = operand1 / operand2;
            }
        }
        display.showText("" + result);
        calculator.clearFlags();
    }
}
