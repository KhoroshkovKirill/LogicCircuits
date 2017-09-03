package views.circuitView

import javafx.scene.paint.Paint
import javafx.scene.shape.Line
import logic.Dot
import views.circuitView.ShapesLC.CircleLC

sealed class DotView(elementOwner: GateView) : CircleLC(elementOwner) {

    init {
        this.fill = Paint.valueOf("white")
        this.stroke = Paint.valueOf("black")
    }

    abstract fun changeInversion()

    class In(val dot: Dot.In, elementOwner: GateView) : DotView(elementOwner){

        val line = Line()
        var previous: Previous? = null

        init {
            if (this.dot.isInverted){
                this.radius = 4.0
            }
            else{
                this.radius = 0.0
            }
        }

        override fun changeInversion(){
            if (this.dot.isInverted){
                this.radius = 0.0
            }
            else{
                this.radius = 4.0
            }
            this.dot.changeInversion()
        }

        fun drawLineToBus(busView: BusView) : Line {
            if (busView is BusView.IO.Out){
                throw IllegalArgumentException()
            }
            line.startY = this.layoutY
            line.startX = this.layoutX
            line.endX = busView.endX
            line.endY = this.layoutY
            return line
        }

    }

    class Out(val dot: Dot.Out, elementOwner: GateView) : DotView(elementOwner){

        val next = mutableListOf<DotView.In>()

        init {
            if (this.dot.isInverted){
                this.radius = 4.0
            }
            else{
                this.radius = 0.0
            }
        }

        override fun changeInversion(){
            if (this.dot.isInverted){
                this.radius = 0.0
            }
            else{
                this.radius = 4.0
            }
            this.dot.changeInversion()
        }
    }

}