package views.circuitView

import javafx.scene.layout.Pane
import logic.Bus
import logic.Circuit
import logic.Gate

class CircuitView : Pane(){
    val circuit = Circuit()
    val inBusesView = InBusesView(this)
    val gatesView = GatesCircuitView(inBusesView.width, this)
    val outBusView = BusView.IO.Out(
            "Y",
            inBusesView.width + gatesView.width + 20.0,
            circuit.outBus
    )
    init {
        this.children.addAll(outBusView.getShapes())
    }

    fun addBusView(name : String) {
        if (name == "") {
            throw IllegalArgumentException("Шину следует назвать")
        } else {
            val bus = Bus.In()
            circuit.addBus(bus)
            val difference = inBusesView.add(name, bus)
            outBusView.changeLayoutX(difference)
            gatesView.changeLayoutAllX(difference)
        }
    }

    fun renameInBus(index: Int, newName : String) {
        if (newName == "") {
            throw IllegalArgumentException("Шину следует назвать")
        } else {
            val difference = inBusesView.rename(index,newName)
            outBusView.changeLayoutX(difference)
            gatesView.changeLayoutAllX(difference)
        }
    }

    fun deleteInBus(index: Int){
        try {
            val difference = -inBusesView.busList[index].getWidth()
            this.children.removeAll(inBusesView.remove(index))
            outBusView.changeLayoutX(difference)
            gatesView.changeLayoutAllX(difference)
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
        gatesView.addGateView(gate)
    }

}