package views.circuitView

import javafx.scene.shape.Line
import javafx.scene.shape.Shape
import javafx.scene.text.Text
import logic.Bus

sealed class BusView {
    abstract val line : Line
    abstract fun move(difference: Double)

    class Local(x: Double, startY: Double, finishY: Double) : BusView() {
        override val line = Line(x,startY,x,finishY)

        override fun move(difference: Double) {
            this.line.startX += difference
            this.line.endX += difference
        }

        fun getShapes() : Line {
            return this.line
        }
    }

    class IO(name: String, x: Double, bus: Bus) : BusView() {
        val nameText = Text(name)
        override val line = Line(x, 30.0, x, 400.0)

        init {
            nameText.layoutX = x
            nameText.layoutY = 20.0
        }

        fun rename(newName: String): Double {
            var difference = this.getWidth()
            this.nameText.text = newName
            difference = this.getWidth() - difference
            return difference
        }

        fun getShapes() : List<Shape>{
            return listOf(this.line, this.nameText)
        }

        fun getWidth() : Double{
            return this.nameText.layoutBounds.width + 5.0
        }

        override fun move(difference: Double) {
            this.line.startX += difference
            this.line.endX += difference
            this.nameText.layoutX += difference
        }

    }
}