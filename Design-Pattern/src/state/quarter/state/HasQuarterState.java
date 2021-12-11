package state.quarter.state;

import state.quarter.GumballMachine;

//동전을 가지고 있을 때
public class HasQuarterState implements State{
    GumballMachine gumballMachine;

    public HasQuarterState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
    public void insertQuarter() {
        System.out.println("동전은 하나만 넣어주세요");
    }

    @Override
    public void turnCrank() { //판매 상태로 전환
        System.out.println("손잡이를 돌리셨습니다.");
        gumballMachine.setState(gumballMachine.getSoldState());
    }

    @Override
    public void dispense() {
        //손잡이를 돌린 후 알맹이를 내보내야하므로 나갈 수 없는 상태.
        System.out.println("알맹이가 나갈 수 없습니다.");

    }

    @Override
    public void ejectQuarter() { //동전 없는 상태로
        System.out.println("동전이 반환됩니다.");
        gumballMachine.setState(gumballMachine.getNoQuarterState());

    }
}
