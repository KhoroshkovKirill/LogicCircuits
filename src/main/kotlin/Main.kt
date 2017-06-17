import gui.Add
import javafx.application.*
import javafx.event.EventHandler
import javafx.scene.*
import javafx.scene.control.*
import javafx.scene.layout.BorderPane
import javafx.scene.layout.VBox
import javafx.stage.*
import views.BusesView

class Main : Application() {
    val busesView = BusesView()
    val borderPane = BorderPane()
    val button = Button()

    override fun start(primaryStage: Stage) {
        primaryStage.title = "Logic Circuits"


        val menuFile = Menu("File")
        val menuEdit = Menu("Edit")
        val menuView = Menu("View")
        val menuBar = MenuBar(menuFile, menuEdit, menuView)

        borderPane.top = VBox(menuBar)
        borderPane.left = busesView
        borderPane.bottom = button

        button.onAction = EventHandler { Add.display(this) }

        val scene = Scene(borderPane, 500.0, 500.0)
        primaryStage.scene = scene
        primaryStage.show()
    }

    companion object {
        fun main(args: Array<String>) {
            Application.launch(*args)
        }
    }
}
