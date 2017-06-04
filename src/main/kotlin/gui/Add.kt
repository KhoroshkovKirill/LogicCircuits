package gui

import javafx.application.Application
import javafx.event.EventHandler
import javafx.scene.Group
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.stage.Stage

/**
 * Created by khoroshkovkirill on 04.06.17.
 */
class Add : Application() {
    override fun start(primaryStage: Stage) {
        primaryStage.title = "Add"
        val root = Group()
        val scene = Scene(root, 300.0, 250.0)
        val btn = Button()
        btn.layoutX = 100.0//положение
        btn.layoutY = 80.0
        btn.onAction = EventHandler { primaryStage.close() }
        root.children.add(btn)
        primaryStage.scene = scene
        primaryStage.show()
    }

    companion object {
        fun main(args: Array<String>) {
            Application.launch(*args)
        }
    }
}