package views.circuitView

import javafx.scene.shape.Line
import javafx.scene.shape.Shape
import logic.Bus
import Deletable
import javafx.geometry.Side
import javafx.scene.control.ContextMenu
import javafx.scene.control.MenuItem
import javafx.scene.input.MouseButton
import javafx.scene.paint.Paint
import javafx.scene.shape.Circle
import logic.Dot
import views.circuitView.ShapesLC.TextLC

sealed class BusView : ElementView, Line() {

    class Local(x: Double) : BusView() {

        var lineToPrevious = Line()
        val intersections = mutableSetOf(lineToPrevious)
        val dots = mutableListOf<Circle>()

        init {
            this.startX = x
            this.endX = x
        }

        override fun getShapes(): List<Shape> {
            val shapes = mutableListOf<Shape>()
            shapes.addAll(this.dots)
            shapes.add(this)
            shapes.addAll(intersections)
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
                    else if (element !== lineToPrevious && element.startY == lineToPrevious.startY && intersections.size > 2 ){
                        dots.add(Circle(this.startX, element.startY, 3.0))
                    }
                    if (element !== lineToPrevious) {
                        element.endX = this.endX
                    }
                }
                return true
            }
        }

        fun drawLineTo(previousBus: BusView) : Line{
            lineToPrevious.startX = this.startX
            lineToPrevious.endX = previousBus.startX
            return lineToPrevious
        }

        fun drawLineTo(dotView: DotView.Out) : Line{
            this.intersections.remove(lineToPrevious)
            this.lineToPrevious = dotView.line
            this.intersections.add(lineToPrevious)
            lineToPrevious.startX = this.startX
            lineToPrevious.startY = dotView.layoutY
            lineToPrevious.endX = dotView.layoutX
            lineToPrevious.endY = dotView.layoutY
            return lineToPrevious
        }
    }

    sealed class IO(name: String, x: Double) : BusView() {
        abstract val nameText: TextLC

        init {
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

        fun getWidth() : Double{
            return this.nameText.layoutBounds.width + 5.0
        }

        class In(name: String, x: Double, val bus: Bus.In) : BusView.IO(name, x) , Deletable, Previous{

            val intersections = mutableSetOf<Line>()
            override val nameText = TextLC(this)
            init {
                nameText.text = name
                nameText.layoutX = x
                nameText.layoutY = 20.0
            }

            fun redraw(){

            }

            override fun prepareToDelete(){
                this.bus.prepareToDelete()
            }

            override fun getOut() : Dot.Out{
                return this.bus.outPut
            }

            override fun getShapes() : List<Shape>{
                return listOf(this, this.nameText)
            }

        }

        class Out(name: String, x: Double, val bus: Bus.Out, circuitView: CircuitView) : BusView.IO(name, x){
            val dotView = DotView.In.ForBus(this)
            val changePreviousItem = MenuItem("Change previous")
            val clearPreviousItem = MenuItem("Clear previous")
            val contextMenu = ContextMenu(changePreviousItem, clearPreviousItem)
            override val nameText = TextLC(this)
            init {
                dotView.layoutX = this.startX
                dotView.layoutY = this.startY
                nameText.text = name
                nameText.layoutX = x
                nameText.layoutY = 20.0
                changePreviousItem.setOnAction {
                    this.execute(true)
                    circuitView.putInRepository(this)
                }
                clearPreviousItem.setOnAction {
                    this.execute(false)
                }
                for (element in this.getShapes()) {
                    element.setOnMouseClicked { event: javafx.scene.input.MouseEvent ->
                        run {
                            if (event.button == MouseButton.SECONDARY) {
                                contextMenu.show(this, Side.RIGHT, 0.0, 0.0)
                            }
                        }
                    }
                }
            }

            fun execute(isExecuted: Boolean){
                if (isExecuted){
                    this.getShapes().forEach { it.fill = Paint.valueOf("aqua") }
                }
                else{
                    this.getShapes().forEach { it.fill = Paint.valueOf("black") }
                }
            }

            override fun getShapes() : List<Shape>{
                val shapes = mutableListOf<Shape>(this, nameText)
                shapes.addAll(dotView.getShapes())
                return shapes
            }
        }
    }
}