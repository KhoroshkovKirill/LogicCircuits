package views.truthTable

import javafx.scene.layout.GridPane
import javafx.scene.text.Text
import views.circuitView.CircuitView

class TruthTable : GridPane() {

    fun refresh(circuitView: CircuitView) {
        this.children.clear()
        for ((i, element) in circuitView.inBusesView.busList.withIndex()) {
            val text = Text(element.nameText.text)
            this.add(text, i, 0)
        }
    }
}