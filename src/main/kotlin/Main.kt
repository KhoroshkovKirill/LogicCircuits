import gui.*
import javafx.application.*
import javafx.event.EventHandler
import javafx.geometry.Orientation
import javafx.scene.*
import javafx.scene.control.*
import javafx.scene.layout.BorderPane
import javafx.scene.layout.VBox
import javafx.stage.*
import javafx.scene.control.ToolBar
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import views.circuitView.CircuitView
import views.truthTable.TruthTable

class Main : Application() {
    val circuitView  = CircuitView()
    val console = TextArea()
    val borderPane = BorderPane()
    val truthTableWindow = VBox()
    val truthTable = TruthTable()
    val menuBar = MainMenuBar(this)
    var splitPane = SplitPane(ScrollPane(circuitView), truthTableWindow)
    val scene = Scene(borderPane, 500.0, 500.0)

    override fun start(primaryStage: Stage) {
        primaryStage.title = "Logic Circuits"

        /*Tools*/
        val addButton = Button("",ImageView(Image("add.png")))
        addButton.onAction = EventHandler { AddController().display(this) }
        val editButton = Button("",ImageView(Image("edit.png")))
        editButton.onAction = EventHandler { RenameBusController().display(this) }
        val checkButton = Button("",ImageView(Image("check.png")))
        checkButton.onAction = EventHandler { this.check() }
        val deleteButton = Button("",ImageView(Image("delete.png")))
        deleteButton.onAction = EventHandler { DeleteController().display(this) }

        val toolBar = ToolBar(
                addButton,
                editButton,
                checkButton,
                deleteButton
        )
        toolBar.orientation = Orientation.VERTICAL

        /*Truth table*/
        val refreshButton = Button("",ImageView(Image("refresh.png")))
        refreshButton.onAction = EventHandler {
            //if (check()) {
                truthTable.refresh(circuitView)
            //}
        }
        val clearButton = Button("",ImageView(Image("clear.png")))
        clearButton.onAction = EventHandler { truthTable.clear() }

        val closeButton = Button("",ImageView(Image("hide.png")))
        closeButton.onAction = EventHandler {
            splitPane.items.remove(truthTableWindow)
        }

        val toolsForTable = ToolBar(
                refreshButton,
                clearButton,
                closeButton
        )
        toolsForTable.orientation = Orientation.HORIZONTAL
        truthTableWindow.children.addAll(toolsForTable,ScrollPane(truthTable))

        /*BorderPane*/
        borderPane.top = VBox(menuBar)
        borderPane.center = splitPane
        borderPane.left = toolBar
        console.prefHeight = 75.0
        console.isEditable = false
        borderPane.bottom = console

        /*Show*/
        scene.stylesheets.add("style.css")
        primaryStage.scene = scene
        primaryStage.show()
    }

    fun check() : Boolean{
        try {
            circuitView.circuit.outBus.calculateValue()
            return true
        } catch (ex: IllegalArgumentException) {
            printMessage(ex.localizedMessage)
            return false
        }
    }

    fun printMessage(message : String){
        console.text = message
    }

    companion object {
        fun main(args: Array<String>) {
            Application.launch(*args)
        }
    }
}
