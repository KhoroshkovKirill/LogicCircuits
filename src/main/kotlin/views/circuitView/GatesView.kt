package views.circuitView


class GatesView(var x : Double, val circuitView : CircuitView) {
    var width = 40.0
    val gatesColumnView = mutableListOf(GatesColumnView(x))

    fun addGateView(gateView: GateView){
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

    fun moveNextColumns(index: Int, difference : Double){
        for (i in index..gatesColumnView.size - 1){
            gatesColumnView[i].moveAll(difference)
        }
        this.x += difference
    }

    fun addColumn() : Double{
        val column = GatesColumnView(this.width)
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
        return difference
    }

    fun removeColumn(index: Int) : Double{
        gatesColumnView.removeAt(index)
        val difference =  -gatesColumnView[index].width
        this.moveNextColumns(index + 1, difference)
        return difference
    }

    fun getGateView(i: Int, j: Int) : GateView{
        return gatesColumnView[j].gatesView[i]
    }

}