package views.circuitView

import logic.Gate

class GatesCircuitView(val x : Double, val circuitView : CircuitView) {
    var width = 40.0
    val gatesRowsView = mutableListOf(GatesRowView())

    fun addGateView(gate: Gate){
        val x = circuitView.inBusesView.width
        val y = this.gatesRowsView[0].height + 10.0
        val gateView =
                when (gate){
                    is Gate.Not -> GateView.Not(x,y,gate)
                    is Gate.Multivariate -> GateView.Multivariate(x,y,gate)
                }
        this.gatesRowsView[0].addGateView(gateView)
        circuitView.children.addAll(gateView.getShapes())
    }

    fun moveAll(difference : Double){
        for (element in gatesRowsView){
            element.moveAll(difference)
        }
    }
}