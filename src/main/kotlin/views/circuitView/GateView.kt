package views.circuitView

import javafx.scene.paint.Paint
import javafx.scene.shape.Rectangle
import javafx.scene.shape.Shape
import javafx.scene.text.Text
import logic.Gate

sealed class GateView : ElementView {
    val width : Double = 40.0
    val rectangle = Rectangle()
    abstract val outDotView: DotView
    init {
        rectangle.width = width
        rectangle.fill = Paint.valueOf("white")
        rectangle.stroke = Paint.valueOf("black")
    }

    abstract fun setLayoutY(y: Double)

    abstract fun setLayoutX(x: Double)

    fun getHeight() : Double{
        return rectangle.height + 15.0
    }

    class Not(val gate: Gate.Not) : GateView() {
        override val outDotView: DotView
        init {
            rectangle.height = 30.0
            outDotView = DotView(gate.output)
        }

        override fun setLayoutX(x: Double) {
            outDotView.layoutX = x + rectangle.width
            rectangle.layoutX = x
        }

        override fun setLayoutY(y: Double) {
            outDotView.layoutY = y + rectangle.height / 2
            rectangle.layoutY = y
        }

        override fun getShapes(): List<Shape> {
            return listOf(rectangle, outDotView)
        }
    }

    class Multivariate(val gate: Gate.Multivariate) : GateView(){
        val text : Text
        override val outDotView: DotView
        init {
            val name = when (gate) {
                is Gate.Multivariate.And -> "&"
                is Gate.Multivariate.Or -> "1"
                is Gate.Multivariate.Xor -> "=1"

            }
            text = Text(name)
            rectangle.height = (gate.inputList.size + 1) * 15.0
            outDotView = DotView(gate.output)
        }

        override fun setLayoutX(x: Double) {
            outDotView.layoutX = x + rectangle.width
            rectangle.layoutX = x
            text.layoutX = x + 10
        }

        override fun setLayoutY(y: Double) {
            outDotView.layoutY = y + rectangle.height / 2
            rectangle.layoutY = y
            text.layoutY = y + 20
        }

        override fun getShapes(): List<Shape> {
            return listOf(rectangle, text, outDotView)
        }

    }
}