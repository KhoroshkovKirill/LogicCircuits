package views.circuitView.ShapesLC

import javafx.scene.shape.Rectangle
import views.circuitView.ElementView

class RectangleLC(val elementOwner: ElementView) : Rectangle(), ShapeLC {

    override fun getOwner(): ElementView = elementOwner
}