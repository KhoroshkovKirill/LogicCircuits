import gui.Add
import javafx.application.*
import javafx.event.EventHandler
import javafx.geometry.Insets
import javafx.geometry.Orientation
import javafx.scene.*
import javafx.scene.control.*
import javafx.scene.layout.BorderPane
import javafx.scene.layout.VBox
import javafx.stage.*
import logic.Bus
import views.BusesView
import javafx.scene.control.ToolBar
import javafx.scene.image.Image
import javafx.scene.image.ImageView



class Main : Application() {
    val inBuses = BusesView()
    val outBus = BusesView.BusView("y",Bus.Out())
    val down = TextArea()
    val borderPane = BorderPane()

    override fun start(primaryStage: Stage) {
        primaryStage.title = "Logic Circuits"

        val menuFile = Menu("File")
        val menuEdit = Menu("Edit")
        val menuView = Menu("View")
        val menuRun = Menu("Run")
        val itemCheck = MenuItem("Check")
        itemCheck.onAction = EventHandler {
            try {
                outBus.bus.calculateValue()
            }
            catch (ex : IllegalArgumentException){
                println(ex.message)
            }
        }
        menuRun.items.add(itemCheck)
        val menuBar = MenuBar(menuFile, menuEdit, menuView, menuRun)

        val addButton = Button("",ImageView(Image("add.png")))
        addButton.onAction = EventHandler { Add.display(this) }
        val checkButton = Button("",ImageView(Image("check.png")))
        checkButton.onAction = EventHandler {
            try {
                outBus.bus.calculateValue()
            }
            catch (ex : IllegalArgumentException){
                println(ex.message)
            }
        }
        val cleanButton = Button("",ImageView(Image("clean.png")))
        cleanButton.onAction = EventHandler {
            inBuses.children.removeAll()
        }


        val toolBar = ToolBar(
                addButton,
                Button("",ImageView(Image("delete.png"))),
                checkButton,
                cleanButton
        )
        toolBar.orientation = Orientation.VERTICAL

        borderPane.top = VBox(menuBar)
        borderPane.center = inBuses
        BorderPane.setMargin(inBuses, Insets(0.0, 0.0, 0.0, 20.0))
        borderPane.right = outBus
        BorderPane.setMargin(outBus, Insets(0.0, 20.0, 0.0, 0.0))
        borderPane.left = toolBar

        val scene = Scene(borderPane, 500.0, 500.0)
        scene.stylesheets.add("style.css")
        primaryStage.scene = scene
        primaryStage.show()
    }

    companion object {
        fun main(args: Array<String>) {
            Application.launch(*args)
        }
    }
}
