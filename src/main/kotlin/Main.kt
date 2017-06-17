import gui.Add
import javafx.application.*
import javafx.scene.*
import javafx.scene.control.*
import javafx.scene.layout.BorderPane
import javafx.scene.layout.VBox
import javafx.stage.*
import views.BusesView

class Main : Application() {
    var button = Button()
    var button1 = Button()
    val busesView = BusesView()
    val layout = VBox()
    val borderPane = BorderPane()
    val menu = ToolBar()

    override fun start(primaryStage: Stage) {
        primaryStage.title = "Logic Circuits"


        val menuFile = Menu("File")
        val menuEdit = Menu("Edit")
        val menuView = Menu("View")
        val menuBar = MenuBar(menuFile, menuEdit, menuView)

        borderPane.top = menuBar

        layout.children.add(borderPane)

        val scene = Scene(layout, 500.0, 500.0)
        primaryStage.scene = scene
        primaryStage.show()
    }

    companion object {
        fun main(args: Array<String>) {
            Application.launch(*args)
        }
    }
}
