import java.util.ArrayList;


abstract class LogGate {

    private ArrayList<Dot> operands;

    abstract boolean calculateValue();

    void addOperand(boolean inversion, Dot operand){
        Dot newDot = new Dot(inversion, operand);
        operands.add(newDot);
    }

    void deleteOperand(Dot operand){
        operands.remove(operands.indexOf(operand));
    }
}
