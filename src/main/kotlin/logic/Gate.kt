package logic

import Deletable
import java.lang.IllegalArgumentException

sealed class Gate : LogElement , Deletable {
    abstract val output : Dot.Out

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

    sealed class Multivariate (inputCount: Int) : logic.Gate() {
        val inputList = arrayListOf<Dot.In>()
        init {
            for (i in 1..inputCount) {
                inputList.add(Dot.In())
            }
        }

        fun changeInPut(index : Int, newPrevious: Dot.Out?){
            try {
                inputList[index].changePrevious(newPrevious)
            }
            catch (ex : IndexOutOfBoundsException){
                throw IllegalArgumentException("Выход за предел списка")
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

        class And(inputCount: Int) : logic.Gate.Multivariate(inputCount){
            override val output: Dot.Out  = Dot.Out(this)
            override fun calculateValue(): Boolean {
                return inputList.all { it.calculateValue() }
            }
        }

        class Or(inputCount: Int) : logic.Gate.Multivariate(inputCount){
            override val output: Dot.Out  = Dot.Out(this)
            override fun calculateValue(): Boolean {
                return inputList.any { it.calculateValue() }
            }
        }

        class Xor(inputCount: Int) : logic.Gate.Multivariate(inputCount){
            override val output: Dot.Out  = Dot.Out(this)
            override fun calculateValue(): Boolean {
                var rez : Boolean = false
                inputList.forEach { rez = it.calculateValue().xor(rez) }
                return rez
            }
        }
    }
}