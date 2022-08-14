package chapter2


/*
    이 질의 서두에 있는 "Hello World" 예제로 다시 돌아가자

    다음은 사람이름을 사용해 환영 인사를 출력하는 코틀린 프로그램이다.
 */

fun main(args: Array<String>) {

    val name = if (args.size > 0 ) args[0] else "Kotlin"

    println("Hello, $name")

}