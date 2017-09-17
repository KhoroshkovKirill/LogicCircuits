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

        /*In Bus Tab*/
        val tabForInBus = Tab("Input")
        tabForInBus.isClosable = false
        val paneForInBus = Pane()
        val nameTextFieldForInBus = TextField()
        nameTextFieldForInBus.layoutX = 75.0
        nameTextFieldForInBus.layoutY = 10.0
        val nameTextForInBus = Text("Name:")
        nameTextForInBus.layoutX = 20.0
        nameTextForInBus.layoutY = 27.0
        val messageForInBus = Text()
        messageForInBus.layoutX = 20.0
        messageForInBus.layoutY = 60.0
        val okButtonForInBus = Button("Ok")
        okButtonForInBus.layoutX = 160.0
        okButtonForInBus.layoutY = 160.0
        okButtonForInBus.onAction = EventHandler {
            try {
                main.circuitView.addInBus(nameTextFieldForInBus.text)
                stage.close()
            }
            catch (ex : IllegalArgumentException){
                messageForInBus.text = ex.message
            }
        }
        val cancelButtonForInBus = Button("Cancel")
        cancelButtonForInBus.layoutX = 210.0
        cancelButtonForInBus.layoutY = 160.0
        cancelButtonForInBus.onAction = EventHandler { stage.close() }
        paneForInBus.children.addAll(
                nameTextForInBus,messageForInBus, okButtonForInBus, cancelButtonForInBus, nameTextFieldForInBus
        )
        tabForInBus.content = paneForInBus

        /*Out Bus Tab*/
        val tabForOutBus = Tab("Output")
        tabForOutBus.isClosable = false
        val paneForOutBus = Pane()
        val nameTextFieldForOutBus = TextField()
        nameTextFieldForOutBus.layoutX = 75.0
        nameTextFieldForOutBus.layoutY = 10.0
        val nameTextForOutBus = Text("Name:")
        nameTextForOutBus.layoutX = 20.0
        nameTextForOutBus.layoutY = 27.0
        val messageForOutBus = Text()
        messageForOutBus.layoutX = 20.0
        messageForOutBus.layoutY = 60.0
        val okButtonForOutBus = Button("Ok")
        okButtonForOutBus.layoutX = 160.0
        okButtonForOutBus.layoutY = 160.0
        okButtonForOutBus.onAction = EventHandler {
            try {
                main.circuitView.addOutBus(nameTextFieldForOutBus.text)
                stage.close()
            }
            catch (ex : IllegalArgumentException){
                messageForOutBus.text = ex.message
            }
        }
        val cancelButtonForOutBus = Button("Cancel")
        cancelButtonForOutBus.layoutX = 210.0
        cancelButtonForOutBus.layoutY = 160.0
        cancelButtonForOutBus.onAction = EventHandler { stage.close() }
        paneForOutBus.children.addAll(
                nameTextForOutBus,messageForOutBus, okButtonForOutBus, cancelButtonForOutBus, nameTextFieldForOutBus
        )
        tabForOutBus.content = paneForOutBus

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
        val root = TabPane(tabForInBus, tabForOutBus,tabForGate)
        val scene = Scene(root, 300.0, 250.0)
        stage.isResizable = false
        stage.initModality(Modality.WINDOW_MODAL)
        stage.initOwner(main.scene.window)
        stage.scene = scene
        stage.show()
    }

}