package views.circuitView

import logic.Bus

class InBusesView(val circuitView: CircuitView) {
    val busList = mutableListOf<BusView>()
    var width = 10.0

    fun add(name : String, bus : Bus.In) : Double{
        val busView = BusView(name,width,bus)
        busList.add(busView)
        circuitView.children.addAll(busView.nameText, busView.line)
        val difference = busView.nameText.layoutBounds.width + 5.0
        width += difference
        return difference
    }

    fun rename(index: Int, newName : String) : Double {
        try {
            val difference = busList[index].rename(newName)
            width += difference
            moveNextBuses((index + 1), difference)
            return difference
        } catch (ex: IndexOutOfBoundsException) {
            throw IllegalArgumentException("Выход за предел списка")
        }
    }

    fun moveNextBuses(index: Int, difference : Double){
        for (i in index..(busList.lastIndex) ){
            this.busList[i].move(difference)
        }
    }
}