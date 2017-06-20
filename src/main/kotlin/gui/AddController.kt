package gui

import Main
import javafx.event.EventHandler
import javafx.scene.Group
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.TabPane
import javafx.stage.Modality
import javafx.stage.Stage
import javafx.scene.control.Tab
import javafx.scene.control.TextField
import javafx.scene.layout.Pane


class AddController {

    fun display(main: Main){//tabPane
        val stage = Stage()
        stage.title = "Add"

        /*Add Bus*/
        val tabForBus = Tab("Bus")
        val paneForBus = Pane()
        val okButtonForBus = Button("Ok")
        val nameBus = TextField()
        okButtonForBus.layoutX = 150.0
        okButtonForBus.layoutY = 150.0
        okButtonForBus.onAction = EventHandler {
            try {
                main.inBuses.add(nameBus.text)
                stage.close()
            }
            catch (ex : IllegalArgumentException){
                print(ex.message)
            }
        }
        val cancelButtonForBus = Button("Cancel")
        cancelButtonForBus.layoutX = 200.0
        cancelButtonForBus.layoutY = 150.0
        cancelButtonForBus.onAction = EventHandler { stage.close() }
        paneForBus.children.addAll(okButtonForBus,cancelButtonForBus,nameBus)
        tabForBus.content = paneForBus

        /*Add Gate*/
        val addGateTab = Tab("Gate")
        val root = TabPane(tabForBus,addGateTab)
        val scene = Scene(root, 300.0, 250.0)
        /*val addBusButton = Button("Bus")
        addBusButton.layoutX = 50.0
        addBusButton.layoutY = 20.0
        addBusButton.onAction = EventHandler {
            displayForBus(main)
            stage.close()
        }
        val addGateButton = Button("Gate")
        addGateButton.layoutX = 150.0
        addGateButton.layoutY = 20.0
        addGateButton.onAction = EventHandler {
            displayFoGate(main)
            stage.close()
        }
        val cancelButton = Button("Cancel")
        cancelButton.layoutX = 150.0
        cancelButton.layoutY = 60.0
        cancelButton.onAction = EventHandler {
            stage.close()
        }
        root.children.addAll(addBusButton,addGateButton,cancelButton)*/
        stage.isResizable = false
        stage.initModality(Modality.WINDOW_MODAL)
        stage.initOwner(main.scene.window)
        stage.scene = scene
        stage.show()
    }

    fun displayForBus(main: Main) {
        val stage = Stage()
        stage.title = "Add Bus"
        val root = Group()
        val scene = Scene(root, 300.0, 250.0)
        val btn = Button()
        btn.layoutX = 100.0
        btn.layoutY = 80.0
        btn.onAction = EventHandler {
            main.inBuses.add("x")
            stage.close()
        }
        root.children.add(btn)
        stage.isResizable = false
        stage.initModality(Modality.WINDOW_MODAL)
        stage.initOwner(main.scene.window)
        stage.scene = scene
        stage.show()
    }

    fun displayFoGate(main: Main) {
        val stage = Stage()
        stage.title = "Add Gate"
        val root = Group()
        val scene = Scene(root, 300.0, 250.0)
        val btn = Button()
        btn.layoutX = 50.0
        btn.layoutY = 80.0
        btn.onAction = EventHandler {
            main.inBuses.add("x")
            stage.close()
        }
        root.children.add(btn)
        stage.isResizable = false
        stage.initModality(Modality.WINDOW_MODAL)
        stage.initOwner(main.scene.window)
        stage.scene = scene
        stage.show()
    }


}