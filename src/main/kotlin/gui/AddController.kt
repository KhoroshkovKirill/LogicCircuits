package gui

import Main
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.TabPane
import javafx.stage.Modality
import javafx.stage.Stage
import javafx.scene.control.Tab
import javafx.scene.control.TextField
import javafx.scene.layout.Pane
import javafx.scene.text.Text

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
        val message = Text()
        message.layoutX = 20.0
        message.layoutY = 60.0
        val okButtonForBus = Button("Ok")
        okButtonForBus.layoutX = 160.0
        okButtonForBus.layoutY = 160.0
        okButtonForBus.onAction = EventHandler {
            try {
                main.circuitView.addBusView(nameTextField.text)
                stage.close()
            }
            catch (ex : IllegalArgumentException){
                message.text = ex.message
            }
        }
        val cancelButtonForBus = Button("Cancel")
        cancelButtonForBus.layoutX = 210.0
        cancelButtonForBus.layoutY = 160.0
        cancelButtonForBus.onAction = EventHandler { stage.close() }
        paneForBus.children.addAll(nameText,message, okButtonForBus, cancelButtonForBus, nameTextField)
        tabForBus.content = paneForBus

        /*Gate Tab*/
        val tabForGate = Tab("Gate")
        tabForGate.isClosable = false
        val paneForGate = Pane()
        val operationText = Text("Operation:")
        operationText.layoutX = 20.0
        operationText.layoutY = 27.0
        val okButtonForGate = Button("Ok")
        okButtonForGate.layoutX = 160.0
        okButtonForGate.layoutY = 160.0
        okButtonForGate.onAction = EventHandler {
            try {
                //main.circuitView.addGateView(GateView.Not())
                stage.close()
            }
            catch (ex : IllegalArgumentException){
                print(ex.message)
            }
        }
        val cancelButtonForGate = Button("Cancel")
        cancelButtonForGate.layoutX = 210.0
        cancelButtonForGate.layoutY = 160.0
        cancelButtonForGate.onAction = EventHandler { stage.close() }
        paneForGate.children.addAll(operationText,okButtonForGate, cancelButtonForGate)
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