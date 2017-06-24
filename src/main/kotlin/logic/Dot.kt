package logic
sealed class Dot : LogElement {
    protected var inversion = false

    fun changeInversion(){
            this.inversion = !this.inversion
    }

    class In: logic.Dot() {
        var previous : Dot.Out? = null

        fun changePrevious(newPrevious: Dot.Out?) {
            if (previous != null){
                previous!!.deleteFromNextList(this)
            }
            if (newPrevious != null){
                newPrevious.next.add(this)
            }
            previous = newPrevious
        }

        override fun calculateValue(): Boolean {
            if (this.previous != null) {
                return previous!!.calculateValue().xor(inversion)
            }
            else {
                throw IllegalArgumentException("К логическому элементу не подведено соединение")
            }
        }
    }

    class Out(private val previous: LogElement): logic.Dot() {
        val next = mutableSetOf<Dot.In>()

        constructor(previous: LogElement, isInverted: Boolean) : this(previous){
            this.inversion = isInverted
        }

        fun deleteFromPrevious(){
            for (inPut in next){
                inPut.previous = null
            }
        }

        fun deleteFromNextList(dot: Dot.In){
            next.remove(dot)
        }

        override fun calculateValue(): Boolean {
            return this.previous.calculateValue().xor(inversion)
        }
    }

}