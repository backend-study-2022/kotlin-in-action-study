package chapter2

// 코틀린의 예외 처리

fun exceptionTest(int: Int) {
    if(int !in 0..100) {
        throw IllegalArgumentException("error")
    }
}

fun exceptionTest2(number: Int) {
    val percentage = if (number in 0..100) number else throw IllegalArgumentException("Error")
}

fun main() {

    exceptionTest(111)

    exceptionTest2(1)

}
