import gui.AddWindow
import gui.MainMenuBar
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
    val console = TextArea("...")
    val borderPane = BorderPane()
    val menuBar = MainMenuBar(this)

    override fun start(primaryStage: Stage) {
        primaryStage.title = "Logic Circuits"

        val addButton = Button("",ImageView(Image("add.png")))
        addButton.onAction = EventHandler { this.add() }
        val checkButton = Button("",ImageView(Image("check.png")))
        checkButton.onAction = EventHandler { this.check() }
        val deleteButton = Button("",ImageView(Image("delete.png")))
        deleteButton.onAction = EventHandler { this.delete() }

        val toolBar = ToolBar(
                addButton,
                checkButton,
                deleteButton
        )
        toolBar.orientation = Orientation.VERTICAL

        borderPane.top = VBox(menuBar)
        borderPane.center = inBuses
        BorderPane.setMargin(inBuses, Insets(0.0, 0.0, 0.0, 20.0))
        borderPane.right = outBus
        BorderPane.setMargin(outBus, Insets(0.0, 20.0, 0.0, 0.0))
        borderPane.left = toolBar
        borderPane.bottom = console

        val scene = Scene(borderPane, 500.0, 500.0)
        scene.stylesheets.add("style.css")
        primaryStage.scene = scene
        primaryStage.show()
    }

    fun  add(){
        AddWindow.display(this)
    }

    fun check(){
        try {
            outBus.bus.calculateValue()
        } catch (ex: IllegalArgumentException) {
            console.text += ( "\n" + ex.message )
        }
    }

    fun delete(){
        inBuses.children.removeAll()
    }

    companion object {
        fun main(args: Array<String>) {
            Application.launch(*args)
        }
    }
}
