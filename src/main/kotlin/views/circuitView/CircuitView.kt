package views.circuitView

import javafx.scene.layout.Pane
import logic.Bus
import logic.Circuit
import logic.Gate

class CircuitView : Pane(){
    val circuit = Circuit()
    val inBusesView = mutableListOf<BusView>()
    val outBusView = BusView("Y", 20.0, circuit.outBus)
    var widthOfBusesView = 10.0
    var widthToOutGate = 20.0
    init {
        this.children.addAll(outBusView.nameText, outBusView.line)
    }

    fun addBusView(name : String) {
        if (name == "") {
            throw IllegalArgumentException("Шину следует назвать")
        } else {
            val bus = Bus.In()
            val busView = BusView(name,widthOfBusesView,bus)
            circuit.addBus(bus)
            inBusesView.add(busView)
            this.children.addAll(busView.nameText, busView.line)
            widthOfBusesView += busView.nameText.layoutBounds.width + 5.0
            widthToOutGate += busView.nameText.layoutBounds.width + 5.0
            moveOutBus()
        }
    }

    fun renameInBus(index: Int, newName : String) {
        if (newName == "") {
            throw IllegalArgumentException("Шину следует назвать")
        } else {
            try {
                val difference = inBusesView[index - 1].rename(newName)
                widthOfBusesView += difference
                widthToOutGate += difference
                moveNextInBuses(index , difference)
                moveOutBus()
            } catch (ex: IndexOutOfBoundsException) {
                throw IllegalArgumentException("Выход за предел списка")
            }
        }
    }

    fun renameOutBus(newName: String){
        if (newName == "") {
            throw IllegalArgumentException("Шину следует назвать")
        } else {
            outBusView.rename(newName)
        }
    }

    fun moveNextInBuses(index: Int, difference : Double){
        for (i in index..(inBusesView.lastIndex) ){
            this.inBusesView[i].line.startX += difference
            this.inBusesView[i].line.endX += difference
            this.inBusesView[i].nameText.layoutX += difference
        }
    }

    fun moveOutBus(){
        outBusView.moveTo(widthToOutGate)
    }

    fun addGateView(gate: Gate){
        circuit.addGate(gate)
    }

}