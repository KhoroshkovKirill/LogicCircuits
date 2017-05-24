
sealed class Dot(var inversion: Boolean, var previous: LogElement?) : LogElement{

    class InDot(inversion: Boolean, previous: OutDot?): Dot(inversion, previous) {
        override fun calculateValue(): Boolean {
            if (this.previous != null) {
                return previous!!.calculateValue().xor(inversion)
            }
            else {
                throw IllegalArgumentException("К логическому элементу не подведено соединение")
            }
        }
    }

    class OutDot(inversion: Boolean, previous: LogElement): Dot(inversion,previous) {
        override fun calculateValue(): Boolean {
            return this.previous!!.calculateValue().xor(inversion)
        }
    }

}