package state.MP09.MP09Student.state;

import state.MP09.MP09Student.CalcV1;

public class StartState implements State{
    CalcV1 calcV1;

    public StartState(CalcV1 calcV1) {
        this.calcV1 = calcV1;
    }

    @Override
    public void processNumber(String ch) { //시작상태에서 숫자를 누르면 firstOperand상태로 전환
        calcV1.setOperand1(Integer.parseInt(ch));
        calcV1.setState(calcV1.getFirstOperandState());
    }

}
