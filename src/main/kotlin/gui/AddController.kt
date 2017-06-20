package gui

import Main
import javafx.event.EventHandler
import javafx.scene.Group
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.stage.Modality
import javafx.stage.Stage

class AddController {

    fun displayForBus(main: Main) {
        val stage = Stage()
        stage.title = "Add Bus"
        val root = Group()
        val scene = Scene(root, 300.0, 250.0)
        val btn = Button()
        btn.layoutX = 100.0
        btn.layoutY = 80.0
        btn.onAction = EventHandler { main.inBuses.add("x") }
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
        btn.onAction = EventHandler { main.inBuses.add("x") }
        root.children.add(btn)
        stage.isResizable = false
        stage.initModality(Modality.WINDOW_MODAL)
        stage.initOwner(main.scene.window)
        stage.scene = scene
        stage.show()
    }


}