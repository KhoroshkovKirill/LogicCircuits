package gui

import Main
import javafx.event.EventHandler
import javafx.scene.Group
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.layout.Pane
import javafx.stage.Modality
import javafx.stage.Stage

class AddController {

    fun display(main: Main){
        val stage = Stage()
        stage.title = "Add"
        val root = Pane()
        val scene = Scene(root, 300.0, 100.0)
        val addBusButton = Button("Bus")
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
        root.children.addAll(addBusButton,addGateButton,cancelButton)
        //stage.isResizable = false
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