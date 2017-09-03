package views.circuitView

import javafx.scene.shape.Line
import javafx.scene.shape.Shape

interface ElementView {
    fun getShapes() : List<Shape>

    fun changeLayoutX(difference : Double){
        for (element in getShapes()){
            if (element is Line){
                element.startX += difference
                element.endX += difference
            }
            else{
                element.layoutX += difference
            }
        }
    }

    fun changeLayoutY(difference : Double){
        for (element in getShapes()){
            if (element is Line){
                element.startY += difference
                element.endY += difference
            }
            else{
                element.layoutY += difference
            }
        }
    }
}