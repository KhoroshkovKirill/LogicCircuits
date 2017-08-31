package views.circuitView

import javafx.scene.shape.Line
import javafx.scene.shape.Shape
import javafx.scene.text.Text
import logic.Bus
import Deletable
import logic.Dot

sealed class BusView : ElementView {
    abstract val line : Line

    class Local(x: Double, startY: Double, endY: Double) : BusView() {
        override val line =  if (startY < endY) Line(x,startY,x,endY) else Line(x,endY,x,startY)

        override fun getShapes(): List<Shape> {
            return listOf(this.line)
        }

    }

    sealed class IO(name: String, x: Double) : BusView() {
        val nameText = Text(name)
        override val line = Line(x, 30.0, x, 300.0)

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

        override fun getShapes() : List<Shape>{
            return listOf(this.line, this.nameText)
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

        class Out(name: String, x: Double, val bus: Bus.Out) : BusView.IO(name, x)
    }
}