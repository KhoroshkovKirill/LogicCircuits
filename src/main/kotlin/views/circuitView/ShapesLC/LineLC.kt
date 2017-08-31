package views.circuitView.ShapesLC

import javafx.scene.shape.Line
import views.circuitView.ElementView

class LineLC(val elementOwner: ElementView) : Line(), ShapeLC {

    override fun getOwner(): ElementView = elementOwner
}