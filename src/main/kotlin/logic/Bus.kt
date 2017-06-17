package logic
sealed class Bus: LogElement {
    var value = false

    class In : logic.Bus(){
        val out : Dot.Out = Dot.Out(false, this)
        override fun calculateValue() = this.value
    }

    class Out: logic.Bus(){
        var previous: Dot? = null

        override fun calculateValue() : Boolean{
            if (this.previous != null) {
                this.value = previous!!.calculateValue()
            }
            else {
                this.value = false
                throw IllegalArgumentException("К выходной шине не подведено соединение")
            }
            return this.value
        }
    }

}