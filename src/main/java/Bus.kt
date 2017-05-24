
sealed class Bus(var name: String, var value: Boolean) : LogElement {

    class InBus(name: String, value: Boolean) : Bus(name, value) {
        override fun calculateValue() = value
    }

    class OutBus(var previous: LogElement, name: String, value: Boolean) : Bus(name, value) {
        override fun calculateValue() {
            try {
                this.value = previous.calculateValue()
                return value
            } catch (ex: NullPointerException) {
                throw IllegalArgumentException("К выходной шине " + this.name + " не подведено соединение")
            }

        }
    }

}