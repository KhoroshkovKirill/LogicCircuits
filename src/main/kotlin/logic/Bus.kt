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
        var inPut: Dot.Out? = null

        override fun calculateValue() : Boolean{
            if (this.inPut != null) {
                return inPut!!.calculateValue()
            }
            else {
                throw IllegalArgumentException("К выходной шине не подведено соединение")
            }
        }
    }

}