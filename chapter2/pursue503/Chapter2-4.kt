package chapter2

// Loop, While, for

// 피즈 버즈 게임

fun fizzBuzz(i: Int) = when {
    i % 15 == 0 -> "FizzBuzz "
    i % 3 == 0 -> "Fizz "
    i % 5 == 0 -> "Buzz "
    else -> "$i "
}

fun main() {

    for (i in 1..100) {
        print(fizzBuzz(i))
    }

    println()

    // 2씩 감소
    for (i in 100 downTo 1 step 2) {
        print(fizzBuzz(i))
    }

    // 끝 값을 포함 하지 않음

    for (i in 1 until 10) {
        print(fizzBuzz(i))
    }

}