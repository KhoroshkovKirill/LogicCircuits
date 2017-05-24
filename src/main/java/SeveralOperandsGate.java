import java.util.ArrayList;


public abstract class SeveralOperandsGate extends Gate{

    private ArrayList<Dot> operands;

    SeveralOperandsGate(ArrayList<LogElement> inList){
        this.operands = new ArrayList<Dot>();
        for (LogElement elem:inList) {
            if (elem instanceof Gate) this.operands.add(new Dot(false,elem.out));
            else if (elem instanceof InBus) operands.add(new Dot(false,elem));
        }
        this.out = new Dot(false, this);
    }

    public abstract boolean calculateValue();

    void addOperand(boolean inversion, Dot operand){
        Dot newDot = new Dot(inversion, operand);
        operands.add(newDot);
    }

    void deleteOperand(Dot operand){
        operands.remove(operands.indexOf(operand));
    }
}
