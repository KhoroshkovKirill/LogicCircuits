
sealed class Bus(var name: String, var value: Boolean): LogElement {

    class InBus(name: String, value: Boolean) : Bus(name, value){
        val out : Dot.OutDot = Dot.OutDot(false, this)
        override fun calculateValue() = this.value
    }

    class OutBus(name: String, value: Boolean, var previous: Dot?) : Bus(name, value){
        override fun calculateValue() : Boolean{
            if (this.previous != null) {
                this.value = previous!!.calculateValue()
            }
            else {
                this.value = false
                throw IllegalArgumentException("К выходной шине " + this.name + " не подведено соединение")
            }
            return this.value
        }
    }

}