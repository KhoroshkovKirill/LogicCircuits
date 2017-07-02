package views.circuitView

import javafx.scene.shape.Rectangle
import javafx.scene.shape.Shape
import javafx.scene.text.Text
import logic.Gate

sealed class GateView(x: Double, y: Double) : ElementView {
    val width : Double = 40.0
    val rectangle = Rectangle(x, y)
    init {
        rectangle.width = width
    }

    class Not(x: Double, y: Double, val gate: Gate.Not) : GateView(x, y) {

        init {
            rectangle.height = 30.0
        }

        override fun getShapes(): List<Shape> {
            return listOf(rectangle)
        }
    }

    class Multivariate(x: Double, y: Double, val gate: Gate.Multivariate) : GateView(x, y){
        val text : Text
        init {
            val name = when (gate) {
                is Gate.Multivariate.And -> "&"
                is Gate.Multivariate.Or -> "1"
                is Gate.Multivariate.Xor -> "=1"
            }
            text = Text(x + 10, y + 10, name)
        }

        init {
            rectangle.height = (gate.inputList.size + 1) * 15.0
        }

        override fun getShapes(): List<Shape> {
            return listOf(rectangle, text)
        }
    }
}