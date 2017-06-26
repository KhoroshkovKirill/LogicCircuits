package gui

import Main
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.Tab
import javafx.scene.control.TabPane
import javafx.scene.control.TextField
import javafx.scene.layout.Pane
import javafx.scene.text.Text
import javafx.stage.Modality
import javafx.stage.Stage

class RenameBusController {

    fun display(main: Main){
        val stage = Stage()
        stage.title = "Rename"

        /*InBus Tab*/
        val tabForInBus = Tab("In")
        tabForInBus.isClosable = false
        val paneForInBus = Pane()
        val newNameInTextField = TextField()
        newNameInTextField.layoutX = 100.0
        newNameInTextField.layoutY = 10.0
        val newNameInText = Text(20.0, 27.0, "New name:")
        val indexInTextField = TextField()
        indexInTextField.layoutX = 100.0
        indexInTextField.layoutY = 50.0
        val indexText = Text(20.0, 67.0, "Index:")
        val messageForInBus = Text(20.0, 110.0, "")
        val okButtonForInBus = Button("Ok")
        okButtonForInBus.layoutX = 160.0
        okButtonForInBus.layoutY = 160.0
        okButtonForInBus.onAction = EventHandler {
            try {
                main.circuitView.renameInBus(indexInTextField.text.toInt(),newNameInTextField.text)
                stage.close()
            }
            catch (ex : NumberFormatException){
                messageForInBus.text = "Неправильно введен индекс"
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
                indexText,messageForInBus, okButtonForInBus, cancelButtonForInBus,
                indexInTextField, newNameInText, newNameInTextField)
        tabForInBus.content = paneForInBus

        /*OutBus Tab*/
        val tabForOutBus = Tab("Out")
        tabForOutBus.isClosable = false
        val paneForOutBus = Pane()
        val newNameOutTextField = TextField()
        newNameOutTextField.layoutX = 100.0
        newNameOutTextField.layoutY = 10.0
        val mewNameOutText = Text(20.0, 27.0, "New name:")
        val messageOutBus = Text(20.0,60.0,"")
        val okButtonForOutBus = Button("Ok")
        okButtonForOutBus.layoutX = 160.0
        okButtonForOutBus.layoutY = 160.0
        okButtonForOutBus.onAction = EventHandler {
            try {
                main.circuitView.renameOutBus(newNameOutTextField.text)
                stage.close()
            }
            catch (ex : IllegalArgumentException){
                messageOutBus.text = ex.message
            }
        }
        val cancelButtonForOutBus = Button("Cancel")
        cancelButtonForOutBus.layoutX = 210.0
        cancelButtonForOutBus.layoutY = 160.0
        cancelButtonForOutBus.onAction = EventHandler { stage.close() }
        paneForOutBus.children.addAll(
                mewNameOutText,messageOutBus, okButtonForOutBus, cancelButtonForOutBus, newNameOutTextField)
        tabForOutBus.content = paneForOutBus

        /*Scene*/
        val root = TabPane(tabForInBus,tabForOutBus)
        val scene = Scene(root, 300.0, 250.0)
        stage.isResizable = false
        stage.initModality(Modality.WINDOW_MODAL)
        stage.initOwner(main.scene.window)
        stage.scene = scene
        stage.show()
    }
}