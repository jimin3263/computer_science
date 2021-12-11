package state.MP09.MP09Student.state;

public interface State {
    default void processOperator(char ch){
        System.out.println("processOperator: Invalid operation");
    }
    default void processNumber(String ch){
        System.out.println("processNumber: Invalid operation");
    }

}
