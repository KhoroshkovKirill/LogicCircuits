package logic

class Circuit {
    val inBuses = mutableSetOf<Bus.In>()
    val gates = mutableSetOf<Gate>()
    val outBuses = mutableSetOf<Bus.Out>()

    fun add(bus: Bus.In){
        inBuses.add(bus)
    }

    fun add(gate: Gate){
        gates.add(gate)
    }

    fun add(bus: Bus.Out){
        outBuses.add(bus)
    }

    fun delete(bus: Bus.In){
        if (inBuses.contains(bus)){
            bus.prepareToDelete()
            inBuses.remove(bus)
        }
        else {
            throw IllegalArgumentException("Такой шины не существует")
        }
    }

    fun delete(bus: Bus.Out){
        if (outBuses.contains(bus)){
            bus.prepareToDelete()
            outBuses.remove(bus)
        }
        else {
            throw IllegalArgumentException("Такой шины не существует")
        }
    }

    fun delete(gate: Gate){
        if (gates.contains(gate)){
            gate.prepareToDelete()
            gates.remove(gate)
        }
        else{
            throw IllegalArgumentException("Такого элемента не существует")
        }
    }

}