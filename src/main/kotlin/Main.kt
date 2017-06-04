import gui.Add
import javafx.application.*
import javafx.event.*
import javafx.scene.*
import javafx.scene.control.*
import javafx.scene.shape.Line
import javafx.stage.*

class Main : Application() {
    override fun start(primaryStage: Stage) {
        primaryStage.title = "Hello World"
        val root = Group()
        val scene = Scene(root, 300.0, 250.0)
        val btn = Button()
        btn.layoutX = 100.0//положение
        btn.layoutY = 80.0
        btn.text = "Hello"
        val line = Line(20.0,20.0,scene.width,40.0)
        btn.onAction = EventHandler { Add().start(Stage()) }
        root.children.add(btn)
        root.children.add(line)
        primaryStage.scene = scene
        primaryStage.show()
    }

    companion object {
        fun main(args: Array<String>) {
            Application.launch(*args)
        }
    }
}
