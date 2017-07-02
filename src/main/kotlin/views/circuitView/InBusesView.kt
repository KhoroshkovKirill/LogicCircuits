package views.circuitView

import logic.Bus

class InBusesView(val circuitView: CircuitView){
    val busList = mutableListOf<BusView.IO.In>()
    var width = 10.0

    fun add(name : String, bus : Bus.In) : Double{
        val busView = BusView.IO.In(name,width,bus)
        busList.add(busView)
        circuitView.children.addAll(busView.getShapes())
        val difference = busView.getWidth()
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

    fun delete(index: Int) : Double{
        try {
            val difference = - busList[index].getWidth()
            busList[index].prepareToDelete()
            busList.removeAt(index)
            moveNextBuses(index + 1, difference)
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