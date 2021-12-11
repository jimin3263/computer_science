package state.quarter.state;

public interface State {
    public void insertQuarter(); // 동전 투입
    public void turnCrank(); //손잡이 돌림
    public void dispense(); //알맹이 내보냄
    public void ejectQuarter(); //동전 반환
}