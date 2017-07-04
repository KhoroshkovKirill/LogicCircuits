package views.circuitView

import javafx.scene.shape.Line
import logic.Dot

class GatesRowView {
    var height = 30.0
    val gatesView = mutableListOf<GateView>()
    val localBuses = mutableMapOf<Dot.Out, Line>()

    fun addGateView(gateView: GateView){
        gatesView.add(gateView)
    }

    fun addLocalBus(){

    }

    fun moveAll(difference : Double){
        for (element in gatesView){
            element.move(difference)
        }
    }
}