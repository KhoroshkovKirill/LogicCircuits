package gui

import Main
import javafx.event.EventHandler
import javafx.scene.Group
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.stage.Stage

/**
 * Created by khoroshkovkirill on 04.06.17.
 */
class Add {

    companion object {
        @JvmStatic fun display(main: Main) {
            val window = Stage()
            window.title = "Add"
            val root = Group()
            val scene = Scene(root, 300.0, 250.0)
            val btn = Button()
            btn.layoutX = 100.0//положение
            btn.layoutY = 80.0
            btn.onAction = EventHandler { main.inBuses.add("x") }
            root.children.add(btn)
            window.scene = scene
            window.show()
        }
    }

}