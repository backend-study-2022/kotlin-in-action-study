import Color.*
import java.io.BufferedReader
import java.io.StringReader
import java.util.TreeMap

fun main(args: Array<String>) {
    println("Hello, world!")
    println(max(1, 2))

    // 2.1.3 변수
    val question = "삶, 우주, 그리고 모든 것에 대한 궁극적인 질문"
    val answer = 42
    val yearsToCompute = 7.5e6

    // val(value) = immutable
    // var(variable) = mutable

    println(question)
    println(answer)
    println(yearsToCompute)

    // val 참조 자체는 불변일지라도 그 참조가 가리키는 객체 내부 값은 변경될 수 있다
    val languages = arrayListOf("Java")
    languages.add("Kotlin")

    // 2.1.4 문자열 템플릿
    val name = if (args.isNotEmpty()) args[0] else "Kotlin"
    println("Hello, $name")
    println("${name}님 반가와요!")
    println("Hello, ${if (args.isNotEmpty()) args[0] else "Kotlin"}")

    val person = Person("Bob", true)    // new 키워드 사용 x
    println(person.name)
    println(person.isMarried)
    person.isMarried = false
    println(person.isMarried)

    val rectangle = Rectangle(41, 43)
    println("rectangle.isSquare = ${rectangle.isSquare}")

    // 2.3.1 enum
    println(RED.rgb())

    // 2.3.2 when
    println(getMnemonic(RED))

    // 한 when 분기 안에 여러 값 사용하기
    println(getWarmth(ORANGE))

    // when의 분기 조건에 여러 다른 객체 사용하기
    println(mix(RED, YELLOW))

    // 2.3.5 스마트 캐스트
    println(eval(Sum(Sum(Num(1), Num(2)), Num(4))))

    // 2.3.6 리팩토링
    println(eval2(Sum(Num(1), Num(2))))

    // 2.4.2 수에 대한 이터레이션
    for (i in 1..100) {
        print(fizzBuzz(i))
    }

    // 증가 값을 갖고 범위 이터레이션
    for (i in 100 downTo 1 step 2) {
        print(fizzBuzz(i))
    }

    val binaryReps = TreeMap<Char, String>()

    for (c in 'A'..'F') {
        val binary = Integer.toBinaryString(c.toInt())
        binaryReps[c] = binary
    }

    for ((letter, binary) in binaryReps) {
        println("$letter = $binary")
    }

    for (i in binaryReps) {
        println(i)
    }

    val get = binaryReps.get('A')
    println(get)

    println(binaryReps['A'])

    // try를 식으로 사용
    val reader = BufferedReader(StringReader("not a number"))
    readNumber(reader)

    val reader2 = BufferedReader(StringReader("1"))
    readNumber(reader2)
}

fun max(a: Int, b: Int): Int {
    return if (a > b) a else b
}

fun max2(a: Int, b: Int) = if (a > b) a else b

class Rectangle(val height: Int, val width: Int) {
    val isSquare: Boolean
        get() { // 프로퍼티 게터 선언
            return height == width
        }
}

enum class Color(
    val r: Int, val g: Int, val b: Int  // 상수의 프로퍼티 정의
) {
    RED(255, 0, 0), ORANGE(255, 165, 0), YELLOW(255, 255, 0);

    fun rgb() = (r * 256 + g) * 256 + b
}

fun getMnemonic(color: Color) =
    when (color) {
        RED -> "Richard"
        ORANGE -> "Of"
        YELLOW -> "York"
    }

fun getWarmth(color: Color) =
    when (color) {
        RED, ORANGE, YELLOW -> "warm"
    }

fun mix(c1: Color, c2: Color) =
    when (setOf(c1, c2)) {
        setOf(RED, YELLOW) -> ORANGE
        else -> {throw Exception("Dirty color")}
    }

interface Expr
class Num(val value: Int) : Expr
class Sum(val left: Expr, val right: Expr) : Expr

fun eval(e: Expr): Int {
    if (e is Num) {
        return e.value
    }
    if (e is Sum) {
        return eval(e.right) + eval(e.left) // 변수 e에 대해 스마트 캐스트를 사용
    }
    throw IllegalArgumentException("Unknown expression")
}

// 값을 만들어내는 if 식
fun eval2(e: Expr): Int =
    if (e is Num) {
        e.value
    } else if (e is Sum) {
        eval2(e.right) + eval2(e.left)
    } else {
        throw IllegalArgumentException("Unknown expression")
    }

// if 중첩 대신 when 사용
fun eval3(e: Expr): Int =
    when (e) {
        is Num -> {
            e.value
        }
        is Sum -> {
            eval3(e.right) + eval3(e.left)
        }
        else -> {
            throw IllegalArgumentException("Unknown expression")
        }
    }

fun fizzBuzz(i : Int) = when {
    i % 15 == 0 -> "FizzBuzz"
    i % 3 == 0 -> "Fizz"
    i % 5 == 0 -> "Buzz"
    else -> "$i"
}

fun readNumber(reader: BufferedReader) {
    val number = try {
        Integer.parseInt(reader.readLine())
    } catch (e: java.lang.NumberFormatException) {
        return
    }
    println(number)
}