package views.circuitView

import javafx.scene.paint.Paint
import javafx.scene.shape.Line
import javafx.scene.shape.Shape
import logic.Dot
import views.circuitView.ShapesLC.CircleLC

sealed class DotView(elementOwner: ElementView) : CircleLC(elementOwner), ElementView {

    val line = Line()

    override fun getShapes(): List<Shape> {
        return listOf(this, this.line)
    }

    sealed class In(elementOwner: ElementView) : DotView(elementOwner){

        var previous: Previous? = null

        fun drawLineToBus(busView: BusView): Line {
            if (busView is BusView.IO.Out) {
                throw IllegalArgumentException()
            }
            line.startY = this.layoutY
            line.startX = this.layoutX
            line.endX = busView.endX
            line.endY = this.layoutY
            return line
        }

        class ForGate(val dot: Dot.In, elementOwner: ElementView) : In(elementOwner), Invertible {

            init {
                if (this.dot.isInverted) {
                    this.radius = 4.0
                } else {
                    this.radius = 0.0
                }
                this.fill = Paint.valueOf("white")
                this.stroke = Paint.valueOf("black")
            }

            override fun changeInversion() {
                if (this.dot.isInverted) {
                    this.radius = 0.0
                } else {
                    this.radius = 4.0
                }
                this.dot.changeInversion()
            }

        }

        class ForBus(elementOwner: ElementView) : DotView.In(elementOwner){

            init {
                this.radius = 3.0
            }

        }

    }

    sealed class Out(val dot: Dot.Out, elementOwner: ElementView) : DotView(elementOwner){

        val next = mutableListOf<DotView.In>()

        class ForGate(dot: Dot.Out, elementOwner: ElementView) : Out(dot, elementOwner), Invertible {
            init {
                if (this.dot.isInverted) {
                    this.radius = 4.0
                } else {
                    this.radius = 0.0
                }
                this.fill = Paint.valueOf("white")
                this.stroke = Paint.valueOf("black")
            }

            override fun changeInversion() {
                if (this.dot.isInverted) {
                    this.radius = 0.0
                } else {
                    this.radius = 4.0
                }
                this.dot.changeInversion()
            }
        }

        class ForBus(dot: Dot.Out, elementOwner: ElementView) : Out(dot, elementOwner){

            init {
                this.radius = 3.0
            }
        }
    }

}