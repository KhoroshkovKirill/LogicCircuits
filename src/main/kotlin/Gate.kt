
sealed class Gate : LogElement{

    class Not(previous: Dot.Out) : Gate(){
        val output : Dot = Dot.Out(true, this)
        val input : Dot = Dot.In(false, previous)
        override fun calculateValue(): Boolean {
            return input.calculateValue()
        }
    }

    sealed class Multivariate(var inversion : Boolean, previousList : MutableList<Dot.Out>) : Gate() {
        var output : Dot = Dot.Out(inversion, this)
        var inputList = mutableListOf<Dot.In>()
        init {
            previousList.forEach { inputList.add(Dot.In(false, it)) }
        }

        class And(inversion: Boolean, previousList: MutableList<Dot.Out>) :
                Gate.Multivariate(inversion, previousList){
            override fun calculateValue(): Boolean {
                return inputList.all { it.calculateValue() }
            }
        }

        class Or(inversion: Boolean, previousList: MutableList<Dot.Out>) :
                Gate.Multivariate(inversion, previousList){
            override fun calculateValue(): Boolean {
                return inputList.any { it.calculateValue() }
            }
        }

        class Xor(inversion: Boolean, previousList: MutableList<Dot.Out>) :
                Gate.Multivariate(inversion, previousList){
            override fun calculateValue(): Boolean {
                var rez : Boolean = false
                inputList.forEach { rez = it.calculateValue().xor(rez) }
                return rez
            }
        }
    }
}