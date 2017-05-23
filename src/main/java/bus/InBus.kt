package bus
import LogElement

open class InBus(var name: String,public var value: Boolean) : LogElement {

    override fun calculateValue() = value

}