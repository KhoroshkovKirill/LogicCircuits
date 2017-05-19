
public class Dot implements LogElement{
    boolean inversion;
    LogElement previous ;

    Dot(boolean inversion, LogElement previous){
        this.inversion = inversion;
        this.previous = previous;
    }

    public boolean calculateValue(){
        return previous.calculateValue() ^ inversion;
    }
}
