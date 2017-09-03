package views.circuitView

import javafx.scene.shape.Line
import javafx.scene.shape.Shape
import javafx.scene.text.Text
import logic.Bus
import Deletable
import javafx.scene.shape.Circle
import logic.Dot

sealed class BusView : ElementView, Line() {

    class Local(x: Double) : BusView() {

        val line = Line()
        val intersections = mutableSetOf(line)
        val dots = mutableListOf<Circle>()

        init {
            this.startX = x
            this.endX = x
        }

        override fun getShapes(): List<Shape> {
            val shapes = mutableListOf<Shape>()
            shapes.addAll(this.dots)
            shapes.add(this)
            return shapes
        }

        fun redraw(): Boolean{
            if (intersections.isEmpty()){
                return false
            }
            else {
                this.startY = intersections.map { it.startY }.min()!!
                this.endY = intersections.map { it.startY }.max()!!
                for (element in intersections) {
                    if (element.startY != this.startY && element.startY != this.endY) {
                        dots.add(Circle(this.startX, element.startY, 3.0))
                    }
                    else if (element !== line && element.startY == line.startY && intersections.size > 2 ){
                        dots.add(Circle(this.startX, element.startY, 3.0))
                    }
                }
                return true
            }
        }

        fun drawLineTo(y: Double, previousBus: BusView) : Line{
            line.startX = this.startX
            line.startY = y
            line.endX = previousBus.layoutX
            line.endY = y
            return line
        }

        fun drawLineTo(dotView: DotView.Out) : Line{
            line.startX = this.startX
            line.startY = dotView.layoutY
            line.endX = dotView.layoutX
            line.endY = dotView.layoutY
            return line
        }

    }

    sealed class IO(name: String, x: Double) : BusView() {
        val nameText = Text(name)

        init {
            nameText.layoutX = x
            nameText.layoutY = 20.0
            this.startX = x
            this.endX = x
            this.startY = 30.0
            this.endY = 300.0
        }

        fun rename(newName: String): Double {
            var difference = this.getWidth()
            this.nameText.text = newName
            difference = this.getWidth() - difference
            return difference
        }

        override fun getShapes() : List<Shape>{
            return listOf(this, this.nameText)
        }

        fun getWidth() : Double{
            return this.nameText.layoutBounds.width + 5.0
        }

        class In(name: String, x: Double, val bus: Bus.In) : BusView.IO(name, x) , Deletable, Previous{

            override fun prepareToDelete(){
                this.bus.prepareToDelete()
            }

            override fun getOut() : Dot.Out{
                return this.bus.outPut
            }

        }

        class Out(name: String, x: Double, val bus: Bus.Out) : BusView.IO(name, x){
            val line = Line()
            val dot = Circle()
            init {
                dot.radius = 3.0
            }

            fun drawLineToBus(busView: BusView.Local) : List<Shape> {
                line.startY = busView.layoutY
                line.startX = this.layoutX
                line.endX = busView.endX
                line.endY = busView.layoutY
                dot.centerX = this.layoutX
                dot.centerY = busView.layoutY
                return listOf(line, dot)
            }
        }
    }
}