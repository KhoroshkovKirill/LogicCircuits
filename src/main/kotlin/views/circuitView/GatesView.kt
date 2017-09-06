package views.circuitView

import javafx.scene.shape.Line


class GatesView(var x : Double, val circuitView : CircuitView) {
    var width = 0.0
    val gatesColumnView = mutableListOf<GatesColumnView>()

    fun addGateView(gateView: GateView) : Double{
        return this.putAt(0, gateView)
    }

    fun putAt(columnIndex: Int, gateView: GateView) : Double{
        var difference = 0.0
        if (columnIndex >= gatesColumnView.size){
            difference = this.addColumn()
        }
        gateView.j = columnIndex
        this.gatesColumnView[columnIndex].addGateView(gateView)
        return difference
    }

    fun moveColumnsFrom(index: Int, difference : Double){
        for (i in index..gatesColumnView.size - 1){
            gatesColumnView[i].changeLayoutX(difference)
        }
        this.x += difference
    }

    fun addColumn() : Double{
        val column = GatesColumnView(this.x + this.width)
        gatesColumnView.add(column)
        this.width += column.width
        return column.width
    }

    fun removeGate(i: Int, j: Int) : Double{
        var difference = 0.0
        gatesColumnView[j].remove(i)
        if (gatesColumnView[j].gatesView.isEmpty()){
            difference = this.removeColumn(j)
        }
        else{
            redrawLocalBuses(j)
        }
        return difference
    }

    fun redrawLocalBuses(column: Int){
        gatesColumnView[column].redrawLocalBuses()
        if (column != 0){
            gatesColumnView[column - 1].redrawLocalBuses()
        }
    }

    fun drawLineThrow(column: Int, y: Double, line: Line) : Double{
        val newY = gatesColumnView[column].putLine(y, line)
        redrawLocalBuses(column)
        return newY
    }

    fun removeColumn(index: Int) : Double{
        val difference = -gatesColumnView[index].width
        gatesColumnView.removeAt(index)
        for (i in index..gatesColumnView.lastIndex){
            for (element in gatesColumnView[i].gatesView){
                element.j--
            }
        }
        this.moveColumnsFrom(index, difference)
        return difference
    }

    fun getGateView(i: Int, j: Int) : GateView{
        return gatesColumnView[j].gatesView[i]
    }

}