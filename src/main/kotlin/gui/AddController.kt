package gui

import Main
import javafx.collections.FXCollections
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.scene.control.*
import javafx.stage.Modality
import javafx.stage.Stage
import javafx.scene.layout.Pane
import javafx.scene.text.Text
import logic.Gate

class AddController {

    fun display(main: Main){
        val stage = Stage()
        stage.title = "Add"

        /*Bus Tab*/
        val tabForBus = Tab("Bus")
        tabForBus.isClosable = false
        val paneForBus = Pane()
        val nameTextField = TextField()
        nameTextField.layoutX = 75.0
        nameTextField.layoutY = 10.0
        val nameText = Text("Name:")
        nameText.layoutX = 20.0
        nameText.layoutY = 27.0
        val messageForBus = Text()
        messageForBus.layoutX = 20.0
        messageForBus.layoutY = 60.0
        val okButtonForBus = Button("Ok")
        okButtonForBus.layoutX = 160.0
        okButtonForBus.layoutY = 160.0
        okButtonForBus.onAction = EventHandler {
            try {
                main.circuitView.addBusView(nameTextField.text)
                stage.close()
            }
            catch (ex : IllegalArgumentException){
                messageForBus.text = ex.message
            }
        }
        val cancelButtonForBus = Button("Cancel")
        cancelButtonForBus.layoutX = 210.0
        cancelButtonForBus.layoutY = 160.0
        cancelButtonForBus.onAction = EventHandler { stage.close() }
        paneForBus.children.addAll(nameText,messageForBus, okButtonForBus, cancelButtonForBus, nameTextField)
        tabForBus.content = paneForBus

        /*Gate Tab*/
        val tabForGate = Tab("Gate")
        tabForGate.isClosable = false
        val paneForGate = Pane()
        val operationText = Text("Operation:")
        operationText.layoutX = 20.0
        operationText.layoutY = 27.0
        val operationList = FXCollections.observableArrayList<String>(
                "Not",
                "And",
                "Or",
                "Xor"
        )
        val countTextField = TextField()
        countTextField.layoutX = 100.0
        countTextField.layoutY = 50.0
        val countText = Text(20.0,67.0,"Input count:")
        val operationCombo = ComboBox<String>(operationList)
        operationCombo.layoutX = 100.0
        operationCombo.layoutY = 10.0
        val messageForGate = Text()
        messageForGate.layoutX = 20.0
        messageForGate.layoutY = 110.0
        val okButtonForGate = Button("Ok")
        okButtonForGate.layoutX = 160.0
        okButtonForGate.layoutY = 160.0
        okButtonForGate.onAction = EventHandler {
            try {
                val gate =
                if (operationCombo.value == "Not"){
                    Gate.Not()
                } 
                else {
                    val count = countTextField.text.toInt()
                    when (operationCombo.value) {
                        "And" -> Gate.Multivariate.And(count)
                        "Or"  -> Gate.Multivariate.Or(count)
                        "Xor" -> Gate.Multivariate.Xor(count)
                        else -> { throw IllegalArgumentException("Выберите операцию") }
                    }
                }
                main.circuitView.addGateView(gate)
                stage.close()
            }
            catch (ex : NumberFormatException){
                messageForGate.text = "Укажите количество входов"
            }
            catch (ex : IllegalArgumentException){
                messageForGate.text = ex.message
            }
        }
        val cancelButtonForGate = Button("Cancel")
        cancelButtonForGate.layoutX = 210.0
        cancelButtonForGate.layoutY = 160.0
        cancelButtonForGate.onAction = EventHandler { stage.close() }
        paneForGate.children.addAll(operationText, countText, okButtonForGate, cancelButtonForGate,
                operationCombo, countTextField, messageForGate)
        tabForGate.content = paneForGate

        /*Scene*/
        val root = TabPane(tabForBus,tabForGate)
        val scene = Scene(root, 300.0, 250.0)
        stage.isResizable = false
        stage.initModality(Modality.WINDOW_MODAL)
        stage.initOwner(main.scene.window)
        stage.scene = scene
        stage.show()
    }

}