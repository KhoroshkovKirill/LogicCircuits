
sealed class Gate : LogElement{

    class NotGate(previous: Dot.OutDot) : Gate(){
        val output : Dot = Dot.OutDot(true, this)
        val input : Dot = Dot.InDot(false, previous)
        override fun calculateValue(): Boolean {
            return input.calculateValue()
        }
    }

    sealed class Multivariate(var inversion : Boolean, previousList : MutableList<Dot.OutDot>) : Gate() {
        var output : Dot = Dot.OutDot(inversion, this)
        var inputList = mutableListOf<Dot.InDot>()
        init {
            previousList.forEach { inputList.add(Dot.InDot(false, it)) }
        }

        class And(inversion: Boolean, previousList: MutableList<Dot.OutDot>) :
                Gate.Multivariate(inversion, previousList){
            override fun calculateValue(): Boolean {
                return inputList.all { it.calculateValue() == true }
            }
        }

        class Or(inversion: Boolean, previousList: MutableList<Dot.OutDot>) :
                Gate.Multivariate(inversion, previousList){
            override fun calculateValue(): Boolean {
                return inputList.any { it.calculateValue() == true }
            }
        }

        class Xor(inversion: Boolean, previousList: MutableList<Dot.OutDot>) :
                Gate.Multivariate(inversion, previousList){
            override fun calculateValue(): Boolean {
                var rez : Boolean = false
                inputList.forEach { rez = it.calculateValue().xor(rez) }
                return rez
            }
        }
    }
}