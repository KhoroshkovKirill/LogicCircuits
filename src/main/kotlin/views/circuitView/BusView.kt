package views.circuitView

import javafx.scene.shape.Line
import javafx.scene.text.Text
import logic.Bus

class BusView(name: String, var x : Double, val bus: Bus) {
    val nameText = Text(name)
    val line = Line(x, 30.0, x, 400.0)

    init {
        nameText.layoutX = x
        nameText.layoutY = 20.0
    }

    fun rename(newName: String) {
        this.nameText.text = newName
    }
}