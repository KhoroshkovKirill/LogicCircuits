package views.circuit

import javafx.scene.control.Label
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import javafx.scene.shape.Line
import logic.Bus

class BusesView : javafx.scene.layout.HBox(10.0){

    class BusView(name: String, val bus : logic.Bus) : javafx.scene.layout.VBox(5.0) {
        init {
            this.children.addAll(javafx.scene.control.Label(name), javafx.scene.shape.Line(0.0, 0.0, 0.0, 300.0))
        }
    }

    fun add(name: String){
        this.children.add(views.circuit.BusesView.BusView(name, Bus.In()))
    }

}