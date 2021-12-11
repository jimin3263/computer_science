package state.MP09.MP09Student.state;

import state.MP09.MP09Student.CalcV1;

public class OperatorState implements State{
    CalcV1 calcV1;

    public OperatorState(CalcV1 calcV1) {
        this.calcV1 = calcV1;
    }

    @Override
    public void processNumber(String ch) {
        calcV1.setOperand2(Integer.parseInt(ch));
        calcV1.setState(calcV1.getSecondOperandState());
    }
}
