import gui.AddController
import gui.MainMenuBar
import javafx.application.*
import javafx.event.EventHandler
import javafx.geometry.Orientation
import javafx.scene.*
import javafx.scene.control.*
import javafx.scene.layout.BorderPane
import javafx.scene.layout.VBox
import javafx.stage.*
import logic.Bus
import views.circuitView.BusesView
import javafx.scene.control.ToolBar
import javafx.scene.image.Image
import javafx.scene.image.ImageView

class Main : Application() {
    val inBuses = BusesView()
    val outBus = BusesView.BusView("y",Bus.Out())
    val console = TextArea()
    val borderPane = BorderPane()
    val centerScrollPane = ScrollPane()
    val menuBar = MainMenuBar(this)
    val splitPane = SplitPane(centerScrollPane, outBus)
    val scene = Scene(borderPane, 500.0, 500.0)

    override fun start(primaryStage: Stage) {
        primaryStage.title = "Logic Circuits"

        /*Tools*/
        val addButton = Button("",ImageView(Image("add.png")))
        addButton.onAction = EventHandler { AddController().display(this) }
        val checkButton = Button("",ImageView(Image("check.png")))

        checkButton.onAction = EventHandler { this.check() }
        val deleteButton = Button("",ImageView(Image("delete.png")))
        deleteButton.onAction = EventHandler { deleteBus(2) }

        val toolBar = ToolBar(
                addButton,
                checkButton,
                deleteButton
        )
        toolBar.orientation = Orientation.VERTICAL

        /*BorderPane*/
        borderPane.top = VBox(menuBar)
        centerScrollPane.content = inBuses
        borderPane.center = splitPane
        borderPane.left = toolBar
        console.prefHeight = 75.0
        borderPane.bottom = console

        /*Show*/
        scene.stylesheets.add("style.css")
        primaryStage.scene = scene
        primaryStage.show()
    }

    fun check() : Boolean{
        try {
            outBus.bus.calculateValue()
            return true
        } catch (ex: IllegalArgumentException) {
            printMessage(ex.localizedMessage)
            return false
        }
    }

    fun printMessage(message : String){
        console.text = message
    }

    fun deleteBus(i : Int){
        try {
            inBuses.children.remove(3, 6)
        }
        catch (ex: Exception ){
            printMessage(ex.toString())
        }
    }

    companion object {
        fun main(args: Array<String>) {
            Application.launch(*args)
        }
    }
}
