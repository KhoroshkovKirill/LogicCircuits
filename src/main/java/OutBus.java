public class OutBus extends InBus {

    LogElement previous;

    public OutBus(String name, boolean value) {
        super(name,value);
    }

    public boolean calculateValue(){
        try {
            this.setValue(previous.calculateValue());
            return getValue();
        }
        catch(NullPointerException ex){
            throw new IllegalArgumentException("К выходной шине " + this.getName() + " не подведено соединение");
        }
    }
}
