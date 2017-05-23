
public class Dot implements LogElement{
    boolean inversion;
    LogElement previous ;

    Dot(boolean inversion, LogElement previous){
        this.inversion = inversion;
        this.previous = previous;
    }

    public boolean calculateValue(){
        try {
            return previous.calculateValue() ^ inversion;
        }
        catch (NullPointerException ex){
            throw new IllegalArgumentException("К логическому элементу не подведено соединение");
        }
    }
}
