package state.MP09.MP09Student.state;

import state.MP09.MP09Student.CalcV1;

public class FirstOperandState implements State{
    CalcV1 calcV1;

    public FirstOperandState(CalcV1 calcV1) {
        this.calcV1 = calcV1;
    }

    @Override
    public void processOperator(char ch) {
        calcV1.setOperator(ch);
        calcV1.setState(calcV1.getOperatorState());
    }

}
