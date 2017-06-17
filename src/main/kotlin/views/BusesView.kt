package views

import javafx.scene.Group
import javafx.scene.Node
import javafx.scene.shape.Line
import logic.Bus

/**
 * Created by khoroshkovkirill on 04.06.17.
 */
class BusesView(vararg children: Node?) : Group(*children) {

    class BusView(startX: Double, startY: Double, endX: Double, endY: Double) : Line(startX, startY, endX, endY) {
        val bus = Bus.In()
        val name = String()
    }

    fun add(name: String){
        this.children.add(BusView(12.0,12.0,12.0,120.0))
    }
}