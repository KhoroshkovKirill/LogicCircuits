package views.circuitView.ShapesLC

import javafx.scene.text.Text
import views.circuitView.ElementView

class TextLC(val elementOwner: ElementView) : Text(), ShapeLC {

    override fun getOwner(): ElementView = elementOwner
}