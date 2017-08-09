import logic.Bus
import logic.Circuit
import logic.Gate
import org.junit.Test

class Test {

    @Test
    fun circuitOne(){
        val circuit = Circuit()
        val x1 = Bus.In()
        val x2 = Bus.In()
        val x3 = Bus.In()
        val x4 = Bus.In()
        val x5 = Bus.In()
        circuit.addBus(x1)
        circuit.addBus(x2)
        circuit.addBus(x3)
        circuit.addBus(x4)
        circuit.addBus(x5)
        val and = Gate.Multivariate.And(3)
        and.changeInPut(0,x1.outPut)
        and.changeInPut(1,x2.outPut)
        and.changeInPut(2,x3.outPut)
        val or = Gate.Multivariate.Or(3)
        or.inputList[1].changeInversion()
        or.changeInPut(0,x4.outPut)
        or.changeInPut(1,x2.outPut)
        or.changeInPut(2,x5.outPut)
        val xor = Gate.Multivariate.Xor(2)
        xor.output.changeInversion()
        xor.changeInPut(0,and.output)
        xor.changeInPut(1,or.output)
        circuit.addGate(and)
        circuit.addGate(or)
        circuit.addGate(xor)
        circuit.outBus.inPut = xor.output
        for (i in 0..31){
            x1.value = (i and 16 != 0)
            x2.value = (i and 8 != 0)
            x3.value = (i and 4 != 0)
            x4.value = (i and 2 != 0)
            x5.value = (i and 1 != 0)
            val y = circuit.outBus.calculateValue()
            println((if (x1.value) "1" else "0") +
                    (if (x2.value) "1" else "0") +
                    (if (x3.value) "1" else "0") +
                    (if (x4.value) "1" else "0") +
                    (if (x5.value) "1" else "0") +
                     " " +
                     if (y) "1" else "0")
        }
    }
}