package views.circuitView

import javafx.scene.input.MouseButton
import javafx.scene.layout.Pane
import logic.Bus
import logic.Circuit
import logic.Gate
import views.circuitView.ShapesLC.ShapeLC

class CircuitView : Pane(){
    val circuit = Circuit()
    val inBusesView = InBusesView(this)
    val gatesView = GatesCircuitView(inBusesView.width, this)
    var repositoryGate: GateView? = null
    var repositoryDot: DotView.In? = null
    val outBusView = BusView.IO.Out(
            "Y",
            inBusesView.width + gatesView.width + 20.0,
            circuit.outBus
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

                }
                if (repositoryGate != null) {
                    repositoryGate!!.execute(false)
                    repositoryGate = null
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
            gatesView.moveNextColumns(0, difference)
        }
    }

    fun renameInBus(index: Int, newName : String) {
        if (newName == "") {
            throw IllegalArgumentException("Шину следует назвать")
        } else {
            val difference = inBusesView.rename(index,newName)
            outBusView.changeLayoutX(difference)
            gatesView.moveNextColumns(0, difference)
        }
    }

    fun deleteInBus(index: Int){
        try {
            val difference = -inBusesView.busList[index].getWidth()
            circuit.delete(inBusesView.busList[index].bus)
            this.children.removeAll(inBusesView.remove(index))
            outBusView.changeLayoutX(difference)
            gatesView.moveNextColumns(0, difference)
        }
        catch (ex : IndexOutOfBoundsException){
            throw IndexOutOfBoundsException("Выход за предел списка")
        }
    }

    fun deleteGate(i: Int, j: Int){
        val gateView = gatesView.getGateView(i, j)
        circuit.delete(gateView.gate)
        this.children.removeAll(gateView.getShapes())
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

    fun shiftGate(i: Int, j: Int, newColumn: Int) : Double{
        val gateView = gatesView.getGateView(i, j)
        val difference = gatesView.removeGate(i, j)
        this.outBusView.changeLayoutX(difference)
        return gatesView.putAt(newColumn,gateView)
    }

    fun putInRepository(gateView: GateView, dotView: DotView.In){
        this.repositoryDot = dotView
        this.repositoryGate = gateView
    }

    fun changeInPutOfGate(previous: Previous, gateView: GateView, inPut: DotView.In){
        if (previous is GateView && previous.j >= gateView.j){
            val difference = this.shiftGate(gateView.i, gateView.j, previous.j + 1)
            outBusView.changeLayoutX(difference)
        }
        inPut.dot.changePrevious(previous.getOut())
    }

}