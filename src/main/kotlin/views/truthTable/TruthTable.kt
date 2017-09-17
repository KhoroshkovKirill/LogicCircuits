package views.truthTable

import javafx.scene.layout.GridPane
import javafx.scene.text.Text
import views.circuitView.BusView
import views.circuitView.CircuitView
import java.lang.Math.pow

class TruthTable : GridPane() {
    init {
        this.isGridLinesVisible = true
        this.hgap = 4.0
        this.vgap = 2.0
        ///////////////////////////////////////////////////////////////////////////////////////////////////delete
        println(this.gridLinesVisibleProperty())
        println(this.isGridLinesVisible)
        /////////////////////////////////////////////////////////////////////////////////////////////////////////
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
        val count = circuitView.circuit.inBuses.size
        circuitView.outBusesView.busList.map {
            Text(it.nameText.text)
        }.forEachIndexed {
            i, nameOut -> this.add(nameOut, count + i, 0)
        }

        /*Values*/
        val input = circuitView.inBusesView.busList.map { (it as BusView.IO.In).bus }
        val output = circuitView.outBusesView.busList.map { (it as BusView.IO.Out).bus }
        val countOfRows = pow(2.0,count.toDouble()).toInt()
        for (rowIndex in 0..countOfRows - 1){
            var num = countOfRows
            for ((columnIndex,element) in input.withIndex()){
                num /= 2
                element.value = (rowIndex and num != 0)
                val x = Text(if (element.value) "1" else "0")
                this.add(x, columnIndex, rowIndex + 1)
            }
            output.forEachIndexed { index, out ->
                val y = Text(if (out.calculateValue()) "1" else "0")
                this.add(y, count + index, rowIndex + 1)
            }
        }
    }
}