package views.circuitView

import javafx.scene.layout.Pane
import logic.Bus
import logic.Circuit
import logic.Gate

class CircuitView : Pane(){
    val circuit = Circuit()
    val inBusesView = InBusesView(this)
    val gatesView = GatesView(inBusesView.width, this)
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
            gatesView.moveNextColumns(0, difference)
        }
    }

    fun renameInBus(index: Int, newName : String) {
        if (newName == "") {
            throw IllegalArgumentException("Шину следует назвать")
        } else {
            val difference = inBusesView.rename(index,newName)
            outBusView.changeLayoutX(difference)
            gatesView.moveNextColumns(0, difference)
        }
    }

    fun deleteInBus(index: Int){
        try {
            val difference = -inBusesView.busList[index].getWidth()
            circuit.delete(inBusesView.busList[index].bus)
            this.children.removeAll(inBusesView.remove(index))
            outBusView.changeLayoutX(difference)
            gatesView.moveNextColumns(0, difference)
        }
        catch (ex : IndexOutOfBoundsException){
            throw IndexOutOfBoundsException("Выход за предел списка")
        }
    }

    fun deleteGate(i: Int, j: Int){
        val gateView = gatesView.getGateView(i, j)
        circuit.delete(gateView.gate)
        this.children.removeAll(gateView.getShapes())
        outBusView.changeLayoutX(gatesView.removeGate(i, j))
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
        val gateView =
                when (gate){
                    is Gate.Not -> GateView.Not(gate)
                    is Gate.Multivariate -> GateView.Multivariate(gate)
                }
        this.children.addAll(gateView.getShapes())
        gatesView.addGateView(gateView)
    }

    fun shiftGate(i: Int, j: Int, newColumn: Int) : Double{
        val gateView = gatesView.getGateView(i, j)
        val difference = gatesView.removeGate(i,j)
        this.outBusView.changeLayoutX(difference)
        return gatesView.putAt(newColumn,gateView)
    }

}