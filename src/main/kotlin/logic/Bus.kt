package logic

import Deletable

sealed class Bus: LogElement {

    class In : logic.Bus() , Deletable {
        var value = false
        val outPut : Dot.Out = Dot.Out(this)
        override fun calculateValue() = this.value

        override fun prepareToDelete(){
            outPut.deleteFromPrevious()
        }
    }

    class Out: logic.Bus(){
        val input : Dot.In = Dot.In()

        override fun calculateValue() : Boolean = input.calculateValue()
    }

}