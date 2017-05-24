/**
 * Created by khoroshkovkirill on 22.05.17.
 */
public class NotGate extends Gate{
    private Dot.InDot in;


    NotGate(Dot.OutDot in){
        this.in = new Dot.InDot(false,in);
        this.out = new Dot.OutDot(true,this);
    }

    public boolean calculateValue() {
        return in.calculateValue();
    }
}
