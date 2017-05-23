/**
 * Created by khoroshkovkirill on 22.05.17.
 */
public class NotGate extends Gate{
    private Dot in;


    NotGate(LogElement in){
        this.in = new Dot(false,in);
        this.out = new Dot(true,this);
    }

    public boolean calculateValue() {
        return in.calculateValue();
    }
}
