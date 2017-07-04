package views.circuitView

import javafx.scene.paint.Paint
import javafx.scene.shape.Circle
import javafx.scene.shape.Shape
import logic.Dot

class DotView(x : Double, y: Double, val dot: Dot) : Circle() {

    init {
        this.fill = Paint.valueOf("white")
        this.stroke = Paint.valueOf("black")

        if (this.dot.isInverted){
            this.radius = 4.0
        }
        else{
            this.radius = 0.0
        }

        this.layoutX = x
        this.layoutY = y
    }

    fun changeInversion(){
        if (this.dot.isInverted){
            this.radius = 0.0
        }
        else{
            this.radius = 4.0
        }
        this.dot.changeInversion()
    }

}