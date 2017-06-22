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
import views.circuitView.CircuitView
import views.truthTable.TruthTable

class Main : Application() {
    val inBuses = BusesView()
    val outBus = BusesView.BusView("y",Bus.Out())
    val console = TextArea()
    val borderPane = BorderPane()
    val circuitView = CircuitView()
    val truthTableWindow = VBox()
    var truthTable = TruthTable(circuitView.circuit)
    val menuBar = MainMenuBar(this)
    val splitPane = SplitPane(circuitView, truthTableWindow)
    val scene = Scene(borderPane, 500.0, 500.0)

    override fun start(primaryStage: Stage) {
        primaryStage.title = "Logic Circuits"

        /*Tools*/
        val addButton = Button("",ImageView(Image("add.png")))
        addButton.onAction = EventHandler { AddController().display(this) }
        val editButton = Button("",ImageView(Image("edit.png")))
        val checkButton = Button("",ImageView(Image("check.png")))
        checkButton.onAction = EventHandler { this.check() }
        val deleteButton = Button("",ImageView(Image("delete.png")))
        deleteButton.onAction = EventHandler { TODO() }

        val toolBar = ToolBar(
                addButton,
                editButton,
                checkButton,
                deleteButton
        )
        toolBar.orientation = Orientation.VERTICAL

        /*Truth table*/
        val refreshButton = Button("",ImageView(Image("refresh.png")))
        refreshButton.onAction = EventHandler { truthTable = TruthTable(circuitView.circuit) }
        val closeButton = Button("",ImageView(Image("close.png")))
        closeButton.onAction = EventHandler { TODO() }

        val toolsForTable = ToolBar(
                refreshButton,
                closeButton
        )
        toolsForTable.orientation = Orientation.HORIZONTAL
        truthTableWindow.children.addAll(toolsForTable,truthTable)

        /*BorderPane*/
        borderPane.top = VBox(menuBar)
        circuitView.content = inBuses
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
