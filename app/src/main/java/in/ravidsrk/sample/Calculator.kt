package `in`.ravidsrk.sample

class Calculator {

    fun add(op1: Int, op2: Int): Int {
        return op1 + op2
    }

    fun diff(op1: Int, op2: Int): Int {
        return op1 - op2
    }

    fun div(op1: Int, op2: Int): Double {
        // if (op2 == 0) return 0;
        return (op1 / op2).toDouble()
    }
}
