package logic
sealed class Dot : LogElement {
    var isInverted = false

    fun changeInversion() {
        this.isInverted = !this.isInverted
    }

    class In: logic.Dot() {
        var previous : Dot.Out? = null

        fun changePrevious(newPrevious: Dot.Out?) {
            if (previous != null){
                previous!!.deleteFromNextList(this)
            }
            if (newPrevious != null){
                newPrevious.nextDots.add(this)
            }
            previous = newPrevious
        }

        override fun calculateValue(): Boolean {
            if (this.previous != null) {
                return previous!!.calculateValue().xor(isInverted)
            }
            else {
                throw IllegalArgumentException("К логическому элементу не подведено соединение")
            }
        }
    }

    class Out(private val previous: LogElement): logic.Dot() {
        val nextDots = mutableSetOf<Dot.In>() //

        constructor(previous: LogElement, isInverted: Boolean) : this(previous){
            this.isInverted = isInverted
        }

        fun deleteFromPrevious(){ //обнуляет ссылки на эту точку у след. точек
            for (inDot in nextDots){
                inDot.previous = null
            }
        }

        fun deleteFromNextList(dot: Dot.In){
            nextDots.remove(dot)
        }

        override fun calculateValue(): Boolean {
            return this.previous.calculateValue().xor(isInverted)
        }
    }

}