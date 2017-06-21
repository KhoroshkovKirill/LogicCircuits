package views.circuitView

import logic.Bus

class BusesView : javafx.scene.layout.HBox(10.0){

    class BusView(name: String, val bus : logic.Bus) : javafx.scene.layout.VBox(5.0) {
        init {
            this.children.addAll(javafx.scene.control.Label(name), javafx.scene.shape.Line(0.0, 0.0, 0.0, 300.0))
        }
    }

    fun add(name: String){
        this.children.add(views.circuitView.BusesView.BusView(name, Bus.In()))
    }

}