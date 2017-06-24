package views.truthTable

import javafx.scene.control.TableColumn
import javafx.scene.control.TableView
import logic.Circuit

class TruthTable() : TableView<String>() {
    constructor(circuit: Circuit) : this(){
        for (element in circuit.inBuses){
            //this.columns.add(TableColumn<String,String>(element))//element
        }
    }
}