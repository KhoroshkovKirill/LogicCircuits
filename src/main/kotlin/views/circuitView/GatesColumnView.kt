package views.circuitView

import javafx.scene.shape.Line

class GatesColumnView(var x: Double) {
    var height = 30.0
    var width = 60.0
    val gatesView = mutableListOf<GateView>()
    val localBuses = mutableMapOf<Previous, BusView.Local>()

    fun addGateView(gateView: GateView){
        gateView.i = gatesView.size
        gatesView.add(gateView)
        gateView.setLayoutY(this.height + 15.0)
        gateView.setLayoutX(this.x)
        this.height += gateView.height
    }

    fun addLocalBus(previous: Previous) : Double{
        if (localBuses.containsKey(previous)){
            return 0.0
        }
        else {
            localBuses.put(previous, BusView.Local(x + width))
            width += 15
            return 15.0
        }
    }

    fun moveAll(difference : Double){
        for (element in gatesView){
            element.changeLayoutX(difference)
        }
        for (element in localBuses.values) {
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
        val difference = -gatesView[i].height
        height += difference
        moveNextGates(i + 1, difference)
        gatesView.removeAt(i)
    }

    fun putLine(y: Double, line: Line) : Double{
        val map = mutableMapOf<Double, GateView>()
        for (element in gatesView){
            map.put(element.rectangle.layoutY + element.height, element)
        }
        var newY = map.keys.min()!!
        var minDistance = if (y < newY) newY - y else y - newY
        for (element in map.keys){
            val distance = if (newY < element) element - newY else newY - element
            if (distance < minDistance){
                minDistance = distance
                newY = element
            }
        }
        val difference =  map[newY]!!.putLine(line)
        moveNextGates(gatesView.indexOf(map[newY]!!) + 1, difference)
        return difference
    }
}