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

        /*ForBus Tab*/
        val tabForBus = Tab("Bus")
        tabForBus.isClosable = false
        val paneForBus = Pane()
        val indexBusField = TextField()
        indexBusField.layoutX = 75.0
        indexBusField.layoutY = 10.0
        val indexText = Text("Index:")
        indexText.layoutX = 20.0
        indexText.layoutY = 27.0
        val messageForBus = Text()
        messageForBus.layoutX = 20.0
        messageForBus.layoutY = 60.0
        val okButtonForBus = Button("Ok")
        okButtonForBus.layoutX = 160.0
        okButtonForBus.layoutY = 160.0
        okButtonForBus.onAction = EventHandler {
            try {
                main.circuitView.deleteInBus(indexBusField.text.toInt())
                stage.close()
            }
            catch (ex : NumberFormatException){
                messageForBus.text = "Неправильно введен индекс"
            }
            catch (ex : IllegalArgumentException){
                messageForBus.text = ex.message
            }
            catch (ex : IndexOutOfBoundsException){
                messageForBus.text = ex.message
            }
        }
        val cancelButtonForBus = Button("Cancel")
        cancelButtonForBus.layoutX = 210.0
        cancelButtonForBus.layoutY = 160.0
        cancelButtonForBus.onAction = EventHandler { stage.close() }
        paneForBus.children.addAll(indexText,messageForBus, okButtonForBus, cancelButtonForBus, indexBusField)
        tabForBus.content = paneForBus

        /*ForGate Tab*/
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
        val root = TabPane(tabForBus,tabForGate)
        val scene = Scene(root, 300.0, 250.0)
        stage.isResizable = false
        stage.initModality(Modality.WINDOW_MODAL)
        stage.initOwner(main.scene.window)
        stage.scene = scene
        stage.show()
    }
}
