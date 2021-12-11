package command.MP08.MP08student.command;

import command.MP08.MP08student.CalcGUIV1;
import command.MP08.MP08student.Calculator;


public class NumButton extends CommandButton{

    private CalcGUIV1 display;

    public NumButton( Calculator calculator, CalcGUIV1 display) {
        super(calculator);
        this.display = display;

    }

    @Override
    public void execute() {
        Calculator calculator = getCalculator();
        if (calculator.isOperand1Set() && calculator.isOperatorSet()) { // 첫 번째 피연산자와 연산자가 지정되었다면 두 번째 피연산자 값으로 사용
            int num2 = Integer.parseInt(getText());
            calculator.setOperand2(num2);
            display.showText("" + num2);
            calculator.setOperand2Set(true);
        }
        else {  // 첫 번째 피연산자 값 지정
            int num1 = Integer.parseInt(getText());
            display.showText("" + num1);
            calculator.setOperand1(num1);
            calculator.setOperand1Set(true);
        }
    }
}
