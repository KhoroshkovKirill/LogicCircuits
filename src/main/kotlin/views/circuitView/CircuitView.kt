package views.circuitView

import javafx.scene.control.ScrollPane
import logic.Circuit
import logic.Gate

class CircuitView : ScrollPane(){
    val circuit = Circuit()

   /* fun addBusView(element : String){
        circuit.addBus(element)
    }*/

    fun addGateView(gate: Gate){
        circuit.addGate(gate)
    }

}