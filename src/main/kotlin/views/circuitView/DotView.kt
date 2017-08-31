package views.circuitView

import javafx.scene.paint.Paint
import logic.Dot
import views.circuitView.ShapesLC.CircleLC

sealed class DotView(elementOwner: ElementView) : CircleLC(elementOwner) {

    init {
        this.fill = Paint.valueOf("white")
        this.stroke = Paint.valueOf("black")
    }

    abstract fun changeInversion()

    class In(val dot: Dot.In, elementOwner: ElementView) : DotView(elementOwner){

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

    class Out(val dot: Dot.Out, elementOwner: ElementView) : DotView(elementOwner){

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