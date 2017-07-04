package views.circuitView

import javafx.scene.shape.Shape

interface ElementView {
    fun getShapes() : List<Shape>
    fun move(difference : Double){
        for (element in getShapes()){
            element.layoutX += difference
        }
    }
}