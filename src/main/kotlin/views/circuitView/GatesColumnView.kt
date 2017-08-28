package views.circuitView

import javafx.scene.shape.Line
import logic.Dot

class GatesColumnView(var x: Double) {
    var height = 30.0
    var width = 60.0
    val gatesView = mutableListOf<GateView>()
    val localBuses = mutableMapOf<Dot.Out, Line>()

    fun addGateView(gateView: GateView){
        gatesView.add(gateView)
        gateView.setLayoutY(this.height + 10.0)
        gateView.setLayoutX(this.x)
        height += gateView.getHeight()
    }

    fun addLocalBus(){

    }

    fun moveAll(difference : Double){
        for (element in gatesView){
            element.changeLayoutX(difference)
        }
        x += difference
    }

    fun remove(i: Int) : GateView{
        val gateView = gatesView[i]
        height -= gatesView[i].getHeight()
        gatesView.removeAt(i)
        return gateView
    }
}