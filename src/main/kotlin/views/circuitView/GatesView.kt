package views.circuitView

import logic.Gate

class GatesView(var x : Double, val circuitView : CircuitView) {
    var width = 40.0
    val gatesColumnView = mutableListOf(GatesColumnView(x))

    fun addGateView(gate: Gate){
        val gateView =
                when (gate){
                    is Gate.Not -> GateView.Not(gate)
                    is Gate.Multivariate -> GateView.Multivariate(gate)
                }
        circuitView.children.addAll(gateView.getShapes())
        this.putAt(0, gateView)
    }

    fun putAt(columnIndex: Int, gateView: GateView) : Double{
        var difference = 0.0
        if (columnIndex >= gatesColumnView.size){
            this.addColumn()
            difference = gatesColumnView[columnIndex].width
        }
        this.gatesColumnView[columnIndex].addGateView(gateView)
        return difference
    }

    fun changeLayoutAllX(difference : Double){
        for (element in gatesColumnView){
            element.moveAll(difference)
        }
        this.x += difference
    }

    fun addColumn() : Double{
        val column = GatesColumnView(this.width)
        gatesColumnView.add(column)
        this.width += column.width
        return column.width
    }

    fun remove(i: Int, j: Int) : GateView{
        return gatesColumnView[j].remove(i)
    }

}