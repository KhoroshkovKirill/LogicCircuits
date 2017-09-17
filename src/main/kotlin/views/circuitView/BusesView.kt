package views.circuitView

import javafx.scene.shape.Shape
import logic.Bus

sealed class BusesView(val circuitView: CircuitView){
    val busList = mutableListOf<BusView.IO>()
    abstract var width: Double

    fun rename(index: Int, newName: String): Double {
        try {
            val difference = busList[index].rename(newName)
            width += difference
            moveBusesFrom((index + 1), difference)
            return difference
        } catch (ex: IndexOutOfBoundsException) {
            throw IllegalArgumentException("Выход за предел списка")
        }
    }

    fun remove(index: Int): List<Shape> {
        try {
            val difference = -busList[index].getWidth()
            width += difference
            moveBusesFrom(index, difference)
            val shapes = busList[index].getShapes()
            busList[index].prepareToDelete()
            busList.removeAt(index)
            return shapes
        } catch (ex: IndexOutOfBoundsException) {
            throw IllegalArgumentException("Выход за предел списка")
        }

    }

    open fun moveBusesFrom(index: Int, difference: Double) {
        for (i in index..(busList.lastIndex)) {
            this.busList[i].changeLayoutX(difference)
        }
    }

    class In(circuitView: CircuitView) : BusesView(circuitView) {

        override var width = 10.0

        fun add(name: String, bus: Bus.In): Double {
            val busView = BusView.IO.In(name, width, bus)
            busList.add(busView)
            circuitView.children.addAll(busView.getShapes())
            val difference = busView.getWidth()
            width += difference
            return difference
        }

        fun redrawBusesTo(index: Int){
            for (i in 0..index){
                busList[i].redraw()
            }
        }

        override fun moveBusesFrom(index: Int, difference: Double) {
            super.moveBusesFrom(index, difference)
            redrawBusesTo(index - 1)
        }

    }

    class Out(var x: Double, circuitView: CircuitView) : BusesView(circuitView), ElementView{

        override var width = 0.0

        fun add(name: String, bus: Bus.Out): Double {
            val busView = BusView.IO.Out(name, x + width, bus, circuitView)
            busList.add(busView)
            circuitView.children.addAll(busView.getShapes())
            val difference = busView.getWidth()
            width += difference
            return difference
        }

        fun redrawBusesFrom(index: Int){
            for (i in index..busList.lastIndex){
                busList[i].redraw()
            }
        }

        override fun moveBusesFrom(index: Int, difference: Double) {
            super.moveBusesFrom(index, difference)
            redrawBusesFrom(index)
        }

        override fun getShapes(): List<Shape> {
            val shapes = mutableListOf<Shape>()
            for (element in busList){
                shapes.addAll(element.getShapes())
            }
            return shapes
        }

        override fun changeLayoutX(difference: Double) {
            super.changeLayoutX(difference)
            x += difference
        }
    }
}