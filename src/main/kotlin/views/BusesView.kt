package views

import javafx.scene.Group
import javafx.scene.Node
import javafx.scene.layout.Pane
import javafx.scene.shape.Line
import logic.Bus

/**
 * Created by khoroshkovkirill on 04.06.17.
 */
class BusesView(vararg children: Node?) : Pane(*children) {

    class BusView(startX: Double, startY: Double, endX: Double, endY: Double) : Line(startX, startY, endX, endY) {
        val bus = Bus.In()
        val name = String()
    }

    fun add(name: String){
        val x = (this.children.count() + 1) * 10.0
        this.children.add(BusView(x,12.0,x,120.0))
    }
}