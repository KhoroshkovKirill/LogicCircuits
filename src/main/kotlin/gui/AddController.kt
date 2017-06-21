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


class AddController {

    fun display(main: Main){
        val stage = Stage()
        stage.title = "Add"

        /*Bus Tab*/
        val tabForBus = Tab("Bus")
        tabForBus.isClosable = false
        val paneForBus = Pane()
        val okButtonForBus = Button("Ok")
        val textFieldForBus = TextField()
        okButtonForBus.layoutX = 160.0
        okButtonForBus.layoutY = 160.0
        okButtonForBus.onAction = EventHandler {
            try {
                main.inBuses.add(textFieldForBus.text)
                stage.close()
            }
            catch (ex : IllegalArgumentException){
                print(ex.message)
            }
        }
        val cancelButtonForBus = Button("Cancel")
        cancelButtonForBus.layoutX = 210.0
        cancelButtonForBus.layoutY = 160.0
        cancelButtonForBus.onAction = EventHandler { stage.close() }
        paneForBus.children.addAll(okButtonForBus, cancelButtonForBus, textFieldForBus)
        tabForBus.content = paneForBus

        /*Gate Tab*/
        val tabForGate = Tab("Gate")
        tabForGate.isClosable = false
        val paneForGate = Pane()
        val okButtonForGate = Button("Ok")
        okButtonForGate.layoutX = 160.0
        okButtonForGate.layoutY = 160.0
        okButtonForGate.onAction = EventHandler {
            try {
                //-----------------------------------------
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
        paneForGate.children.addAll(okButtonForGate, cancelButtonForGate)
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