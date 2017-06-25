package views.circuitView

import javafx.scene.layout.Pane
import logic.Bus
import logic.Circuit
import logic.Gate

class CircuitView : Pane(){
    val circuit = Circuit()
    val inBusesView = mutableMapOf<String,BusView>()
    val outBusView = BusView("Y",20.0,circuit.outBus)
    var widthOfBusesView = 10.0
    init {
        this.children.addAll(outBusView.nameText, outBusView.line)
    }

    fun addBusView(name : String) {
        if (name == "") {
            throw IllegalArgumentException("Шину следует назвать")
        } else if (inBusesView.containsKey(name)) {
            throw IllegalArgumentException("Шина с таким именем уже существует")
        } else {
            val bus = Bus.In()
            val busView = BusView(name,widthOfBusesView,bus)
            circuit.addBus(bus)
            inBusesView.put(name, busView)
            this.children.addAll(busView.nameText, busView.line)
            widthOfBusesView += busView.nameText.layoutBounds.width + 5.0
        }
    }

    fun addGateView(gate: Gate){
        circuit.addGate(gate)
    }

}