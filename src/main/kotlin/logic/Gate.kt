package logic

import java.lang.IllegalArgumentException

sealed class Gate : LogElement {
    abstract val output : Dot.Out
    abstract fun prepareToDelete()

    class Not : logic.Gate(){
        override val output : Dot.Out = Dot.Out(this, true)
        private val input : Dot.In = Dot.In()

        fun changeInPut(newPrevious: Dot.Out?) {
            input.changePrevious(newPrevious)
        }

        override fun prepareToDelete(){
            output.deleteFromPrevious()
            if (input.previous != null){
                input.previous!!.deleteFromNextList(input)
            }
        }

        override fun calculateValue(): Boolean {
            return input.calculateValue()
        }
    }

    sealed class Multivariate (outputsCount : Int) : logic.Gate() {
        val inputList = arrayListOf<Dot.In>()
        init {
            for (i in 1..outputsCount) {
                inputList.add(Dot.In())
            }
        }

        fun changeInPut(index : Int, newPrevious: Dot.Out?){
            try {
                inputList[index].changePrevious(newPrevious)
            }
            catch (ex : IndexOutOfBoundsException){
                throw IllegalArgumentException("Выход за предел массива")
            }
        }

        override fun prepareToDelete() {
            output.deleteFromPrevious()
            for (input in inputList) {
                if (input.previous != null) {
                    input.previous!!.deleteFromNextList(input)
                }
            }
        }

        class And(outputsCount: Int) : logic.Gate.Multivariate(outputsCount){
            override val output: Dot.Out  = Dot.Out(this)
            override fun calculateValue(): Boolean {
                return inputList.all { it.calculateValue() }
            }
        }

        class Or(outputsCount: Int) : logic.Gate.Multivariate(outputsCount){
            override val output: Dot.Out  = Dot.Out(this)
            override fun calculateValue(): Boolean {
                return inputList.any { it.calculateValue() }
            }
        }

        class Xor(outputsCount: Int) : logic.Gate.Multivariate(outputsCount){
            override val output: Dot.Out  = Dot.Out(this)
            override fun calculateValue(): Boolean {
                var rez : Boolean = false
                inputList.forEach { rez = it.calculateValue().xor(rez) }
                return rez
            }
        }
    }
}