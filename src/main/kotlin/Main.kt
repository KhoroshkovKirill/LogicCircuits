import javafx.application.*
import javafx.event.EventHandler
import javafx.geometry.Insets
import javafx.scene.*
import javafx.scene.control.*
import javafx.scene.layout.BorderPane
import javafx.scene.layout.VBox
import javafx.stage.*
import logic.Bus
import views.BusesView

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
        val itemTry = MenuItem("Try")
        menuRun.items.add(itemTry)
        itemTry.onAction = EventHandler {
            try {
                outBus.bus.calculateValue()
            }
            catch (ex : IllegalArgumentException){
                println(ex.message)
            }
        }
        val menuBar = MenuBar(menuFile, menuEdit, menuView, menuRun)

        borderPane.top = VBox(menuBar)
        borderPane.left = inBuses
        BorderPane.setMargin(inBuses, Insets(0.0, 0.0, 0.0, 20.0))
        borderPane.right = outBus
        BorderPane.setMargin(outBus, Insets(0.0, 20.0, 0.0, 0.0))
        borderPane.bottom = down


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
