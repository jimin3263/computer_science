package state.quarter.state;

import state.quarter.GumballMachine;

//동전이 없는 경우
public class NoQuarterState implements State{
    GumballMachine gumballMachine;

    public NoQuarterState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
    public void insertQuarter() { //동전을 넣으면 -> HasQuarterState로 전환
        System.out.println("동전을 넣으셨습니다.");
        gumballMachine.setState(gumballMachine.getHasQuarterState());
    }

    @Override
    public void turnCrank() {
        System.out.println("동전을 넣어주세요.");

    }

    @Override
    public void dispense() {
        System.out.println("동전을 넣어주세요.");
    }

    @Override
    public void ejectQuarter() {
        System.out.println("동전을 넣어주세요.");

    }
}
