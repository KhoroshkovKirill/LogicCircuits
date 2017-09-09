package views.circuitView

import javafx.scene.shape.Line
import javafx.scene.shape.Shape

class GatesColumnView(var x: Double) : ElementView {
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

    fun moveNextGates(index: Int, difference : Double){
        for (i in index..(gatesView.lastIndex)) {
            this.gatesView[i].changeLayoutY(difference)
        }
    }

    fun remove(index: Int){
        val difference = -gatesView[index].height
        height += difference
        moveNextGates(index + 1, difference)
        for (i in index + 1..(gatesView.lastIndex)) {
            this.gatesView[i].i--
        }
        gatesView.removeAt(index)
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
        this.height += difference
        moveNextGates(gatesView.indexOf(map[newY]!!) + 1, difference)
        return difference
    }

    fun getLocalBuses() : List<BusView.Local>{
        return this.localBuses.values.toList()
    }

    override fun getShapes(): List<Shape> {
        val shapes = mutableListOf<Shape>()
        gatesView.forEach { shapes.addAll(it.getShapes()) }
        localBuses.forEach {
            shapes.add(it.value)
            shapes.addAll(it.value.dots)
        }
        return shapes
    }

    override fun changeLayoutX(difference: Double) {
        super.changeLayoutX(difference)
        x += difference
    }
}