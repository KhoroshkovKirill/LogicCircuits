package views.circuitView

import javafx.scene.paint.Paint
import javafx.scene.shape.Rectangle
import javafx.scene.shape.Shape
import javafx.scene.text.Text
import logic.Gate

sealed class GateView(x: Double, y: Double) : ElementView {
    val width : Double = 40.0
    val rectangle = Rectangle(x, y)
    abstract val outDotView: DotView
    init {
        rectangle.width = width
        rectangle.layoutX = x
        rectangle.layoutY = y
        rectangle.fill = Paint.valueOf("white")
        rectangle.stroke = Paint.valueOf("black")
    }

    fun getHeight() : Double{
        return rectangle.height + 15.0
    }

    class Not(x: Double, y: Double, val gate: Gate.Not) : GateView(x, y) {
        override val outDotView: DotView
        init {
            rectangle.height = 30.0
            outDotView = DotView(
                    x + rectangle.width,
                    y + rectangle.height / 2,
                    gate.output
            )
        }

        override fun getShapes(): List<Shape> {
            return listOf(rectangle, outDotView)
        }
    }

    class Multivariate(x: Double, y: Double, val gate: Gate.Multivariate) : GateView(x, y){
        val text : Text
        override val outDotView: DotView
        init {
            val name = when (gate) {
                is Gate.Multivariate.And -> "&"
                is Gate.Multivariate.Or -> "1"
                is Gate.Multivariate.Xor -> "=1"

            }
            text = Text(x + 10, y + 20, name)
            rectangle.height = (gate.inputList.size + 1) * 15.0
            outDotView = DotView(
                    x + rectangle.width,
                    y + rectangle.height / 2,
                    gate.output
            )
        }

        override fun getShapes(): List<Shape> {
            return listOf(rectangle, text, outDotView)
        }
    }
}