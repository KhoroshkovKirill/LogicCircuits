package views.circuitView

import javafx.geometry.Side
import javafx.scene.control.ContextMenu
import javafx.scene.control.MenuItem
import javafx.scene.input.MouseButton
import javafx.scene.paint.Paint
import javafx.scene.shape.Line
import javafx.scene.shape.Shape
import javafx.scene.text.Text
import logic.Dot
import logic.Gate
import views.circuitView.ShapesLC.RectangleLC
import views.circuitView.ShapesLC.TextLC

sealed class GateView(var i: Int, var j: Int, open val gate: Gate, val circuitView: CircuitView) : ElementView, Previous {
    val width : Double = 40.0
    abstract var height : Double
    val rectangle = RectangleLC(this)
    val linesBetweenBuses = mutableListOf<Line>()
    abstract val outDotView: DotView.Out
    abstract val contextMenu: ContextMenu
    init {
        rectangle.width = width
        rectangle.fill = Paint.valueOf("white")
        rectangle.stroke = Paint.valueOf("black")
    }

    abstract fun setLayoutY(y: Double)

    abstract fun setLayoutX(x: Double)

    abstract fun getPrevious() : List<Previous>

    fun execute(isExecuted: Boolean){
        if (isExecuted){
            this.getShapes().filter { it !is Text }.forEach { it.fill = Paint.valueOf("aqua") }
        }
        else{
            this.getShapes().filter { it !is Text }.forEach { it.fill = Paint.valueOf("white") }
        }
    }

    fun putLine(line: Line) : Double{
        linesBetweenBuses.add(line)
        line.startY = this.rectangle.layoutY + height
        line.endY = this.rectangle.layoutY + height
        height += 15.0
        return 15.0
    }

    override fun getOut() : Dot.Out {
        return this.gate.output
    }

    class Not(i: Int, j: Int, override val gate: Gate.Not, circuitView: CircuitView) : GateView(i, j, gate, circuitView) {
        override val outDotView = DotView.Out.ForGate(gate.output, this)
        val inDotView = DotView.In.ForGate(gate.input, this)
        val inDotItem = MenuItem("InPut")
        override val contextMenu = ContextMenu(inDotItem)
        override var height: Double = 0.0
        init {
            rectangle.height = 30.0
            inDotItem.setOnAction {
                this.execute(true)
                circuitView.putInRepository(this, this.inDotView)
            }
            for (element in this.getShapes()) {
                element.setOnMouseClicked { event: javafx.scene.input.MouseEvent ->
                    run {
                        if (event.button == MouseButton.SECONDARY) {
                            contextMenu.show(outDotView, Side.RIGHT, 0.0, 0.0)
                        }
                    }
                }
            }
            height = rectangle.height + 15.0
        }

        override fun setLayoutX(x: Double) {
            outDotView.layoutX = x + rectangle.width
            rectangle.layoutX = x
            inDotView.layoutX = x
        }

        override fun setLayoutY(y: Double) {
            outDotView.layoutY = y + rectangle.height / 2
            rectangle.layoutY = y
            inDotView.layoutY = y + rectangle.height / 2
        }

        override fun getShapes(): List<Shape> {
            val shapes = mutableListOf<Shape>(rectangle, inDotView, inDotView.line)
            shapes.addAll(linesBetweenBuses)
            shapes.addAll(outDotView.getShapes())
            return shapes
        }

        override fun getPrevious(): List<Previous> {
            val previous = mutableListOf<Previous>()
            if (inDotView.previous is GateView) {
                previous.addAll((inDotView.previous as GateView).getPrevious())
            }
            previous.add(this)
            return previous
        }

    }

    class Multivariate(i: Int, j: Int, override val gate: Gate.Multivariate, circuitView: CircuitView) : GateView(i, j, gate, circuitView){
        val text : TextLC
        val inDotListView = mutableListOf<DotView.In.ForGate>()
        override var height: Double = 0.0
        override val outDotView: DotView.Out.ForGate
        override val contextMenu = ContextMenu()
        init {
            val name = when (gate) {
                is Gate.Multivariate.And -> "&"
                is Gate.Multivariate.Or -> "1"
                is Gate.Multivariate.Xor -> "=1"
            }
            text = TextLC(this)
            text.text = name
            rectangle.height = (gate.inputList.size + 1) * 15.0
            for (element in gate.inputList){
                inDotListView.add(DotView.In.ForGate(element, this))
            }
            for ((index, element) in inDotListView.withIndex()){
                val inDotItem = MenuItem("InPut " + index.toString())
                inDotItem.setOnAction {
                    this.execute(true)
                    circuitView.putInRepository(this, element)
                }
                contextMenu.items.add(inDotItem)
            }
            outDotView = DotView.Out.ForGate(gate.output, this)
            for (element in this.getShapes()) {
                element.setOnMouseClicked { event: javafx.scene.input.MouseEvent ->
                    run {
                        if (event.button == MouseButton.SECONDARY) {
                            contextMenu.show(outDotView, Side.RIGHT, 0.0, 0.0)
                        }
                    }
                }
            }
            this.height = rectangle.height + 15.0
        }

        override fun setLayoutX(x: Double) {
            outDotView.layoutX = x + rectangle.width
            rectangle.layoutX = x
            text.layoutX = x + 10
            for (element in inDotListView){
                element.layoutX = x
            }
        }

        override fun setLayoutY(y: Double) {
            outDotView.layoutY = y + rectangle.height / 2
            rectangle.layoutY = y
            text.layoutY = y + 20
            val distance = this.rectangle.height / (gate.inputList.size + 1)
            for ((index, element) in inDotListView.withIndex()){
                element.layoutY = y + distance * (index + 1)
            }
        }

        override fun getShapes(): List<Shape> {
            val shapes = mutableListOf<Shape>(rectangle, text)
            shapes.addAll(linesBetweenBuses)
            for (element in inDotListView){
                shapes.add(element)
                shapes.add(element.line)
            }
            shapes.addAll(outDotView.getShapes())
            return shapes
        }

        override fun getPrevious(): List<Previous> {
            val previous = mutableListOf<Previous>()
            for (dot in inDotListView){
                if (dot.previous is GateView) {
                    previous.addAll((dot.previous as GateView).getPrevious())
                }
            }
            previous.add(this)
            return previous
        }

    }
}