package views.circuitView

import javafx.scene.shape.Shape

interface ElementView {
    fun getShapes() : List<Shape>
}