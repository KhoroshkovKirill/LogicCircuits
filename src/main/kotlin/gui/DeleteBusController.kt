package gui

import Main
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.TextField
import javafx.scene.layout.Pane
import javafx.scene.text.Text
import javafx.stage.Modality
import javafx.stage.Stage

class DeleteBusController{

    fun display(main: Main){
        val stage = Stage()
        stage.title = "Delete Bus"

        val indexTextField = TextField()
        indexTextField.layoutX = 100.0
        indexTextField.layoutY = 20.0
        val indexText = Text(20.0, 37.0, "Index:")
        val message = Text(20.0, 80.0, "")
        val okButton = Button("Ok")
        okButton.layoutX = 160.0
        okButton.layoutY = 190.0
        okButton.onAction = EventHandler {
            try {
                main.circuitView.deleteInBus(indexTextField.text.toInt())
                stage.close()
            }
            catch (ex : NumberFormatException){
                message.text = "Неправильно введен индекс"
            }
            catch (ex : IllegalArgumentException){
                message.text = ex.message
            }
            catch (ex : IndexOutOfBoundsException){
                message.text = ex.message
            }

        }
        val cancelButton = Button("Cancel")
        cancelButton.layoutX = 210.0
        cancelButton.layoutY = 190.0
        cancelButton.onAction = EventHandler { stage.close() }

        /*Scene*/
        val root = Pane(indexText, indexTextField, okButton, cancelButton, message)
        val scene = Scene(root, 300.0, 250.0)
        stage.isResizable = false
        stage.initModality(Modality.WINDOW_MODAL)
        stage.initOwner(main.scene.window)
        stage.scene = scene
        stage.show()
    }
}