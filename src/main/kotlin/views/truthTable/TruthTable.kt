package views.truthTable

import javafx.scene.layout.GridPane
import javafx.scene.text.Text
import views.circuitView.CircuitView
import java.lang.Math.pow

class TruthTable : GridPane() {
    init {
        this.isGridLinesVisible = true
        this.hgap = 4.0
        this.vgap = 2.0
        //
        println(this.gridLinesVisibleProperty())
        println(this.isGridLinesVisible)
        //
    }

    fun clear(){
        this.children.clear()
    }

    fun refresh(circuitView: CircuitView) {
        this.children.clear()

        /*Names*/
        circuitView.inBusesView.busList.map {
            Text(it.nameText.text)
        }.forEachIndexed {
            i, nameIn -> this.add(nameIn, i, 0)
        }
        val nameOut = Text(circuitView.outBusView.nameText.text)
        val count = circuitView.circuit.inBuses.size
        this.add(nameOut, count, 0)

        /*Values*/
        val input = circuitView.circuit.inBuses
        val output = circuitView.circuit.outBus
        val countOfRows = pow(2.0,count.toDouble()).toInt()
        for (rowIndex in 0..countOfRows - 1){
            var num = countOfRows
            for ((columnIndex,element) in input.withIndex()){
                num /= 2
                element.value = (rowIndex and num != 0)
                val x = Text(if (element.value) "1" else "0")
                this.add(x, columnIndex, rowIndex + 1)
            }
            ///////////////////////////////////////////////////////////////////////////////////////////////////////////
            val y = Text("yy")//if (output.calculateValue()) "1" else "0")
            this.add(y, count, rowIndex + 1)
        }
    }
}