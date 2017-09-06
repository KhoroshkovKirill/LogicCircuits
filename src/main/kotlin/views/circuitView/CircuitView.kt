package views.circuitView

import javafx.scene.input.MouseButton
import javafx.scene.layout.Pane
import javafx.scene.shape.Line
import logic.Bus
import logic.Circuit
import logic.Gate
import views.circuitView.ShapesLC.ShapeLC

class CircuitView : Pane(){
    val circuit = Circuit()
    val inBusesView = InBusesView(this)
    val gatesView = GatesView(inBusesView.width, this)
    var repositoryGate: GateView? = null
    var repositoryDot: DotView.In.ForGate? = null
    var repositoryOutBus : BusView.IO.Out? = null
    val outBusView = BusView.IO.Out(
            "Y",
            inBusesView.width + gatesView.width + 20.0,
            circuit.outBus,
            this
    )
    init {
        this.children.addAll(outBusView.getShapes())
        this.setOnMouseClicked { event: javafx.scene.input.MouseEvent ->
            run {
                if (event.button == MouseButton.PRIMARY) {
                    val node = event.pickResult.intersectedNode
                    if (repositoryGate != null && repositoryDot != null && node is ShapeLC){
                        val previous = node.getOwner()
                        if (previous is Previous) {
                            this.changeInPutOfGate(previous, repositoryGate!!, repositoryDot!!)
                        }
                    }
                    else if (repositoryOutBus != null && node is ShapeLC){
                        val previous = node.getOwner()
                        if (previous is GateView) {
                            this.changeInPutOfOutBus(previous, repositoryOutBus!!)
                        }
                    }
                    else {
                        println(event.pickResult.intersectedNode)///////////////////////////////////////////delete
                    }
                }
                if (repositoryGate != null) {
                    repositoryGate!!.execute(false)
                    repositoryGate = null
                }
                if (repositoryOutBus != null){
                    repositoryOutBus!!.execute(false)
                    repositoryOutBus != null
                }
                repositoryDot = null
            }
        }
    }

    fun addBusView(name : String) {
        if (name == "") {
            throw IllegalArgumentException("Шину следует назвать")
        } else {
            val bus = Bus.In()
            circuit.addBus(bus)
            val difference = inBusesView.add(name, bus)
            outBusView.changeLayoutX(difference)
            gatesView.moveColumnsFrom(0, difference)
        }
    }

    fun renameInBus(index: Int, newName : String) {
        if (newName == "") {
            throw IllegalArgumentException("Шину следует назвать")
        } else {
            val difference = inBusesView.rename(index,newName)
            outBusView.changeLayoutX(difference)
            gatesView.moveColumnsFrom(0, difference)
        }
    }

    fun deleteInBus(index: Int){
        try {
            val difference = -inBusesView.busList[index].getWidth()
            circuit.delete(inBusesView.busList[index].bus)
            this.children.removeAll(inBusesView.remove(index))
            outBusView.changeLayoutX(difference)
            gatesView.moveColumnsFrom(0, difference)
        }
        catch (ex : IndexOutOfBoundsException){
            throw IndexOutOfBoundsException("Выход за предел списка")
        }
    }

    fun deleteGate(i: Int, j: Int){
        val gateView = gatesView.getGateView(i, j)

        circuit.delete(gateView.gate)
        this.children.removeAll(gateView.getShapes())
        for (inDot in gateView.outDotView.next){
            //inDot.clear()
        }
        outBusView.changeLayoutX(gatesView.removeGate(i, j))
    }

    fun renameOutBus(newName: String){
        if (newName == "") {
            throw IllegalArgumentException("Шину следует назвать")
        } else {
            outBusView.rename(newName)
        }
    }

    fun addGateView(gate: Gate){
        circuit.addGate(gate)
        var row = 0
        try {
            row = gatesView.gatesColumnView[0].gatesView.size
        }
        catch (ex: IndexOutOfBoundsException){}
        val gateView =
                when (gate){
                    is Gate.Not -> GateView.Not(row, 0, gate, this)
                    is Gate.Multivariate -> GateView.Multivariate(row, 0, gate, this)
                }
        this.children.addAll(gateView.getShapes())
        val difference = gatesView.addGateView(gateView)
        outBusView.changeLayoutX(difference)
    }

    fun shiftGate(i: Int, j: Int, newColumn: Int){
        val gateView = gatesView.getGateView(i, j)
        var difference = gatesView.removeGate(i, j)
        difference += gatesView.putAt(newColumn,gateView)
        this.outBusView.changeLayoutX(difference)
        shiftNextGates(gateView)
    }

    fun shiftNextGates(gateView: GateView){
        for (element in gateView.outDotView.next){
            val nextGate = element.elementOwner as GateView
            shiftGate(nextGate.i, nextGate.j, nextGate.j + 1)
        }
    }

    fun putInRepository(gateView: GateView, dotView: DotView.In.ForGate){
        this.repositoryDot = dotView
        this.repositoryGate = gateView
    }

    fun putInRepository(outBusView: BusView.IO.Out){
        this.repositoryOutBus = outBusView
    }

    fun changeInPutOfGate(previous: Previous, gateView: GateView, inPut: DotView.In.ForGate){
        if (previous is GateView && !previous.getPrevious().contains(gateView)) {
            if (previous.j >= gateView.j) {
                this.shiftGate(gateView.i, gateView.j, previous.j + 1)
            }
            inPut.dot.changePrevious(previous.getOut())
            inPut.previous = previous
            previous.outDotView.next.add(inPut)
            this.drawLine(gateView.j, inPut, previous)
        }
        else if(previous is BusView.IO.In){
            inPut.dot.changePrevious(previous.getOut())
            inPut.previous = previous
            this.drawLine(gateView.j, inPut, previous)
        }
    }

    fun changeInPutOfOutBus(previous: GateView, busView: BusView.IO.Out) {
        busView.bus.input.changePrevious(previous.getOut())
        val dotView = busView.dotView
        previous.outDotView.next.add(dotView)
        busView.dotView.layoutY = previous.outDotView.layoutY
        val localBus = this.drawLineWithLocalBus(
                this.gatesView.gatesColumnView.lastIndex, dotView.layoutY, previous, dotView.line
        )
        dotView.drawLineToBus(localBus)
        localBus.redraw()
    }

    fun drawLine(column: Int, dotView: DotView.In, previous: Previous){
        if (column == 0 && previous is BusView.IO.In){
            dotView.drawLineToBus(previous)
            //////////////////////////////////////////////////////////////////////////////////////////previous.redraw()
        }
        else {
            val previousBus: BusView.Local
            if (gatesView.gatesColumnView[column - 1].localBuses.containsKey(previous)){
                previousBus = gatesView.gatesColumnView[column - 1].localBuses[previous]!!
            }
            else{
                previousBus = this.drawLineWithLocalBus(column - 1, dotView.layoutY, previous, dotView.line)
            }
            dotView.drawLineToBus(previousBus)
            previousBus.intersections.add(dotView.line)
            previousBus.redraw()
            previousBus.getShapes().forEach { if (!this.children.contains(it)) this.children.add(it) }
        }
    }

    fun drawLineWithLocalBus(column: Int, y: Double, previous: Previous, line: Line) : BusView.Local{
        val localBuses = gatesView.gatesColumnView[column].localBuses
        if (localBuses.containsKey(previous)){
            localBuses[previous]!!.intersections.add(line)
            localBuses[previous]!!.redraw()
            return localBuses[previous]!!
        }
        else {
            val difference = gatesView.gatesColumnView[column].addLocalBus(previous)
            gatesView.moveColumnsFrom(column + 1, difference)
            outBusView.changeLayoutX(difference)
            val localBus = gatesView.gatesColumnView[column].localBuses[previous]!!
            if (gatesView.gatesColumnView[column].gatesView.contains(previous) && previous is GateView){
                localBus.drawLineTo(previous.outDotView)
            }
            else {
                gatesView.drawLineThrow(column, y, localBus.lineToPrevious)
                if (column == 0 && previous is BusView.IO.In) {
                    //////////////////////////////////////////////////////////////////////////////////////previous.add
                    localBus.drawLineTo(previous)
                }
                else {
                    localBus.drawLineTo(this.drawLineWithLocalBus(column - 1, y, previous, localBus.lineToPrevious))
                }
            }
            localBus.intersections.add(line)
            gatesView.gatesColumnView[column].redrawLocalBuses()
            localBus.getShapes().forEach { if (!this.children.contains(it)) this.children.add(it) }
            return localBus
        }
    }

}