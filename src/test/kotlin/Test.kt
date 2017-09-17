import logic.Bus
import logic.Circuit
import logic.Gate
import org.junit.Test

class Test {

    /*@Test
    fun circuitOne(){
        val circuit = Circuit()
        val x1 = ForBus.In()
        val x2 = ForBus.In()
        val x3 = ForBus.In()
        val x4 = ForBus.In()
        val x5 = ForBus.In()
        circuit.add(x1)
        circuit.add(x2)
        circuit.add(x3)
        circuit.add(x4)
        circuit.add(x5)
        val and = ForGate.Multivariate.And(3)
        and.changeInPut(0,x1.outPut)
        and.changeInPut(1,x2.outPut)
        and.changeInPut(2,x3.outPut)
        val or = ForGate.Multivariate.Or(3)
        or.inputList[1].changeInversion()
        or.changeInPut(0,x4.outPut)
        or.changeInPut(1,x2.outPut)
        or.changeInPut(2,x5.outPut)
        val xor = ForGate.Multivariate.Xor(2)
        xor.output.changeInversion()
        xor.changeInPut(0,and.output)
        xor.changeInPut(1,or.output)
        circuit.add(and)
        circuit.add(or)
        circuit.add(xor)
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
    }*/
}