package views.circuitView

import javafx.scene.shape.Line
import logic.Dot
class GatesColumnView(var x: Double) {
    var height = 30.0
    var width = 60.0
    val gatesView = mutableListOf<GateView>()
    val localBuses = mutableMapOf<Dot.Out, Line>()

    fun addGateView(gateView: GateView){
        gateView.i = gatesView.size
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

    fun moveNextGates(index: Int, difference : Double){
        for (i in index..(gatesView.lastIndex)) {
            this.gatesView[i].changeLayoutY(difference)
            this.gatesView[i].i--
        }
    }

    fun remove(i: Int){
        val difference = -gatesView[i].getHeight()
        height += difference
        moveNextGates(i + 1, difference)
        gatesView.removeAt(i)
    }
}