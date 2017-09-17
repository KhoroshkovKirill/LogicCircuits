package gui

import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.scene.control.*
import javafx.scene.layout.Pane
import javafx.scene.text.Text
import javafx.stage.Modality
import javafx.stage.Stage
import Main

class DeleteController(){

    fun display(main: Main){
        val stage = Stage()
        stage.title = "Delete"

        /*In Bus Tab*/
        val tabForInBus = Tab("Input")
        tabForInBus.isClosable = false
        val paneForInBus = Pane()
        val indexInBusField = TextField()
        indexInBusField.layoutX = 75.0
        indexInBusField.layoutY = 10.0
        val indexTextForInBus = Text("Index:")
        indexTextForInBus.layoutX = 20.0
        indexTextForInBus.layoutY = 27.0
        val messageForInBus = Text()
        messageForInBus.layoutX = 20.0
        messageForInBus.layoutY = 60.0
        val okButtonForInBus = Button("Ok")
        okButtonForInBus.layoutX = 160.0
        okButtonForInBus.layoutY = 160.0
        okButtonForInBus.onAction = EventHandler {
            try {
                main.circuitView.deleteInBus(indexInBusField.text.toInt())
                stage.close()
            }
            catch (ex : NumberFormatException){
                messageForInBus.text = "Неправильно введен индекс"
            }
            catch (ex : IllegalArgumentException){
                messageForInBus.text = ex.message
            }
            catch (ex : IndexOutOfBoundsException){
                messageForInBus.text = ex.message
            }
        }
        val cancelButtonForInBus = Button("Cancel")
        cancelButtonForInBus.layoutX = 210.0
        cancelButtonForInBus.layoutY = 160.0
        cancelButtonForInBus.onAction = EventHandler { stage.close() }
        paneForInBus.children.addAll(indexTextForInBus,messageForInBus, okButtonForInBus, cancelButtonForInBus, indexInBusField)
        tabForInBus.content = paneForInBus

        /*Out Bus Tab*/
        val tabForOutBus = Tab("Output")
        tabForOutBus.isClosable = false
        val paneForOutBus = Pane()
        val indexOutBusField = TextField()
        indexOutBusField.layoutX = 75.0
        indexOutBusField.layoutY = 10.0
        val indexTextForOutBus = Text("Index:")
        indexTextForOutBus.layoutX = 20.0
        indexTextForOutBus.layoutY = 27.0
        val messageForOutBus = Text()
        messageForOutBus.layoutX = 20.0
        messageForOutBus.layoutY = 60.0
        val okButtonForOutBus = Button("Ok")
        okButtonForOutBus.layoutX = 160.0
        okButtonForOutBus.layoutY = 160.0
        okButtonForOutBus.onAction = EventHandler {
            try {
                main.circuitView.deleteOutBus(indexOutBusField.text.toInt())
                stage.close()
            }
            catch (ex : NumberFormatException){
                messageForOutBus.text = "Неправильно введен индекс"
            }
            catch (ex : IllegalArgumentException){
                messageForOutBus.text = ex.message
            }
            catch (ex : IndexOutOfBoundsException){
                messageForOutBus.text = ex.message
            }
        }
        val cancelButtonForOutBus = Button("Cancel")
        cancelButtonForOutBus.layoutX = 210.0
        cancelButtonForOutBus.layoutY = 160.0
        cancelButtonForOutBus.onAction = EventHandler { stage.close() }
        paneForOutBus.children.addAll(indexTextForOutBus,messageForOutBus, okButtonForOutBus, cancelButtonForOutBus, indexOutBusField)
        tabForOutBus.content = paneForOutBus

        /*Gate Tab*/
        val tabForGate = Tab("Gate")
        tabForGate.isClosable = false
        val paneForGate = Pane()
        val columnText = Text(20.0, 27.0, "Column:")
        val columnTextField = TextField()
        columnTextField.layoutX = 100.0
        columnTextField.layoutY = 50.0
        val rowText = Text(20.0,67.0,"Row:")
        val rowTextField = TextField()
        rowTextField.layoutX = 100.0
        rowTextField.layoutY = 10.0
        val messageForGate = Text()
        messageForGate.layoutX = 20.0
        messageForGate.layoutY = 110.0
        val okButtonForGate = Button("Ok")
        okButtonForGate.layoutX = 160.0
        okButtonForGate.layoutY = 160.0
        okButtonForGate.onAction = EventHandler {
            try {
                main.circuitView.deleteGate(columnTextField.text.toInt(), rowTextField.text.toInt())
                stage.close()
            }
            catch (ex : NumberFormatException){
                messageForGate.text = "Неправильно введен индекс"
            }
            catch (ex : IllegalArgumentException){
                messageForGate.text = ex.message
            }
            catch (ex : IndexOutOfBoundsException){
                messageForGate.text = ex.message
            }
        }
        val cancelButtonForGate = Button("Cancel")
        cancelButtonForGate.layoutX = 210.0
        cancelButtonForGate.layoutY = 160.0
        cancelButtonForGate.onAction = EventHandler { stage.close() }
        paneForGate.children.addAll(columnText, rowText, okButtonForGate, cancelButtonForGate,
                rowTextField, columnTextField, messageForGate)
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
