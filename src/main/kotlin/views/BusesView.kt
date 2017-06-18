package views

import javafx.scene.control.Label
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import javafx.scene.shape.Line
import logic.Bus

/**
 * Created by khoroshkovkirill on 04.06.17.
 */
class BusesView : HBox(10.0){

    class BusView(name: String, val bus : Bus) : VBox(5.0) {
        init {
            this.children.addAll(Label(name),Line(10.0, 0.0, 10.0, 140.0))
        }
    }

    fun add(name: String){
        this.children.add(BusView(name,Bus.In()))
    }

    fun remove(bus: BusView){
        this.children.remove(bus)
    }
}