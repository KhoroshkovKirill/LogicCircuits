package logic

class Circuit {
    val inBuses = mutableSetOf<Bus.In>()
    val gates = mutableListOf<Gate>()
    val outBus = Bus.Out()

    fun addBus(bus: Bus.In){
            inBuses.add(bus)
    }

    fun addGate(gate: Gate){
        gates.add(gate)
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