import gui.Add
import javafx.application.*
import javafx.event.*
import javafx.scene.*
import javafx.scene.control.*
import javafx.scene.layout.StackPane
import javafx.scene.shape.Line
import javafx.stage.*

class Main : Application() , EventHandler<ActionEvent> {
    var button = Button()
    var button1 = Button()

    override fun start(primaryStage: Stage) {
        primaryStage.title = "Hello World"

        //val button = Button()
        button.layoutX = 100.0//положение
        button.layoutY = 80.0
        button.text = "Hello"
        button.onAction = this
        //val button1 = Button()
        button1.layoutX = 200.0//положение
        button1.layoutY = 80.0
        button1.text = "Bello"
        button1.onAction = this

        val line = Line(20.0,20.0,40.0,40.0)

        val layout = Group()
        layout.children.add(button)
        layout.children.add(button1)
        layout.children.add(line)

        val scene = Scene(layout, 300.0, 250.0)
        primaryStage.scene = scene
        primaryStage.show()
    }

    override fun handle(event: ActionEvent) {
        if (event.source == button) {
            Add().start(Stage())
        }
        if (event.source == button1) {
            button1.text = "Hello"
        }
    }

    companion object {
        fun main(args: Array<String>) {
            Application.launch(*args)
        }
    }
}
