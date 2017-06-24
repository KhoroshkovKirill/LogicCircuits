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

    fun deleteBus(bus: Bus.In){
        if (inBuses.contains(bus)){
            bus.outPut.deleteFromPrevious()
            inBuses.remove(bus)
        }
        else {
            throw IllegalArgumentException("Такой шины не существует")
        }
    }

    fun deleteGate(gate: Gate){
        if (gates.contains(gate)){
            gate.prepareToDelete()
            gates.remove(gate)
        }
        else{
            throw IllegalArgumentException("Такого элемента не существует")
        }
    }

}