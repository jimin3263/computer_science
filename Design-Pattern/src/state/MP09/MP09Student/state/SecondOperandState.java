package state.MP09.MP09Student.state;

import state.MP09.MP09Student.CalcV1;

public class SecondOperandState implements State {
    CalcV1 calcV1;

    public SecondOperandState(CalcV1 calcV1) {
        this.calcV1 = calcV1;
    }

    @Override
    public void processOperator(char ch) {
        if (ch == '=') {
            calcV1.printOutResult();
            calcV1.setState(calcV1.getStartState());
        }

    }

}
