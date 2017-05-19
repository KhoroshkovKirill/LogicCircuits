
public class OutBus extends InBus {

    LogElement previous;

    public boolean calculateValue(){
        value = previous.calculateValue();
        return value;
    }
}
