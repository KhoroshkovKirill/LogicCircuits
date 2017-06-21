package logic

class Circuit {
    val inBuses = mutableMapOf<String, Bus.In>()
    val gates = mutableListOf<Gate>()
    val outBus = Bus.Out()

    fun addBus(name : String){
        if (inBuses.containsKey(name)){
            throw IllegalArgumentException("Шина с таким именем уже существует")
        }
        else {
            inBuses.put(name, Bus.In())
        }
    }

    fun addGateNOT(){
        gates.add(Gate.Not())
    }

    fun addGateAND(outputsCount : Int){
        gates.add(Gate.Multivariate.And(outputsCount))
    }

    fun addGateOR(outputsCount : Int){
        gates.add(Gate.Multivariate.Or(outputsCount))
    }

    fun addGateXOR(outputsCount : Int){
        gates.add(Gate.Multivariate.Xor(outputsCount))
    }
}