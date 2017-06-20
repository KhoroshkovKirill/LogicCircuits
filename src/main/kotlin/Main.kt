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
import views.BusesView
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
        deleteButton.onAction = EventHandler { inBuses.autosize() }

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

    fun check(){
        try {
            outBus.bus.calculateValue()
        } catch (ex: IllegalArgumentException) {
            console.text = ex.localizedMessage
        }
    }

    fun deleteBus(i : Int){
        try {
            inBuses.children.remove(i, i)
        }
        catch (ex: Exception ){
            console.text = ex.toString()
        }
    }

    companion object {
        fun main(args: Array<String>) {
            Application.launch(*args)
        }
    }
}
