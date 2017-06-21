package views.truthTable

import javafx.scene.control.TableView
import logic.Circuit

class TruthTable(val circuit: Circuit) : TableView<String>() {
    init {
        for (element in circuit.inBuses.keys){
            this //element
        }
    }
}