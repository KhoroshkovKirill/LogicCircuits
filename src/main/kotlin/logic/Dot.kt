package logic
sealed class Dot : LogElement {
    var inversion = false

    class In: logic.Dot() {
        var previous : Dot.Out? = null

        override fun calculateValue(): Boolean {
            if (this.previous != null) {
                return previous!!.calculateValue().xor(inversion)
            }
            else {
                throw IllegalArgumentException("К логическому элементу не подведено соединение")
            }
        }
    }

    class Out(val previous: LogElement): logic.Dot() {

        override fun calculateValue(): Boolean {
            return this.previous.calculateValue().xor(inversion)
        }
    }

}