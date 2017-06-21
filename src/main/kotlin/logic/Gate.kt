package logic

sealed class Gate : LogElement {

    class Not : logic.Gate(){
        val output : Dot = Dot.Out(this)
        val input : Dot = Dot.In()
        override fun calculateValue(): Boolean {
            return input.calculateValue()
        }
    }

    sealed class Multivariate(outputsCount : Int) : logic.Gate() {
        open val output : Dot.Out = Dot.Out(this)
        val inputList = mutableListOf<Dot.In>()
        init {
            for (i in 1..outputsCount)
            inputList.add(Dot.In())
        }

        class And(outputsCount: Int) : logic.Gate.Multivariate(outputsCount){
            override fun calculateValue(): Boolean {
                return inputList.all { it.calculateValue() }
            }
        }

        class Or(outputsCount: Int) : logic.Gate.Multivariate(outputsCount){
            override fun calculateValue(): Boolean {
                return inputList.any { it.calculateValue() }
            }
        }

        class Xor(outputsCount: Int) : logic.Gate.Multivariate(outputsCount){
            override fun calculateValue(): Boolean {
                var rez : Boolean = false
                inputList.forEach { rez = it.calculateValue().xor(rez) }
                return rez
            }
        }
    }
}