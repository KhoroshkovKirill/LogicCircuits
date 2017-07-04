package views.circuitView

import logic.Gate

class GatesCircuitView(var x : Double, val circuitView : CircuitView) {
    var width = 40.0
    val gatesColumnView = mutableListOf(GatesColumnView())

    fun addGateView(gate: Gate){
        val y = this.gatesColumnView[0].height + 10.0
        val gateView =
                when (gate){
                    is Gate.Not -> GateView.Not(x,y,gate)
                    is Gate.Multivariate -> GateView.Multivariate(x,y,gate)
                }
        this.gatesColumnView[0].addGateView(gateView)
        circuitView.children.addAll(gateView.getShapes())
    }

    fun changeLayoutAllX(difference : Double){
        for (element in gatesColumnView){
            element.moveAll(difference)
        }
        this.x += difference
    }

    fun addColumn(){

    }
}