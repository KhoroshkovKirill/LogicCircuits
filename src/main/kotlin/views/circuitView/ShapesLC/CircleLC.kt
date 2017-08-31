package views.circuitView.ShapesLC

import javafx.scene.shape.Circle
import views.circuitView.ElementView

open class CircleLC(val elementOwner: ElementView) : Circle(), ShapeLC {

    override fun getOwner(): ElementView = elementOwner
}