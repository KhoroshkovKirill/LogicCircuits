package views.circuitView

import javafx.scene.layout.Pane
import logic.Bus
import logic.Circuit
import logic.Gate

class CircuitView : Pane(){
    val circuit = Circuit()
    val inBusesView = InBusesView(this)
    val outBusView = BusView.IO.Out("Y", 20.0, circuit.outBus)
    val gatesRowsViev = mutableListOf<GatesRowView>()
    init {
        this.children.addAll(outBusView.getShapes())
    }

    fun addBusView(name : String) {
        if (name == "") {
            throw IllegalArgumentException("Шину следует назвать")
        } else {
            val bus = Bus.In()
            circuit.addBus(bus)
            outBusView.move(inBusesView.add(name, bus))

            //-gatemove
        }
    }

    fun renameInBus(index: Int, newName : String) {
        if (newName == "") {
            throw IllegalArgumentException("Шину следует назвать")
        } else {
            outBusView.move(inBusesView.rename(index,newName))
            //-gatemove
        }
    }

    fun deleteInBus(index: Int){
        try {
            val difference = -inBusesView.busList[index].getWidth()
            this.children.removeAll(inBusesView.remove(index))
            outBusView.move(difference)
            //-gatemove
        }
        catch (ex : IndexOutOfBoundsException){
            throw IndexOutOfBoundsException("Выход за предел списка")
        }
    }

    fun renameOutBus(newName: String){
        if (newName == "") {
            throw IllegalArgumentException("Шину следует назвать")
        } else {
            outBusView.rename(newName)
        }
    }

    fun addGateView(gate: Gate){
        circuit.addGate(gate)
    }

}